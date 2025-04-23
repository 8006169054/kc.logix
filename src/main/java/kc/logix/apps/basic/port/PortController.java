package kc.logix.apps.basic.port;

import java.util.ArrayList;
import java.util.Iterator;
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
import kc.logix.apps.basic.port.dto.PortDto;
import kc.logix.apps.basic.port.dto.PostExcelReadDto;
import kc.logix.apps.basic.port.service.PortService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicPort;
import kc.logix.common.util.MessageUtil;
import kc.logix.common.util.excel.GridRowSpenHandler;
import kc.logix.common.util.excel.KainosExcelReadHandler;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PortController {
	
	private final PortService service;
	private final MessageUtil message;
	private final GridRowSpenHandler handler;
	
	@GetMapping(value = "/api/basic/port")
	public ResponseEntity<BasicPort> selectPort(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectPort(BasicPort.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/save-port")
	public ResponseEntity<Void> savePort(@RequestBody List<PortDto> paramList, @KainosSession SessionDto session) throws Exception {
		try {
			service.savePort(paramList, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
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
			
			
			/* 필수 처리 */
			excelReadHandler.objectCoypClose();
			excelReadHandler.rowSapnCoyp(excelData);
			excelReadHandler.customFunctionCall(excelData);
			
			List<PostExcelReadDto> removeIndex = new ArrayList<> ();
			for (int i = 0; i < excelData.size(); i++)
				if(excelData.get(i).getHblNo() == null || excelData.get(i).getHblNo().equalsIgnoreCase("")) removeIndex.add(excelData.get(i));
			
			if(removeIndex.size() > 0) excelData.removeAll(removeIndex);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new KainosBusinessException("common.system.error");
		}
		return KainosResponseEntity.builder().build()
				.addData(handler.GenerationRowSpen(excelData, PostExcelReadDto.class))
				.close();
	}
}
