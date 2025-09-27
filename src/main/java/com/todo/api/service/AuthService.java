package com.todo.api.service;

import com.todo.api.dtos.requestDto.LoginReq;
import com.todo.api.dtos.requestDto.RegisterReq;
import com.todo.api.dtos.responseDto.LoginRes;
import com.todo.api.dtos.responseDto.RegisterRes;

public interface AuthService {

      RegisterRes register(RegisterReq body);

      LoginRes login(LoginReq body);
}
