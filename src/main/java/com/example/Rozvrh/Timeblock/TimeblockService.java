package com.example.Rozvrh.Timeblock;

import com.example.Rozvrh.Group.GroupEntity;
import com.example.Rozvrh.Group.GroupRepository;
import com.example.Rozvrh.Subject.SubjectEntity;
import com.example.Rozvrh.Subject.SubjectRepository;
import com.example.Rozvrh.Classroom.ClassroomEntity;
import com.example.Rozvrh.Classroom.ClassroomRepository;
import com.example.Rozvrh.Teacher.TeacherEntity;
import com.example.Rozvrh.Teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeblockService {
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private ClassroomRepository classroomRepository;
    private GroupRepository groupRepository;
    private TimeblockRepository timeblockRepository;

    public TimeblockService(TeacherRepository teacherRepository, SubjectRepository subjectRepository, ClassroomRepository classroomRepository, GroupRepository groupRepository, TimeblockRepository timeblockRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.classroomRepository = classroomRepository;
        this.groupRepository = groupRepository;
        this.timeblockRepository = timeblockRepository;
    }

    private static TimeblockDto mapToTimeblockDto(TimeblockEntity timeblockEntity){
        TimeblockDto timeblockDto = new TimeblockDto();
        timeblockDto.setTeacher(timeblockEntity.getTeacher().getFullName());
        timeblockDto.setSubject(timeblockEntity.getSubject().getName());
        timeblockDto.setClassroom(timeblockEntity.getClassroom().getName());
        timeblockDto.setGroup(timeblockEntity.getGroup().getName());
        timeblockDto.setTeacherId(timeblockEntity.getTeacher().getId());
        timeblockDto.setSubjectId(timeblockEntity.getSubject().getId());
        timeblockDto.setClassroomId(timeblockEntity.getClassroom().getId());
        timeblockDto.setGroupId(timeblockEntity.getGroup().getId());
        timeblockDto.setDay(timeblockEntity.getDay());
        timeblockDto.setClassroom(timeblockEntity.getClassroom().getName());
        timeblockDto.setStart(timeblockEntity.getStart());
        timeblockDto.setFinish(timeblockEntity.getFinish());
        timeblockDto.setId(timeblockEntity.getId());

        return timeblockDto;
    }

    @Transactional
    public Long createTimeblock(TimeblockDto timeblockDto){
        //kontrola, či je zadaný čas správne, a či učiteľ, trieda, alebo učebňa nie sú v danom čase obsadené
        TimeblockCheck(timeblockDto);

            TimeblockEntity tbe = new TimeblockEntity();
            //priraďovanie id pre učiteľa, predmet a učebňu, ak sa id v databáze nenachádza, vypíše sa chyba, aby sa nepridali
            // do databázy id s null
            Optional<TeacherEntity> teacherById = teacherRepository.findById(timeblockDto.getTeacherId());
            if (teacherById.isPresent()) {
                tbe.setTeacher(teacherById.get());
            } else throw new RuntimeException("zadané id pre učiteľa neexistuje");

            Optional<ClassroomEntity> ClassroomById = classroomRepository.findById(timeblockDto.getClassroomId());
            if (ClassroomById.isPresent()) {
                tbe.setClassroom(ClassroomById.get());
            } else throw new RuntimeException("zadané id pre učebňu neexistuje");

            Optional<SubjectEntity> subjectById = subjectRepository.findById(timeblockDto.getSubjectId());
            if (subjectById.isPresent()) {
                tbe.setSubject(subjectById.get());
            } else throw new RuntimeException("zadané id pre predmet neexistuje");

            Optional<GroupEntity> groupById = groupRepository.findById(timeblockDto.getGroupId());
            if (groupById.isPresent()) {
                tbe.setGroup(groupById.get());
            } else throw new RuntimeException("zadané id pre triedu neexistuje");

            tbe.setDay(timeblockDto.getDay());
            tbe.setStart(timeblockDto.getStart());
            tbe.setFinish(timeblockDto.getFinish());

            this.timeblockRepository.save(tbe);
            return tbe.getId();
    }

    @Transactional
    public TimeblockDto getTimeblock(Long timeblockId){
        Optional<TimeblockEntity> byId = timeblockRepository.findById(timeblockId);
        if(byId.isPresent()){
            TimeblockDto tb = mapToTimeblockDto(byId.get());
            return tb;
        }
        return null;
    }

    @Transactional
    public List<TimeblockDto> getTimeblocks(){
        List<TimeblockDto> ret = new LinkedList<>();
        for(TimeblockEntity tb1 : timeblockRepository.findAll()){
            TimeblockDto tb2 = mapToTimeblockDto(tb1);
            ret.add(tb2);
        }
        return ret;
    }
    @Transactional
    public void updateTimeblock(TimeblockDto timeblockDto, Long timeblockId){
        //kontrola, či je zadaný čas správne, a či učiteľ, trieda, alebo učebňa nie sú v danom čase obsadené
        TimeblockCheck(timeblockDto);
        Optional<TimeblockEntity> tbe = timeblockRepository.findById(timeblockId);
        if(tbe.isPresent()){
            Optional<TeacherEntity> teacherById = teacherRepository.findById(timeblockDto.getTeacherId());
            if(teacherById.isPresent()){
                tbe.get().setTeacher(teacherById.get());
            } else throw new RuntimeException("zadané id pre učiteľa neexistuje");
            Optional<ClassroomEntity> classroomById = classroomRepository.findById(timeblockDto.getClassroomId());
            if(classroomById.isPresent()){
                tbe.get().setClassroom(classroomById.get());
            } else throw new RuntimeException("zadané id pre Učebňu neexistuje");
            Optional<SubjectEntity> subjectById = subjectRepository.findById(timeblockDto.getSubjectId());
            if(subjectById.isPresent()){
                tbe.get().setSubject(subjectById.get());
            } else throw new RuntimeException("zadané id pre predmet neexistuje");
            Optional<GroupEntity> groupById = groupRepository.findById(timeblockDto.getGroupId());
            if (groupById.isPresent()) {
                tbe.get().setGroup(groupById.get());
            } else throw new RuntimeException("zadané id pre triedu neexistuje");
            tbe.get().setDay(timeblockDto.getDay());
            tbe.get().setStart(timeblockDto.getStart());
            tbe.get().setFinish(timeblockDto.getFinish());
        }

    }
    @Transactional
    public void deleteTimeblock(Long timeblockId){
        timeblockRepository.deleteById(timeblockId);
    }


    //funkcia vráti zoznam všetkých blokov v rozvrhu pre deň, ktorý je zadaný ako parameter funkcie
    @Transactional
    public List<TimeblockDto> getTimeblockOfTheDay(int day){
        List<TimeblockDto> ret = new LinkedList<>();
        for(TimeblockEntity r1 : timeblockRepository.findAll()){
            TimeblockDto r2 = mapToTimeblockDto(r1);
            if(r2.getDay() == day)
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
    public void TimeblockCheck(TimeblockDto timeblockDto){
        if(!(timeblockDto.StartFinishCheck()))
            throw new RuntimeException("zle zadaný čas");
        List<TimeblockDto> ret = getTimeblockOfTheDay(timeblockDto.getDay());
        for(TimeblockDto r1 : ret) {
            if (r1.getTeacherId() == timeblockDto.getTeacherId()) {
                if (!r1.TimeCheck(timeblockDto.getStart(), timeblockDto.getFinish()))
                    throw new RuntimeException("Učiteľ je v danom čase obsadený");
            }
            if (r1.getClassroomId() == timeblockDto.getClassroomId()) {
                if (!r1.TimeCheck(timeblockDto.getStart(), timeblockDto.getFinish()))
                    throw new RuntimeException("Učebňa je v danom čase obsadená");
            }
            if (r1.getGroupId()  == timeblockDto.getGroupId()) {
                if (!r1.TimeCheck(timeblockDto.getStart(), timeblockDto.getFinish()))
                    throw new RuntimeException("Trieda v danom čase má nejakú hodinu");
            }
        }
    }

}
