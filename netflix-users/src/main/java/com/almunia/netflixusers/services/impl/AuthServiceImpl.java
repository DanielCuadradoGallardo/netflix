package com.almunia.netflixusers.services.impl;

import com.almunia.netflixusers.dto.NetflixDto;
import com.almunia.netflixusers.entities.ERole;
import com.almunia.netflixusers.entities.Role;
import com.almunia.netflixusers.entities.User;
import com.almunia.netflixusers.payload.request.LoginRequest;
import com.almunia.netflixusers.payload.request.SignupRequest;
import com.almunia.netflixusers.payload.response.JwtResponse;
import com.almunia.netflixusers.payload.response.MessageResponse;
import com.almunia.netflixusers.repositories.RoleRepository;
import com.almunia.netflixusers.repositories.UserRepository;
import com.almunia.netflixusers.security.jwt.JwtUtils;
import com.almunia.netflixusers.security.services.UserDetailsImpl;
import com.almunia.netflixusers.services.AuthService;

import com.almunia.netflixusers.utils.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Returns a token
     *
     * @param loginRequest loginRequest
     * @return NetflixResponse with the token
     */
    public NetflixDto<JwtResponse> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
        return new NetflixDto<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, jwtResponse);
    }

    /**
     * Returns a message when the user is registered successfully or not successfully (if the username or email is already taken)
     *
     * @param signUpRequest signUpRequest
     * @return NetflixResponse with the message
     * @throws RuntimeException if the role is not found
     */
    public NetflixDto<MessageResponse> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new NetflixDto<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), CommonConstants.BAD_REQUEST, new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new NetflixDto<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), CommonConstants.BAD_REQUEST, new MessageResponse(("Error: Email is already in use!")));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return new NetflixDto<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED), CommonConstants.CREATED, new MessageResponse(("User registered successfully!")));
    }
}
