package com.example.base.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.base.security.dto.JwtAuthenticationResponse;
import com.example.base.security.dto.LoginRequest;
import com.example.base.security.dto.SignUpRequest;
import com.example.base.security.utility.JwtTokenProvider;
import com.example.user.service.IUserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private IUserService userService;
	private JwtTokenProvider tokenProvider;

	public AuthController(AuthenticationManager authenticationManager, IUserService userService,
			JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		this.userService.signUp(signUpRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}