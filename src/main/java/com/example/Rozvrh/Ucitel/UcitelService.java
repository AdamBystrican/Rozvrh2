package com.example.Rozvrh.Ucitel;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UcitelService {
    UcitelRpository ucitelRpository;

    public UcitelService(UcitelRpository ucitelRpository) {
        this.ucitelRpository = ucitelRpository;
    }

    private static UcitelDto mapToUcitelDto(UcitelEntity ucitelEntity){
        UcitelDto ucitelDto = new UcitelDto();
        ucitelDto.setMeno(ucitelEntity.getMeno());
        ucitelDto.setPriezvisko(ucitelEntity.getPriezvisko());
        ucitelDto.setKontakt(ucitelEntity.getKontakt());
        return ucitelDto;
    }

    @Transactional
    public Long createUcitel(UcitelDto ucitelDto){
        UcitelEntity ue = new UcitelEntity();
        ue.setMeno(ucitelDto.getMeno());
        ue.setPriezvisko(ucitelDto.getPriezvisko());
        ue.setKontakt(ucitelDto.getKontakt());
        this.ucitelRpository.save(ue);
        return ue.getId();
    }

    @Transactional
    public UcitelDto getUcitel(Long ucitelId){
        Optional<UcitelEntity> byId = this.ucitelRpository.findById(ucitelId);
        if(byId.isPresent()){
            return mapToUcitelDto(byId.get());
        }
        return null;
    }

    @Transactional
    public List<UcitelDto> getUcitelia(String menoUcitela){
        List<UcitelDto> ucitelia = new LinkedList<>();
        for(UcitelEntity u1 : ucitelRpository.findAll()){
            UcitelDto u2 = mapToUcitelDto(u1);
            ucitelia.add(u2);
        }
        return ucitelia;
    }

    @Transactional
    public void updateUcitel(int ucitelId, UcitelDto ucitelDto){
        Optional<UcitelEntity> byId = ucitelRpository.findById((long)ucitelId);
        if(byId.isPresent()){
            byId.get().setMeno(ucitelDto.getMeno());
            byId.get().setPriezvisko(ucitelDto.getPriezvisko());
            byId.get().setKontakt(ucitelDto.getKontakt());
        }
    }

    @Transactional
    public void deleteUcitel(Long ucitelId){
        ucitelRpository.deleteById(ucitelId);
    }
}
