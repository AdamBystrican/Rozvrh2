package com.example.Rozvrh.Group;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/group")
public class GroupController {
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public String createGroup(@RequestBody GroupDto groupDto){
        return groupService.createGroup(groupDto);
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId){
        return groupService.getGroup(groupId);
    }

    @GetMapping
    public List<GroupDto> getGroups(){
        return groupService.getGroups();
    }

    @PutMapping("/{groupId}")
    public String updateGroup(@PathVariable Long groupId,@RequestBody GroupDto groupDto){
        return groupService.updateGroup(groupId, groupDto);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable Long groupId){
        groupService.deleteGroup(groupId);
    }
}
