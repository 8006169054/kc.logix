package kc.logix.apps.partner.home;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import kc.logix.apps.management.website.dto.WebsiteDto;
import kc.logix.apps.partner.home.dto.HomeDto;
import kc.logix.apps.partner.home.service.HomeService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.util.excel.GridRowSpenHandler;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService service;
	private final GridRowSpenHandler handler;
	
	@GetMapping(value = "/api/partner/home/website-terminal-code")
	public ResponseEntity<HomeDto> selectWebsiteTerminalCode(
			@RequestParam(required = false) String hblNo, @KainosSession SessionDto session
		) throws Exception {
		List<HomeDto> PortList = service.selectWebsiteTerminalCode(HomeDto.builder().hblNo(hblNo).partner(session.getPartnerCode()).build());
		return KainosResponseEntity.builder().build()
				.addData(handler.GenerationRowSpen(PortList, WebsiteDto.class))
				.close();
	}
	
	@GetMapping(value = "/api/partner/home/website-terminal-code-grid-col")
	public ResponseEntity<String> selectWebsiteTerminalCodeGridCol(@KainosSession SessionDto session) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectWebsiteTerminalCodeGridCol(session.getUserId()))
				.close();
	}
}
