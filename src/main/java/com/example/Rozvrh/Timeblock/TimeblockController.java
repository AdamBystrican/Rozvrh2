package com.example.Rozvrh.Timeblock;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeblock")
public class TimeblockController {
    TimeblockService timeblockService;

    public TimeblockController(TimeblockService timeblockService) {
        this.timeblockService = timeblockService;
    }
    @PostMapping
    public String createTimeblock(@RequestBody TimeblockDto timeblockDto){
        return timeblockService.createTimeblock(timeblockDto);
    }
    @GetMapping
    public List<TimeblockDto> getTimeblocks(@RequestParam(required = false)String name, @RequestParam(required = false)String type){
        return timeblockService.getTimeblocks(name, type);
    }
    @GetMapping("/{timeblockId}")
    public TimeblockDto getTimeblock(@PathVariable Long timeblockId){
        return timeblockService.getTimeblock(timeblockId);
    }

    /*@GetMapping("/{dayId}")
    public List<TimeblockDto> getRozvrhOfTheDay(@PathVariable int dayId){
        return timeblockService.getRozvrhOfTheDay(dayId);
    }*/
    @PutMapping("/{timeblockId}")
    public String updateTimeblock(@PathVariable Long timeblockId, @RequestBody TimeblockDto timeblockDto){
        return timeblockService.updateTimeblock(timeblockDto,timeblockId);
    }
    @DeleteMapping("/{timeblockId}")
    public void deleteTimeblock(@PathVariable Long timeblockId){
        timeblockService.deleteTimeblock(timeblockId);
    }



}