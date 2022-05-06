package com.example.Rozvrh.Timeblock.controller;

import com.example.Rozvrh.Timeblock.service.TimeblockCreateDto;
import com.example.Rozvrh.Timeblock.service.TimeblockDto;
import com.example.Rozvrh.Timeblock.service.TimeblockService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/timeblock")
public class TimeblockController {
    TimeblockService timeblockService;

    public TimeblockController(TimeblockService timeblockService) {
        this.timeblockService = timeblockService;
    }
    @PostMapping
    public String createTimeblock(@Valid @RequestBody TimeblockCreateDto timeblockCreateDto){
        return timeblockService.createTimeblock(timeblockCreateDto);
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
    public String updateTimeblock(@PathVariable Long timeblockId, @RequestBody TimeblockCreateDto timeblockCreateDto){
        return timeblockService.updateTimeblock(timeblockCreateDto,timeblockId);
    }
    @DeleteMapping("/{timeblockId}")
    public void deleteTimeblock(@PathVariable Long timeblockId){
        timeblockService.deleteTimeblock(timeblockId);
    }



}