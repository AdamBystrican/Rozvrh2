package com.example.Rozvrh.Predmet;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PredmetService {

    private PredmetRepository predmetRepository;

    public PredmetService(PredmetRepository predmetRepository){
        this.predmetRepository = predmetRepository;
    }

    private static PredmetDto mapToPredmetDto(PredmetEntity predmetEntity){
        PredmetDto predmetDto = new PredmetDto();
        predmetDto.setName(predmetEntity.getName());
        predmetDto.setComputersRequired(predmetEntity.isComputersRequired());
        predmetDto.setType(predmetEntity.getType());
        return predmetDto;
    }

    @Transactional
    public Long createPredmet(PredmetDto predmetDto){
        PredmetEntity pe = new PredmetEntity();
        pe.setName(predmetDto.getName());
        pe.setComputersRequired(predmetDto.isComputersRequired());
        pe.setType(predmetDto.getType());
        this.predmetRepository.save(pe);
        return pe.getId();
    }

    @Transactional
    public PredmetDto getPredmet(Long predmetId){
        Optional<PredmetEntity> byId =predmetRepository.findById(predmetId);
            if(byId.isPresent()){
                return mapToPredmetDto(byId.get());
            }
            return null;
    }

   @Transactional
   public List<PredmetDto> getPredmety(String nazovPredmetu){
       List<PredmetDto> predmety = new LinkedList<>();
       for(PredmetEntity p1 : predmetRepository.findAll()){
            PredmetDto p2 =mapToPredmetDto(p1);
            predmety.add(p2);
       }
       return predmety;
   }

   @Transactional
   public void updatePredmet(int predmetId, PredmetDto predmetDto){
        Optional<PredmetEntity> byId = predmetRepository.findById((long)predmetId);
        if(byId.isPresent()){
            byId.get().setName(predmetDto.getName());
            byId.get().setComputersRequired(predmetDto.isComputersRequired());
            byId.get().setType(predmetDto.getType());
        }
   }
    @Transactional
    public void delete(Long predmetId){
        predmetRepository.deleteById(predmetId);
    }


}
