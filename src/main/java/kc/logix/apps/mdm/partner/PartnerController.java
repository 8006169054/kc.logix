package kc.logix.apps.mdm.partner;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.lang.KainosBusinessException;
import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import kc.logix.apps.mdm.partner.dto.PartnerDto;
import kc.logix.apps.mdm.partner.service.PartnerService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PartnerController {
	
	private final PartnerService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/mdm/partner")
	public ResponseEntity<PartnerDto> selectPartner(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectPartner(PartnerDto.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/mdm/partner")
	public ResponseEntity<Void> savePartner(@RequestBody List<PartnerDto> paramList, @KainosSession SessionDto session) throws Exception {
		try {
			service.savePartner(paramList, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
}
