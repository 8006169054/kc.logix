package kc.logix.apps.management.inventory;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InventoryController {
	
//	private final InventoryService service;
	
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
