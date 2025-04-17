package kc.logix.apps.basic.port;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kainos.framework.core.lang.KainosBusinessException;
import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import kainos.framework.core.support.jqgrid.RowSpenHandler;
import kc.logix.apps.basic.port.dto.PostExcelReadDto;
import kc.logix.apps.basic.port.service.PortService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicPort;
import kc.logix.common.util.MessageUtil;
import kc.logix.common.util.excel.KainosExcelReadHandler;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PortController {
	
	private final PortService service;
	private final MessageUtil message;
	private final RowSpenHandler handler;
	
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
	
	@PostMapping(value = "/api/basic/excel-upload")
	public ResponseEntity<PostExcelReadDto> excelupload(@RequestPart MultipartFile upload) throws Exception {
		List<PostExcelReadDto> excelData = new ArrayList<>();
		try {       
			/* 클라이언트에서 넘어온 MultipartFile 객체 */
			KainosExcelReadHandler excelReadHandler = KainosExcelReadHandler.builder().startRowNum(5) // 엑셀파일 데이터 시작 로우
					.excel(upload.getInputStream()) // MultipartFile 객체
					.rowSpan(true)
					.build(); // 객체 생성
			excelReadHandler.readExcel() // 엑셀 파일 읽기
					.getRows() // 데이터 get List
					.forEach(dataRow -> {
						try {
							/* 주의 엑셀 파일에 빈 데이터 체크 필요 */
							excelData.add(excelReadHandler.objectCoyp(dataRow, PostExcelReadDto.class));
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					});
			excelReadHandler.rowSapnCoyp(excelData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KainosBusinessException("common.system.error");
		}
		return KainosResponseEntity.builder().build()
				.addData(handler.GenerationRowSpen(excelData, PostExcelReadDto.class))
				.close();
	}
}
