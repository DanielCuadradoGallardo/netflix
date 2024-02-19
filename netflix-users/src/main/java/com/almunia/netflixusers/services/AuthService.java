package com.almunia.netflixusers.services;

import com.almunia.netflixusers.dto.NetflixDto;
import com.almunia.netflixusers.payload.request.LoginRequest;
import com.almunia.netflixusers.payload.request.SignupRequest;
import com.almunia.netflixusers.payload.response.JwtResponse;
import com.almunia.netflixusers.payload.response.MessageResponse;

public interface AuthService {
    public NetflixDto<JwtResponse> authenticateUser(LoginRequest loginRequest);
    public NetflixDto<MessageResponse> registerUser(SignupRequest signUpRequest);
}
