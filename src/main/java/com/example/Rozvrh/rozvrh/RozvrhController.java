package com.example.Rozvrh.rozvrh;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rozvrh")
public class RozvrhController {
    RozvrhService rozvrhService;

    public RozvrhController(RozvrhService rozvrhService) {
        this.rozvrhService = rozvrhService;
    }
    @PostMapping
    public Long createRozvrh(@RequestBody RozvrhDto rozvrhDto){
        return rozvrhService.createRozvrh(rozvrhDto);
    }
    @GetMapping
    public List<RozvrhDto> getRozvrhy(){
        return rozvrhService.getRozvrhy();
    }
    @GetMapping("/{rozvrhId}")
    public RozvrhDto getRozvrh(@PathVariable Long rozvrhId){
        return rozvrhService.getRozvrh(rozvrhId);
    }
    @PutMapping("/{rozvrhId}")
    public void updateRozvrh(@PathVariable Long rozvrhId, @RequestBody RozvrhDto rozvrhDto){
        rozvrhService.updateRozvrh(rozvrhDto,rozvrhId);
    }
    @DeleteMapping("/{rozvrhId}")
    public void deleteRozvrh(@PathVariable Long rozvrhId){
        rozvrhService.deleteRozvrh(rozvrhId);
    }



}
