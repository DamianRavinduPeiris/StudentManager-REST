package com.damian.starter.service.custom;

import com.damian.starter.dto.Student_DTO;
import com.damian.starter.response.Response;
import com.damian.starter.service.SuperService;

public interface StudentService extends SuperService<Student_DTO,String> {
    Response createAndSendResponse(int statusCode,String message,Object data);
}
