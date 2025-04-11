package kc.logix.apps.basic.partner;

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
import kc.logix.apps.basic.partner.service.PartnerService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicPartner;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PartnerController {
	
	private final PartnerService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/basic/partner")
	public ResponseEntity<BasicPartner> selectPartner(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectPartner(BasicPartner.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/partner")
	public ResponseEntity<Void> insertPartner(@RequestBody BasicPartner paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.insertPartner(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@PatchMapping(value = "/api/basic/partner")
	public ResponseEntity<Void> updatePartner(@RequestBody BasicPartner paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.updatePartner(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getUpdateMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@DeleteMapping(value = "/api/basic/partner")
	public ResponseEntity<Void> deletePartner(@RequestBody List<BasicPartner> paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.deletePartner(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getDeleteMessage(KainosResponseEntity.builder().build()).close();
	}
}
