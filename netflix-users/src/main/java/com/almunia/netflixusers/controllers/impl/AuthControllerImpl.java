package com.almunia.netflixusers.controllers.impl;



import com.almunia.netflixusers.controllers.AuthController;
import com.almunia.netflixusers.dto.NetflixDto;
import com.almunia.netflixusers.payload.request.LoginRequest;
import com.almunia.netflixusers.payload.request.SignupRequest;
import com.almunia.netflixusers.payload.response.JwtResponse;
import com.almunia.netflixusers.payload.response.MessageResponse;
import com.almunia.netflixusers.services.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {

  @Autowired
  private AuthServiceImpl authService;

    /**
     * Returns a token
     *
     * @param loginRequest loginRequest
     * @return NetflixResponse with the token
     * @Example localhost:8088/netflix/v1/auth/signin
     */
  @PostMapping("/signin")
  public NetflixDto<JwtResponse> signin(@Validated @RequestBody LoginRequest loginRequest) {
      return authService.authenticateUser(loginRequest);
  }

    /**
     * Returns a message when the user is registered successfully or not successfully (if the username or email is already taken)
     *
     * @param signUpRequest signUpRequest
     * @return NetflixResponse with the message
     * @Example localhost:8088/netflix/v1/auth/signup
     */
  @PostMapping("/signup")
  public NetflixDto<MessageResponse> signup(@Validated @RequestBody SignupRequest signUpRequest) {
      return authService.registerUser(signUpRequest);
  }
}
