package com.gabriel.appoms.service;

import com.gabriel.appoms.dto.request.LoginRequest;
import com.gabriel.appoms.dto.request.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);

}