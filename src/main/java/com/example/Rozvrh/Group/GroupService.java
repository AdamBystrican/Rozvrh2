package com.example.Rozvrh.Group;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    private static GroupDto mapToGroupDto(GroupEntity groupEntity){
        GroupDto groupDto = new GroupDto();
        groupDto.setName(groupEntity.getName());
        groupDto.setId(groupEntity.getId());
        return groupDto;
    }

    @Transactional
    public String createGroup(GroupDto groupDto){
        List<GroupDto> groups = new LinkedList<>();
        for(GroupEntity g1 : groupRepository.findAll()){
            if(g1.getName().equals(groupDto.getName()))
                return "Group with this name already exists";
        }
        GroupEntity ge = new GroupEntity();
        ge.setName(groupDto.getName());
        this.groupRepository.save(ge);
        return ge.getId().toString();
    }

   @Transactional
   public GroupDto getGroup(Long groupId){
       Optional<GroupEntity> byId = this.groupRepository.findById(groupId);
       if(byId.isPresent()){
           return mapToGroupDto(byId.get());
       }
       return null;
   }

    @Transactional
    public List<GroupDto> getGroups(){
        List<GroupDto> groups = new LinkedList<>();
        for(GroupEntity g1 : groupRepository.findAll()){
            GroupDto g2 = mapToGroupDto(g1);
            groups.add(g2);
        }
        return groups;
    }

    @Transactional
    public String updateGroup(Long groupId, GroupDto groupDto){
        for(GroupEntity g1 : groupRepository.findAll()){
            if(g1.getName().equals(groupDto.getName()))
                return "Group with this name already exists";
        }
        Optional<GroupEntity> byId = this.groupRepository.findById(groupId);
        if(byId.isPresent()){
            byId.get().setName(groupDto.getName());
            return byId.get().getId().toString();
        }
        return "Wrong Id";
    }

    @Transactional
    public void deleteGroup(Long groupId){
        this.groupRepository.deleteById(groupId);
    }

}
