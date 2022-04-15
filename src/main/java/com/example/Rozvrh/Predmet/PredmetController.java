package com.example.Rozvrh.Predmet;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/predmety")
public class PredmetController {
    private PredmetService predmetService;

    public PredmetController(PredmetService predmetService) {
        this.predmetService = predmetService;
    }

    @PostMapping
    public Long createPredmet(@RequestBody PredmetDto predmetDto){
        return predmetService.createPredmet(predmetDto);
    }
    @GetMapping
    public List<PredmetDto> getPredmety(@RequestParam(required = false) String nazovPredmetu){
        return predmetService.getPredmety(nazovPredmetu);
    }
    @GetMapping("/{predmetId}")
    public PredmetDto getPredmet(@PathVariable Long predmetId){
        return predmetService.getPredmet(predmetId);
    }
    @PutMapping("/{predmetId}")
    public void updatePredmet(@PathVariable int predmetId, @RequestBody PredmetDto predmetDto){
        predmetService.updatePredmet(predmetId,predmetDto);
    }
    @DeleteMapping("/{predmetId}")
    public void deletePredmet(@PathVariable Long predmetId){
        predmetService.delete(predmetId);
    }
}
