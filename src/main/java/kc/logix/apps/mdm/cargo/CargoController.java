package kc.logix.apps.mdm.cargo;

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
import kc.logix.apps.mdm.cargo.dto.CargoDto;
import kc.logix.apps.mdm.cargo.service.CargoService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CargoController {
	
	private final CargoService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/mdm/cargo")
	public ResponseEntity<CargoDto> selectCargo(
			@RequestParam(required = false) String name
			) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectCargo(CargoDto.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/mdm/cargo")
	public ResponseEntity<Void> saveCargo(@RequestBody List<CargoDto> paramList, @KainosSession SessionDto session) throws Exception {
		try {
			service.saveCargo(paramList, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}

}
