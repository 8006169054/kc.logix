package kc.logix.apps.basic.shipping;

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
import kc.logix.apps.basic.shipping.service.ShippingService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicShipping;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShippingController {
	
	private final ShippingService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/basic/shipping")
	public ResponseEntity<BasicShipping> selectShipping(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectShipping(BasicShipping.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/shipping")
	public ResponseEntity<Void> insertShipping(@RequestBody BasicShipping paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.insertShipping(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@PatchMapping(value = "/api/basic/shipping")
	public ResponseEntity<Void> updateShipping(@RequestBody BasicShipping paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.updateShipping(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getUpdateMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@DeleteMapping(value = "/api/basic/shipping")
	public ResponseEntity<Void> deleteShipping(@RequestBody List<BasicShipping> paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.deleteShipping(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getDeleteMessage(KainosResponseEntity.builder().build()).close();
	}
}
