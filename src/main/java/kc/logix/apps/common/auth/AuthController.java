package kc.logix.apps.common.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import kc.logix.apps.common.auth.service.AuthService;
import kc.logix.common.dto.SessionDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService service;
	
	@GetMapping(value = "/open/dblogin")
	public ResponseEntity<SessionDto> dbLogin(@RequestParam(required = true) String id, @RequestParam(required = true) String password) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.dbLogin(id, password))
				.close();
	}
	
	@GetMapping(value = "/api/logout")
	public ResponseEntity<Void> logout() throws Exception {
		service.logout();
		return KainosResponseEntity.noneData();
	}
}
