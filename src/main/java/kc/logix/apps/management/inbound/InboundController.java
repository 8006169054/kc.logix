package kc.logix.apps.management.inbound;

import org.springframework.web.bind.annotation.RestController;

import kc.logix.apps.management.inbound.service.InboundService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InboundController {
	
	private final InboundService service;
	
//	@GetMapping(value = "/open/dblogin")
//	public ResponseEntity<SessionDto> dbLogin(@RequestParam(required = true) String id, @RequestParam(required = true) String password) throws Exception {
//		return KainosResponseEntity.builder().build()
//				.addData(service.dbLogin(id, password))
//				.close();
//	}
//	
//	@GetMapping(value = "/api/logout")
//	public ResponseEntity<Void> logout() throws Exception {
//		service.logout();
//		return KainosResponseEntity.noneData();
//	}
}
