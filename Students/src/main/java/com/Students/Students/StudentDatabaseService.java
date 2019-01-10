package com.Students.Students;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
@Component
public class StudentDatabaseService {
	private static Map<Long,StudentV1> studentMap = new HashMap<>();
    static{
    	studentMap.put((long) 1, new StudentV1(1L,"Anurag",new Date(1993, 8, 19)));
    	studentMap.put((long)2, new StudentV1(2L,"Mr. Jitesh",new Date(1998, 1, 27)));
    	studentMap.put((long)3, new StudentV1(3L,"Abhishek",new Date(1993, 1, 7)));
    }
    public List<StudentV1> findAll(){
        return studentMap.values().stream().collect(Collectors.toList());
    }

    public StudentV1 findOne(long id){
        return studentMap.get(id);
    }

    public StudentV1 save(StudentV1 student){
        if(!Optional.ofNullable(studentMap.get(student.getStudentId())).isPresent()){
        	studentMap.put(student.getStudentId(),student);
            return student;
        }
        else{
            return null;
        }
    }

    public StudentV1 update(Long studentId,StudentV1 student){
        if(studentMap.containsKey(studentId)){
            return studentMap.put(studentId, student);
        }
        else{
            return null;
        }
    }
    public StudentV1 deleteById(Long id){
        if(studentMap.containsKey(id)){
            StudentV1 student = studentMap.get(id);
            studentMap.remove(id);
            return student;
        }
        else{
            return null;
        }
    }
}