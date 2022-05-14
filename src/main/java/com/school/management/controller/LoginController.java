package com.school.management.controller;



import com.school.management.model.User;
import com.school.management.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		String key = "roles";
		List<String> roles = Arrays.asList("ADMIN", "DEVELOPER", "NORMAL");
		Map<String, List<String>> maps = new HashMap<>();
		maps.put(key, roles);
		String token = jwtTokenProvider.createToken(maps);
		System.out.println("Token: "+token);
		HttpHeaders headers = new HttpHeaders();
		headers.set("authentication", token);
		return new ResponseEntity<String>(token, headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/role_test")
	//@Secured("ROLE_DEVELOPER")
	//@PreAuthorize("hasRole('ROLE_ADMIN')") // hasRole, hasAnyRole, hasAuthority, hasAnyAuthority
	//@RolesAllowed("ROLE_NORMAL")
	public String test() {
		return "test success..";
	}
}
