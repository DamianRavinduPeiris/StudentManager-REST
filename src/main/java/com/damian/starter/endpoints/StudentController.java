package com.damian.starter.endpoints;

import com.damian.starter.dto.Student_DTO;
import com.damian.starter.response.Response;
import com.damian.starter.service.custom.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/studentManager")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/saveStudent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveStudent(@RequestBody Student_DTO studentDto) {
        return studentService.add(studentDto);

    }
    @GetMapping(path = "/search",produces = MediaType.APPLICATION_JSON_VALUE,params = "studentId")
    public Response searchStudent(@RequestParam("studentId") String studentId){
        return studentService.search(studentId);
    }


    @PutMapping(path = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateStudent(@RequestBody Student_DTO studentDto){
        return studentService.update(studentDto);

    }
    @DeleteMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_VALUE,params = "studentId")
    public Response deleteStudent(@RequestParam("studentId") String studentId){
        return studentService.delete(studentId);
    }

    @GetMapping(path = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllStudents(){
        return studentService.getAll();
    }




}
