package kc.logix.apps.basic.port;

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
import kc.logix.apps.basic.port.service.PortService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicPort;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PortController {
	
	private final PortService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/basic/port")
	public ResponseEntity<BasicPort> selectPort(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectPort(BasicPort.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/port")
	public ResponseEntity<Void> insertPort(@RequestBody BasicPort paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.insertPort(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@PatchMapping(value = "/api/basic/port")
	public ResponseEntity<Void> updatePort(@RequestBody BasicPort paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.updatePort(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getUpdateMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@DeleteMapping(value = "/api/basic/port")
	public ResponseEntity<Void> deletePort(@RequestBody List<BasicPort> paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.deletePort(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getDeleteMessage(KainosResponseEntity.builder().build()).close();
	}
}
