package com.damian.starter.service.impl;

import com.damian.starter.dto.Student_DTO;
import com.damian.starter.entity.Student;
import com.damian.starter.repo.StudentRepo;
import com.damian.starter.response.Response;
import com.damian.starter.service.custom.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private Response response;

    @Override
    public Response add(Student_DTO studentDto) {
        if (search(studentDto.getStudentId()).getData() != null) {
            throw new RuntimeException("Student already exists!");
        }
        studentRepo.save(mapper.map(studentDto, Student.class));
        return createAndSendResponse(HttpStatus.CREATED.value(), "Student saved successfully!", null);

    }

    @Override
    public Response update(Student_DTO studentDto) {
        if(search(studentDto.getStudentId()).getData()!=null){
            studentRepo.save(mapper.map(studentDto,Student.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Student updated successfully!",null);
        }
        throw new RuntimeException("Student does not exist!");
    }

    @Override
    public Response search(String s) {
        Optional<Student> student = studentRepo.findById(s);
        if (student.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "Student Retrieved successfully!", student.get());
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Student not found! ", null);

    }

    @Override
    public Response delete(String s) {
        if(search(s).getData()!=null){
            studentRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Student deleted successfully!", null);
        }
        throw new RuntimeException("Student does not exist!");
    }

    @Override
    public Response getAll() {
        List<Student> students = studentRepo.findAll();
        ArrayList<Student_DTO> studentDtos = new ArrayList<>();
        if(!students.isEmpty()){
            students.forEach((student)->{
                studentDtos.add(mapper.map(student,Student_DTO.class));
            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "Students retrieved successfully!",studentDtos);
        }
        throw new RuntimeException("No students found!");
    }


    @Override
    public Response createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
