package com.example.Rozvrh.rozvrh;

import com.example.Rozvrh.Den;
import com.example.Rozvrh.Predmet.PredmetEntity;
import com.example.Rozvrh.Predmet.PredmetRepository;
import com.example.Rozvrh.Ucebna.UcebnaEntity;
import com.example.Rozvrh.Ucebna.UcebnaRepository;
import com.example.Rozvrh.Ucitel.UcitelEntity;
import com.example.Rozvrh.Ucitel.UcitelRpository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RozvrhService {
    private UcitelRpository ucitelRpository;
    private PredmetRepository predmetRepository;
    private UcebnaRepository ucebnaRepository;
    private RozvrhRepository rozvrhRepository;

    public RozvrhService(UcitelRpository ucitelRpository, PredmetRepository predmetRepository, UcebnaRepository ucebnaRepository, RozvrhRepository rozvrhRepository) {
        this.ucitelRpository = ucitelRpository;
        this.predmetRepository = predmetRepository;
        this.ucebnaRepository = ucebnaRepository;
        this.rozvrhRepository = rozvrhRepository;
    }

    private static RozvrhDto mapToRozvrhDto(RozvrhEntity rozvrhEntity){
        RozvrhDto rozvrhDto = new RozvrhDto();
        rozvrhDto.setUcitel(rozvrhEntity.getUcitel().getFullName());
        rozvrhDto.setPredmet(rozvrhEntity.getPredmet().getName());
        rozvrhDto.setUcebna(rozvrhEntity.getUcebna().getName());
        rozvrhDto.setDen(rozvrhEntity.getDen());
        rozvrhDto.setStart(rozvrhEntity.getStart());
        rozvrhDto.setFinish(rozvrhEntity.getFinish());
        rozvrhDto.setId(rozvrhEntity.getId());

        return rozvrhDto;
    }

    @Transactional
    public Long createRozvrh(RozvrhDto rozvrhDto){
        if(!(rozvrhDto.kontrolaStartEnd()))
            throw new RuntimeException("zle zadany cas");
        // vytvorim si list dam donho rozvrh dna a  ak sa prvok listu.getucitelid = rozvrhdto.getucitelid,
        // tak zavolam kontrolu casu a to iste pre ucebnu.
        RozvrhEntity re = new RozvrhEntity();
        Optional<UcitelEntity> ucitelById = ucitelRpository.findById(rozvrhDto.getUcitelId());
        if(ucitelById.isPresent()){
            re.setUcitel(ucitelById.get());
        }
        Optional<UcebnaEntity> ucebnaById = ucebnaRepository.findById(rozvrhDto.getUcebnaId());
        if(ucebnaById.isPresent()){
            re.setUcebna(ucebnaById.get());
        }
        Optional<PredmetEntity> predmetById = predmetRepository.findById(rozvrhDto.getPredmetId());
        if(predmetById.isPresent()){
            re.setPredmet(predmetById.get());
        }
        re.setDen(rozvrhDto.getDen());
        re.setStart(rozvrhDto.getStart());
        re.setFinish(rozvrhDto.getFinish());

        this.rozvrhRepository.save(re);
        return re.getId();
    }

    @Transactional
    public RozvrhDto getRozvrh(Long rozvrhId){
        Optional<RozvrhEntity> byId = rozvrhRepository.findById(rozvrhId);
        if(byId.isPresent()){
            RozvrhDto r = mapToRozvrhDto(byId.get());
            r.setPredmetId(byId.get().getPredmet().getId());
            r.setUcitelId(byId.get().getUcitel().getId());
            r.setUcebnaId(byId.get().getUcebna().getId());
            return r;
        }
        return null;
    }

    @Transactional
    public List<RozvrhDto> getRozvrhy(){
        List<RozvrhDto> ret = new LinkedList<>();
        for(RozvrhEntity r1 : rozvrhRepository.findAll()){
            RozvrhDto r2 = mapToRozvrhDto(r1);
            r2.setPredmetId(r1.getPredmet().getId());
            r2.setUcitelId(r1.getUcitel().getId());
            r2.setUcebnaId(r1.getUcebna().getId());
            ret.add(r2);
        }
        return ret;
    }
    @Transactional
    public void updateRozvrh(RozvrhDto rozvrhDto,Long rozvrhId){
        if(!(rozvrhDto.kontrolaStartEnd()))
            throw new RuntimeException("zle zadany cas");

        Optional<RozvrhEntity> re = rozvrhRepository.findById(rozvrhId);
        if(re.isPresent()){
            Optional<UcitelEntity> ucitelById = ucitelRpository.findById(rozvrhDto.getUcitelId());
            if(ucitelById.isPresent()){
                re.get().setUcitel(ucitelById.get());
            }
            Optional<UcebnaEntity> ucebnaById = ucebnaRepository.findById(rozvrhDto.getUcebnaId());
            if(ucebnaById.isPresent()){
                re.get().setUcebna(ucebnaById.get());
            }
            Optional<PredmetEntity> predmetById = predmetRepository.findById(rozvrhDto.getPredmetId());
            if(predmetById.isPresent()){
                re.get().setPredmet(predmetById.get());
            }
            re.get().setDen(rozvrhDto.getDen());
            re.get().setStart(rozvrhDto.getStart());
            re.get().setFinish(rozvrhDto.getFinish());
        }
    }
    @Transactional
    public void deleteRozvrh(Long rozvrhId){
        rozvrhRepository.deleteById(rozvrhId);
    }

    public List<RozvrhDto> getRozvrhDna(Den den){
        List<RozvrhDto> ret = new LinkedList<>();
        for(RozvrhEntity r1 : rozvrhRepository.findAll()){
            RozvrhDto r2 = mapToRozvrhDto(r1);
            if(r2.getDen()== den)
                ret.add(r2);
        }
        return ret;
    }
}
