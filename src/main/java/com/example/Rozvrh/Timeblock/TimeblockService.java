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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Transactional
    public String createTimeblock(TimeblockDto timeblockDto){
        //kontrola, ??i je zadan?? ??as spr??vne, a ??i u??ite??, trieda, alebo u??eb??a nie s?? v danom ??ase obsaden??
        String errorMessage = TimeblockCheck(timeblockDto);
        if(errorMessage != null)
            return errorMessage;
            TimeblockEntity tbe = new TimeblockEntity();
            //prira??ovanie id pre u??ite??a, predmet a u??eb??u, ak sa id v datab??ze nenach??dza, vyp????e sa chyba, aby sa nepridali
            // do datab??zy id s null
            Optional<TeacherEntity> teacherById = teacherRepository.findById(timeblockDto.getTeacherId());
            if (teacherById.isPresent()) {
                tbe.setTeacher(teacherById.get());
            } else return "zadan?? id pre u??ite??a neexistuje";

            Optional<ClassroomEntity> ClassroomById = classroomRepository.findById(timeblockDto.getClassroomId());
            if (ClassroomById.isPresent()) {
                tbe.setClassroom(ClassroomById.get());
            } else return "zadan?? id pre u??eb??u neexistuje";

            Optional<SubjectEntity> subjectById = subjectRepository.findById(timeblockDto.getSubjectId());
            if (subjectById.isPresent()) {
                tbe.setSubject(subjectById.get());
            } else return "zadan?? id pre predmet neexistuje";

            Optional<GroupEntity> groupById = groupRepository.findById(timeblockDto.getGroupId());
            if (groupById.isPresent()) {
                tbe.setGroup(groupById.get());
            } else return "zadan?? id pre triedu neexistuje";

            tbe.setDay(timeblockDto.getDay());
            //dto obsahuje casove premenne ako string preto pri pridavani entity je potrebne zmenit string na LocalTime
            tbe.setStart(LocalTime.parse(timeblockDto.getStart()));
            tbe.setFinish(LocalTime.parse(timeblockDto.getFinish()));

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
    public String updateTimeblock(TimeblockDto timeblockDto, Long timeblockId){
        //kontrola, ??i je zadan?? ??as spr??vne, a ??i u??ite??, trieda, alebo u??eb??a nie s?? v danom ??ase obsaden??
        String errorMessage = TimeblockCheck(timeblockDto);
        if(errorMessage != null)
            return errorMessage;
        Optional<TimeblockEntity> tbe = timeblockRepository.findById(timeblockId);
        if(tbe.isPresent()){
            Optional<TeacherEntity> teacherById = teacherRepository.findById(timeblockDto.getTeacherId());
            if(!teacherById.isPresent()){
                return "zadan?? id pre u??ite??a neexistuje";
            }
            Optional<ClassroomEntity> classroomById = classroomRepository.findById(timeblockDto.getClassroomId());
            if(!classroomById.isPresent()){
                return "zadan?? id pre U??eb??u neexistuje";
            }
            Optional<SubjectEntity> subjectById = subjectRepository.findById(timeblockDto.getSubjectId());
            if(!subjectById.isPresent()){
                return "zadan?? id pre predmet neexistuje";
            }
            Optional<GroupEntity> groupById = groupRepository.findById(timeblockDto.getGroupId());
            if (!groupById.isPresent()) {
                return "zadan?? id pre triedu neexistuje";
            }

            tbe.get().setTeacher(teacherById.get());
            tbe.get().setClassroom(classroomById.get());
            tbe.get().setDay(timeblockDto.getDay());
            tbe.get().setSubject(subjectById.get());
            tbe.get().setGroup(groupById.get());

            //dto obsahuje casove premenne ako string preto pri pridavani entity je potrebne zmenit string na LocalTime
            tbe.get().setStart(LocalTime.parse(timeblockDto.getStart()));
            tbe.get().setFinish(LocalTime.parse(timeblockDto.getFinish()));
            return tbe.get().getId().toString();
        }else return "Zle id";

    }
    @Transactional
    public void deleteTimeblock(Long timeblockId){
        timeblockRepository.deleteById(timeblockId);
    }


    //funkcia vr??ti zoznam v??etk??ch blokov v rozvrhu pre de??, ktor?? je zadan?? ako parameter funkcie
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
    public boolean StartFinishCheck(TimeblockDto timeblockDto){
        LocalTime st = LocalTime.parse(timeblockDto.getStart());
        LocalTime fin = LocalTime.parse(timeblockDto.getFinish());
        if(st.isAfter(fin))
            return false;
        return true;
    }

    //kontrola, ci hodina, ktoru pridavame nie je v kolizii, s nejakou inou hodinou, ktoru posielame ako druhy parameter
    public boolean TimeCheck(TimeblockDto timeblockDto, TimeblockDto checktimeblockDto){
        LocalTime st = LocalTime.parse(timeblockDto.getStart());
        LocalTime fin = LocalTime.parse(timeblockDto.getFinish());
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
    //kontrola, ??i u??ite??, u??eb??a alebo trieda v danom ??ase a dan?? de?? u?? nie s?? obsaden??, ak s?? tak throw new Runtimeexception
    //funkcia sa bude vola?? pri create alebo update
    public String TimeblockCheck(TimeblockDto timeblockDto){
        if(!StartFinishCheck(timeblockDto))
            return "zle zadan?? ??as";
        List<TimeblockDto> ret = getTimeblockOfTheDay(timeblockDto.getDay());
        for(TimeblockDto r1 : ret) {
            if (r1.getTeacherId() == timeblockDto.getTeacherId()) {
                if (!TimeCheck(timeblockDto,r1))
                    return"U??ite?? je v danom ??ase obsaden??";
            }
            if (r1.getClassroomId() == timeblockDto.getClassroomId()) {
                if (!TimeCheck(timeblockDto,r1))
                    return "U??eb??a je v danom ??ase obsaden??";
            }
            if (r1.getGroupId()  == timeblockDto.getGroupId()) {
                if (!TimeCheck(timeblockDto,r1))
                    return "Trieda v danom ??ase m?? nejak?? hodinu";
            }
        }
        return null;
    }

}
