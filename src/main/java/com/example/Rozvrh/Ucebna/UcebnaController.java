package com.example.Rozvrh.Ucebna;

import com.example.Rozvrh.Predmet.PredmetDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ucebne")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UcebnaController {
    private UcebnaService ucebnaService;

    public UcebnaController(UcebnaService ucebnaService) {
        this.ucebnaService = ucebnaService;
    }
    @PostMapping
    public Long createUcebna(@RequestBody UcebnaDto ucebnaDto){
        return ucebnaService.createUcebna(ucebnaDto);
    }
    @GetMapping
    public List<UcebnaDto> getUcebne(@RequestParam(required = false) String nazovUcebne){
        return ucebnaService.getUcebne(nazovUcebne);
    }
    @GetMapping("/{ucebnaId}")
    public UcebnaDto getUcebna(@PathVariable Long ucebnaId){
        return ucebnaService.getUcebna(ucebnaId);
    }
    @PutMapping("/{ucebnaId}")
    public void updateUcebna(@PathVariable Long ucebnaId, @RequestBody UcebnaDto ucebnaDto){
        ucebnaService.updateUcebna(ucebnaId, ucebnaDto);
    }
    @DeleteMapping("/{ucebnaId}")
    public void deleteUcebna(@PathVariable Long ucebnaId){
        ucebnaService.deleteUcebna(ucebnaId);
    }
}
