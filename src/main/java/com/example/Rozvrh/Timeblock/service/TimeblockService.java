package com.example.Rozvrh.Timeblock.service;

import com.example.Rozvrh.Group.GroupEntity;
import com.example.Rozvrh.Group.GroupRepository;
import com.example.Rozvrh.Subject.SubjectEntity;
import com.example.Rozvrh.Subject.SubjectRepository;
import com.example.Rozvrh.Classroom.ClassroomEntity;
import com.example.Rozvrh.Classroom.ClassroomRepository;
import com.example.Rozvrh.Teacher.TeacherEntity;
import com.example.Rozvrh.Teacher.TeacherRepository;
import com.example.Rozvrh.Timeblock.TimeblockEntity;
import com.example.Rozvrh.Timeblock.TimeblockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
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
        timeblockDto.setStart(timeblockEntity.getStart().toString());
        timeblockDto.setFinish(timeblockEntity.getFinish().toString());
        timeblockDto.setId(timeblockEntity.getId());

        return timeblockDto;
    }
    /*
    @Transactional
    public String createTimeblock(TimeblockDto timeblockDto){
        //kontrola, či je zadaný čas správne, a či učiteľ, trieda, alebo učebňa nie sú v danom čase obsadené
        String errorMessage = TimeblockCheck(timeblockDto);
        if(errorMessage != null)
            return errorMessage;
        TimeblockEntity tbe = new TimeblockEntity();
        //priraďovanie id pre učiteľa, predmet a učebňu, ak sa id v databáze nenachádza, vypíše sa chyba, aby sa nepridali
        // do databázy id s null
        Optional<TeacherEntity> teacherById = teacherRepository.findById(timeblockDto.getTeacherId());
        if (teacherById.isPresent()) {
            tbe.setTeacher(teacherById.get());
        } else return "zadané id pre učiteľa neexistuje";

        Optional<ClassroomEntity> ClassroomById = classroomRepository.findById(timeblockDto.getClassroomId());
        if (ClassroomById.isPresent()) {
            tbe.setClassroom(ClassroomById.get());
        } else return "zadané id pre učebňu neexistuje";

        Optional<SubjectEntity> subjectById = subjectRepository.findById(timeblockDto.getSubjectId());
        if (subjectById.isPresent()) {
            tbe.setSubject(subjectById.get());
        } else return "zadané id pre predmet neexistuje";

        Optional<GroupEntity> groupById = groupRepository.findById(timeblockDto.getGroupId());
        if (groupById.isPresent()) {
            tbe.setGroup(groupById.get());
        } else return "zadané id pre triedu neexistuje";

        tbe.setDay(timeblockDto.getDay());
        //dto obsahuje casove premenne ako string preto pri pridavani entity je potrebne zmenit string na LocalTime
        tbe.setStart(LocalTime.parse(timeblockDto.getStart()));
        tbe.setFinish(LocalTime.parse(timeblockDto.getFinish()));

        this.timeblockRepository.save(tbe);
        return tbe.getId().toString();
    }*/

    @Transactional
    public String createTimeblock(TimeblockCreateDto timeblockCreateDto){
        //kontrola, či je zadaný čas správne, a či učiteľ, trieda, alebo učebňa nie sú v danom čase obsadené
        String errorMessage = TimeblockCheck(timeblockCreateDto);
        if(errorMessage != null)
            return errorMessage;
        TimeblockEntity tbe = new TimeblockEntity();
        Optional<TeacherEntity> teacherById = teacherRepository.findById(timeblockCreateDto.getTeacher().getId());
        if (teacherById.isPresent()) {
            tbe.setTeacher(teacherById.get());
        }

        Optional<ClassroomEntity> ClassroomById = classroomRepository.findById(timeblockCreateDto.getClassroom().getId());
        if (ClassroomById.isPresent()) {
            tbe.setClassroom(ClassroomById.get());
        }

        Optional<SubjectEntity> subjectById = subjectRepository.findById(timeblockCreateDto.getSubject().getId());
        if (subjectById.isPresent()) {
            tbe.setSubject(subjectById.get());
        }

        Optional<GroupEntity> groupById = groupRepository.findById(timeblockCreateDto.getGroup().getId());
        if (groupById.isPresent()) {
            tbe.setGroup(groupById.get());
        }
        tbe.setDay(timeblockCreateDto.getDay());
        //dto obsahuje casove premenne ako string preto pri pridavani entity je potrebne zmenit string na LocalTime
        tbe.setStart(LocalTime.parse(timeblockCreateDto.getStart()));
        tbe.setFinish(LocalTime.parse(timeblockCreateDto.getFinish()));

        this.timeblockRepository.save(tbe);
        return tbe.getId().toString();
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
    public List<TimeblockDto> getTimeblocks(String name, String type){
        System.out.println(name + " " + type);
        List<TimeblockDto> ret = new LinkedList<>();
        for(TimeblockEntity tb1 : timeblockRepository.findAll()){
            TimeblockDto tb2 = mapToTimeblockDto(tb1);
            if(name == null){
                ret.add(tb2);
            }else{
                if(type.equals("teacher") && name.equals(tb2.getTeacher())){
                    ret.add(tb2);
                }
                if(type.equals("group") && name.equals(tb2.getGroup())){
                    ret.add(tb2);
                }
                if(type.equals("classroom") && name.equals(tb2.getClassroom())){
                    ret.add(tb2);
                }
            }



        }
        return ret;
    }
    @Transactional
    public String updateTimeblock(TimeblockCreateDto timeblockCreateDto, Long timeblockId){

        Optional<TimeblockEntity> tbe = timeblockRepository.findById(timeblockId);
        if(tbe.isPresent()) {
            timeblockCreateDto.setId(timeblockId);
            //kontrola, či je zadaný čas správne, a či učiteľ, trieda, alebo učebňa nie sú v danom čase obsadené
            String errorMessage = TimeblockCheck(timeblockCreateDto);
            if(errorMessage != null)
                return errorMessage;
            Optional<TeacherEntity> teacherById = teacherRepository.findById(timeblockCreateDto.getTeacher().getId());
            if (teacherById.isPresent()) {
                tbe.get().setTeacher(teacherById.get());
            }

            Optional<ClassroomEntity> ClassroomById = classroomRepository.findById(timeblockCreateDto.getClassroom().getId());
            if (ClassroomById.isPresent()) {
                tbe.get().setClassroom(ClassroomById.get());
            }

            Optional<SubjectEntity> subjectById = subjectRepository.findById(timeblockCreateDto.getSubject().getId());
            if (subjectById.isPresent()) {
                tbe.get().setSubject(subjectById.get());
            }

            Optional<GroupEntity> groupById = groupRepository.findById(timeblockCreateDto.getGroup().getId());
            if (groupById.isPresent()) {
                tbe.get().setGroup(groupById.get());
            }
            tbe.get().setDay(timeblockCreateDto.getDay());
            //dto obsahuje casove premenne ako string preto pri pridavani entity je potrebne zmenit string na LocalTime
            tbe.get().setStart(LocalTime.parse(timeblockCreateDto.getStart()));
            tbe.get().setFinish(LocalTime.parse(timeblockCreateDto.getFinish()));
            return tbe.get().getId().toString();
        } else return "Zle id";

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


    //kontrola, ci cas zaciatku hodiny nie je az po jej konci
    public boolean StartFinishCheck(TimeblockCreateDto timeblockCreateDto){
        LocalTime st = LocalTime.parse(timeblockCreateDto.getStart());
        LocalTime fin = LocalTime.parse(timeblockCreateDto.getFinish());
        if(st.isAfter(fin))
            return false;
        return true;
    }

    //kontrola, ci hodina, ktoru pridavame nie je v kolizii, s nejakou inou hodinou, ktoru posielame ako druhy parameter
    public boolean TimeCheck(TimeblockCreateDto timeblockCreateDto, TimeblockDto checktimeblockDto){
        LocalTime st = LocalTime.parse(timeblockCreateDto.getStart());
        LocalTime fin = LocalTime.parse(timeblockCreateDto.getFinish());
        LocalTime checkSt = LocalTime.parse(checktimeblockDto.getStart());
        LocalTime checFin = LocalTime.parse(checktimeblockDto.getFinish());

        //ak uz je skontrolovane na Dto ,ci zaciatok hodiny nie je pred jej koncom, tak staci skontrolovat,
        // ci koniec hodiny, kt. pridavame je pred zaciatkom tej, s ktorou ju porovnavame, ak ano tak je to v poriadku
        // a tak isto ak je jej zaciatok po skonceni hodiny, s ktorou porovnavame, je to v poriadku.
        if(fin.isBefore(checkSt))
            return true;
        if(st.isAfter(checFin))
            return true;
        return false;
    }
    //kontrola, či učiteľ, učebňa alebo trieda v danom čase a daný deň už nie sú obsadené, ak sú tak throw new Runtimeexception
    //funkcia sa bude volať pri create alebo update
    public String TimeblockCheck(TimeblockCreateDto timeblockCreateDto){
        if(!StartFinishCheck(timeblockCreateDto))
            return "zle zadaný čas";
        List<TimeblockDto> ret = getTimeblockOfTheDay(timeblockCreateDto.getDay());

        for(TimeblockDto r1 : ret) {
            if(r1.getId() != timeblockCreateDto.getId()){
                if (r1.getTeacherId() == timeblockCreateDto.getTeacher().getId()) {
                    if (!TimeCheck(timeblockCreateDto,r1))
                        return"Učiteľ je v danom čase obsadený";
                }
                if (r1.getClassroomId() == timeblockCreateDto.getClassroom().getId()) {
                    if (!TimeCheck(timeblockCreateDto,r1))
                        return "Učebňa je v danom čase obsadená";
                }
                if (r1.getGroupId()  == timeblockCreateDto.getGroup().getId()) {
                    if (!TimeCheck(timeblockCreateDto,r1))
                        return "Trieda v danom čase má nejakú hodinu";
                }
            }

        }
        return null;
    }

}