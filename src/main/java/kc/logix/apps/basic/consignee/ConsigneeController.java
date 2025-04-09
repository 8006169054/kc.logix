package kc.logix.apps.basic.consignee;

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
import kc.logix.apps.basic.consignee.service.ConsigneeService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicConsignee;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConsigneeController {
	
	private final ConsigneeService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/basic/consignee")
	public ResponseEntity<BasicConsignee> selectConsignee(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectConsignee(BasicConsignee.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/consignee")
	public ResponseEntity<Void> insertConsignee(@RequestBody BasicConsignee consigneeDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.insertConsignee(consigneeDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@PatchMapping(value = "/api/basic/consignee")
	public ResponseEntity<Void> updateConsignee(@RequestBody BasicConsignee consigneeDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.updateConsignee(consigneeDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getUpdateMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@DeleteMapping(value = "/api/basic/consignee")
	public ResponseEntity<Void> deleteConsignee(@RequestBody List<BasicConsignee> consigneeDtos, @KainosSession SessionDto session) throws Exception {
		try {
			service.deleteConsignee(consigneeDtos, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getDeleteMessage(KainosResponseEntity.builder().build()).close();
	}
	
}
