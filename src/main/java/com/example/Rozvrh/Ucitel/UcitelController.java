package com.example.Rozvrh.Ucitel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ucitelia")
public class UcitelController {
    UcitelService ucitelService;

    public UcitelController(UcitelService ucitelService) {
        this.ucitelService = ucitelService;
    }

    @PostMapping
    public Long createUciel(@RequestBody UcitelDto ucitelDto){
        return ucitelService.createUcitel(ucitelDto);
    }
    @GetMapping("/{ucitelId}")
    public UcitelDto getUcitel(@PathVariable Long ucitelId){
        return ucitelService.getUcitel(ucitelId);
    }
    @GetMapping
    public List<UcitelDto> getUcitelia(@RequestParam(required = false)String menoUcitela){
        return ucitelService.getUcitelia(menoUcitela);
    }
    @PutMapping("/{ucitelId}")
    public void updateUcitel(@PathVariable int ucitelId, @RequestBody UcitelDto ucitelDto){
        ucitelService.updateUcitel(ucitelId,ucitelDto);
    }
    @DeleteMapping("/{ucitelId}")
    public void deleteUcitel(@PathVariable Long ucitelId){
        ucitelService.deleteUcitel(ucitelId);
    }
}
