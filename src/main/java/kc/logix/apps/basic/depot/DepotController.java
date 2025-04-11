package kc.logix.apps.basic.depot;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.lang.KainosBusinessException;
import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import kc.logix.apps.basic.depot.service.DepotService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicDepot;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DepotController {
	
	private final DepotService service;
private final MessageUtil message;
	
	@GetMapping(value = "/api/basic/depot")
	public ResponseEntity<BasicDepot> selectDepot(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectDepot(BasicDepot.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/depot")
	public ResponseEntity<Void> insertDepot(@RequestBody BasicDepot paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.insertDepot(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@PatchMapping(value = "/api/basic/depot")
	public ResponseEntity<Void> updateDepot(@RequestBody BasicDepot paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.updateDepot(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getUpdateMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@DeleteMapping(value = "/api/basic/depot")
	public ResponseEntity<Void> deleteDepot(@RequestBody List<BasicDepot> paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.deleteDepot(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getDeleteMessage(KainosResponseEntity.builder().build()).close();
	}
}
