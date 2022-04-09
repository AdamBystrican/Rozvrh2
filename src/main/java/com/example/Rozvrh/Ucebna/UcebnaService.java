package com.example.Rozvrh.Ucebna;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UcebnaService {
    private UcebnaRepository ucebnaRepository;

    public UcebnaService(UcebnaRepository ucebnaRepository) {
        this.ucebnaRepository = ucebnaRepository;
    }

    private static UcebnaDto mapToUcebnaDto(UcebnaEntity ucebnaEntity){
        UcebnaDto ucebnaDto = new UcebnaDto();
        ucebnaDto.setNazov(ucebnaEntity.getNazov());
        ucebnaDto.setMaPocitace(ucebnaEntity.isMaPocitace());
        ucebnaDto.setAdresa(ucebnaEntity.getAdresa());
        return ucebnaDto;
    }

    @Transactional
    public Long createUcebna(UcebnaDto ucebnaDto){
        UcebnaEntity ue = new UcebnaEntity();
        ue.setNazov(ucebnaDto.getNazov());
        ue.setMaPocitace(ucebnaDto.isMaPocitace());
        ue.setAdresa(ucebnaDto.getAdresa());
        ucebnaRepository.save(ue);
        return ue.getId();
    }
    @Transactional
    public UcebnaDto getUcebna(Long ucebnaId){
        Optional<UcebnaEntity> byId = ucebnaRepository.findById(ucebnaId);
        if(byId.isPresent()){
            return mapToUcebnaDto(byId.get());
        }
        return null;
    }

    @Transactional
    public List<UcebnaDto> getUcebne(String nazovUcebne){
        List<UcebnaDto> ucebne = new LinkedList<>();
        for(UcebnaEntity u1 : ucebnaRepository.findAll()){
            UcebnaDto u2 = mapToUcebnaDto(u1);
            ucebne.add(u2);
        }
        return ucebne;
    }

    @Transactional
    public void updateUcebna(Long ucebnaId, UcebnaDto ucebnaDto){
        Optional<UcebnaEntity> byId = ucebnaRepository.findById(ucebnaId);
        if(byId.isPresent()){
            byId.get().setNazov(ucebnaDto.getNazov());
            byId.get().setMaPocitace(ucebnaDto.isMaPocitace());
            byId.get().setAdresa(ucebnaDto.getAdresa());
        }
    }

    @Transactional
    public void deleteUcebna(Long ucebnaId){
        ucebnaRepository.deleteById(ucebnaId);
    }
}
