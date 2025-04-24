package kc.logix.apps.basic.terminal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kainos.framework.core.lang.KainosBusinessException;
import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import kc.logix.apps.basic.terminal.dto.TerminalDto;
import kc.logix.apps.basic.terminal.service.TerminalService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicShipping;
import kc.logix.common.util.MessageUtil;
import kc.logix.common.util.excel.KainosExcelReadHandler;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TerminalController {
	
	private final TerminalService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/basic/terminal")
	public ResponseEntity<BasicShipping> selectTerminal(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectTerminal(TerminalDto.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/terminal")
	public ResponseEntity<Void> saveTerminal(@RequestBody List<TerminalDto> paramList, @KainosSession SessionDto session) throws Exception {
		try {
			service.saveTerminal(paramList, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@PostMapping(value = "/api/basic/terminal/upload")
	public ResponseEntity<Void> excelupload(@RequestPart MultipartFile upload, @KainosSession SessionDto session) throws Exception {
		List<TerminalDto> excelData = new ArrayList<>();
		try {       
			/* 클라이언트에서 넘어온 MultipartFile 객체 */
			KainosExcelReadHandler excelReadHandler = KainosExcelReadHandler.builder().startRowNum(1) // 엑셀파일 데이터 시작 로우
					.excel(upload.getInputStream()) // MultipartFile 객체
					.build(); // 객체 생성
			excelReadHandler.readExcel() // 엑셀 파일 읽기
					.getRows() // 데이터 get List
					.forEach(dataRow -> {
						try {
							/* 주의 엑셀 파일에 빈 데이터 체크 필요 */
							excelData.add(excelReadHandler.objectCoyp(dataRow, TerminalDto.class));
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					});
			
			service.excelupload(excelData, session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
}
