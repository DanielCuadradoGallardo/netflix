package com.almunia.netflixusers.controllers;

import com.almunia.netflixusers.dto.NetflixDto;
import com.almunia.netflixusers.payload.request.LoginRequest;
import com.almunia.netflixusers.payload.request.SignupRequest;
import com.almunia.netflixusers.payload.response.JwtResponse;
import com.almunia.netflixusers.payload.response.MessageResponse;

public interface AuthController{
    public NetflixDto<JwtResponse> signin(LoginRequest loginRequest);
    public NetflixDto<MessageResponse> signup(SignupRequest signUpRequest);
}
