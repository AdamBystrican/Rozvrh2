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
        rozvrhDto.setUcitelId(rozvrhEntity.getUcitel().getId());
        rozvrhDto.setPredmetId(rozvrhEntity.getPredmet().getId());
        rozvrhDto.setUcebnaId(rozvrhEntity.getUcebna().getId());
        rozvrhDto.setDen(rozvrhEntity.getDen());
        rozvrhDto.setTrieda(rozvrhEntity.getTrieda());
        rozvrhDto.setStart(rozvrhEntity.getStart());
        rozvrhDto.setFinish(rozvrhEntity.getFinish());
        rozvrhDto.setId(rozvrhEntity.getId());

        return rozvrhDto;
    }

    @Transactional
    public Long createRozvrh(RozvrhDto rozvrhDto){
        //kontrola, či je zadaný čas správne, a či učiteľ, trieda, alebo učebňa nie sú v danom čase obsadené
        kontrolaRozvrhu(rozvrhDto);

            RozvrhEntity re = new RozvrhEntity();
            //priraďovanie id pre učiteľa, predmet a učebňu, ak sa id v databáze nenachádza, vypíše sa chyba, aby sa nepridali
            // do databázy id s null
            Optional<UcitelEntity> ucitelById = ucitelRpository.findById(rozvrhDto.getUcitelId());
            if (ucitelById.isPresent()) {
                re.setUcitel(ucitelById.get());
            } else throw new RuntimeException("zadané id pre učiteľa neexistuje");

            Optional<UcebnaEntity> ucebnaById = ucebnaRepository.findById(rozvrhDto.getUcebnaId());
            if (ucebnaById.isPresent()) {
                re.setUcebna(ucebnaById.get());
            } else throw new RuntimeException("zadané id pre učebňu neexistuje");

            Optional<PredmetEntity> predmetById = predmetRepository.findById(rozvrhDto.getPredmetId());
            if (predmetById.isPresent()) {
                re.setPredmet(predmetById.get());
            } else throw new RuntimeException("zadané id pre predmet neexistuje");

            re.setDen(rozvrhDto.getDen());
            re.setTrieda(rozvrhDto.getTrieda());
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
        //kontrola, či je zadaný čas správne, a či učiteľ, trieda, alebo učebňa nie sú v danom čase obsadené
        kontrolaRozvrhu(rozvrhDto);
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
            re.get().setTrieda(rozvrhDto.getTrieda());
            re.get().setStart(rozvrhDto.getStart());
            re.get().setFinish(rozvrhDto.getFinish());
        }

    }
    @Transactional
    public void deleteRozvrh(Long rozvrhId){
        rozvrhRepository.deleteById(rozvrhId);
    }


    //funkcia vráti zoznam všetkých blokov v rozvrhu pre deň, ktorý je zadaný ako parameter funkcie
    @Transactional
    public List<RozvrhDto> getRozvrhDna(int den){
        List<RozvrhDto> ret = new LinkedList<>();
        for(RozvrhEntity r1 : rozvrhRepository.findAll()){
            RozvrhDto r2 = mapToRozvrhDto(r1);
            if(r2.getDen() == den)
                ret.add(r2);
        }
        return ret;
    }
    /*
    //funkcia vráti zoznam všetkých blokov v rozvrhu pre učebňu, ktorej id je zadané ako parameter funkcie
    @Transactional
    public List<RozvrhDto> getRozvrhUcebne(Long ucebnaId){
        List<RozvrhDto> ret = new LinkedList<>();
        for(RozvrhEntity r1 : rozvrhRepository.findAll()){
            RozvrhDto r2 = mapToRozvrhDto(r1);
            if(r2.getUcebnaId() == ucebnaId)
                ret.add(r2);
        }
        return ret;
    }
    //funkcia vráti zoznam všetkých blokov v rozvrhu pre učiteľa, ktorého id je zadané ako parameter funkcie
    @Transactional
    public List<RozvrhDto> getRozvrUcitela(int ucitelId){
        List<RozvrhDto> ret = new LinkedList<>();
        for(RozvrhEntity r1 : rozvrhRepository.findAll()){
            RozvrhDto r2 = mapToRozvrhDto(r1);
            if(r2.getUcitelId() == ucitelId)
                ret.add(r2);
        }
        return ret;
    }
    //funkcia vráti zoznam všetkých blokov v rozvrhu pre predmet, ktorého id je zadané ako parameter funkcie
    @Transactional
    public List<RozvrhDto> getRozvrhpredmetu(int predmetId){
        List<RozvrhDto> ret = new LinkedList<>();
        for(RozvrhEntity r1 : rozvrhRepository.findAll()){
            RozvrhDto r2 = mapToRozvrhDto(r1);
            if(r2.getPredmetId() == predmetId)
                ret.add(r2);
        }
        return ret;
    }
    //funkcia vráti zoznam všetkých blokov v rozvrhu pre predmet, ktorého id je zadané ako parameter funkcie
    @Transactional
    public List<RozvrhDto> getRozvrhTriedy(String trieda){
        List<RozvrhDto> ret = new LinkedList<>();
        for(RozvrhEntity r1 : rozvrhRepository.findAll()){
            RozvrhDto r2 = mapToRozvrhDto(r1);
            if(r2.getTrieda() == trieda)
                ret.add(r2);
        }
        return ret;
    }
*/
    //kontrola, či učiteľ, učebňa alebo trieda v danom čase a daný deň už nie sú obsadené, ak sú tak throw new Runtimeexception
    //funkcia sa bude volať pri create alebo update
    public void kontrolaRozvrhu(RozvrhDto rozvrhDto){
        if(!(rozvrhDto.kontrolaStartEnd()))
            throw new RuntimeException("zle zadaný čas");
        List<RozvrhDto> ret = getRozvrhDna(rozvrhDto.getDen());
        for(RozvrhDto r1 : ret) {
            if (r1.getUcitelId() == rozvrhDto.getUcitelId()) {
                if (!r1.kontrolaCasu(rozvrhDto.getStart(), rozvrhDto.getFinish()))
                    throw new RuntimeException("Učiteľ je v danom čase obsadený");
            }
            if (r1.getUcebnaId() == rozvrhDto.getUcebnaId()) {
                if (!r1.kontrolaCasu(rozvrhDto.getStart(), rozvrhDto.getFinish()))
                    throw new RuntimeException("Učebňa je v danom čase obsadená");
            }
            if (r1.getTrieda() == rozvrhDto.getTrieda()) {
                if (!r1.kontrolaCasu(rozvrhDto.getStart(), rozvrhDto.getFinish()))
                    throw new RuntimeException("Trieda v danom čase má nejakú hodinu");
            }

        }
    }

}
