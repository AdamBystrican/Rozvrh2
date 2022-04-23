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
    public Long createTimeblock(@RequestBody TimeblockDto timeblockDto){
        return timeblockService.createTimeblock(timeblockDto);
    }
    @GetMapping
    public List<TimeblockDto> getTimeblocks(){
        return timeblockService.getTimeblocks();
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
    public void updateTimeblock(@PathVariable Long timeblockId, @RequestBody TimeblockDto timeblockDto){
        timeblockService.updateTimeblock(timeblockDto,timeblockId);
    }
    @DeleteMapping("/{timeblockId}")
    public void deleteTimeblock(@PathVariable Long timeblockId){
        timeblockService.deleteTimeblock(timeblockId);
    }



}
