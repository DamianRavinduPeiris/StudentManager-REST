package com.damian.starter.service;

import com.damian.starter.dto.Super_DTO;
import com.damian.starter.response.Response;

public interface SuperService <T extends Super_DTO,ID>{
    Response add(T t);
    Response update(T t);
    Response search(ID id);
    Response delete(ID id);
    Response getAll();
}
