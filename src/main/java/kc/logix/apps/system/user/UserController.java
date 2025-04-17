package kc.logix.apps.system.user;

import org.springframework.web.bind.annotation.RestController;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	
//	private final UserService service;
	
//	@GetMapping(value = "/open/dblogin")
//	public ResponseEntity<SessionDto> dbLogin(@RequestParam(required = true) String id, @RequestParam(required = true) String password) throws Exception {
//		return KainosResponseEntity.builder().build()
//				.addData(service.dbLogin(id, password))
//				.close();
//	}
//	
//	@GetMapping(value = "/api/logout")
//	public ResponseEntity<Void> logout() throws Exception {
//		service.logout();
//		return KainosResponseEntity.noneData();
//	}
	

	
//	@PostMapping(value = "/open/excel-upload")
//	public ResponseEntity<?> excelupload(@RequestPart MultipartFile upload) throws Exception {
//		try {       
//	        List<ExcelReadDto> excelData = new ArrayList<>();
//			/* 클라이언트에서 넘어온 MultipartFile 객체 */
//			KainosExcelReadHandler excelReadHandler = KainosExcelReadHandler.builder().startRowNum(5) // 엑셀파일 데이터 시작 로우
//					.excel(upload.getInputStream()) // MultipartFile 객체
//					.rowSpan(true)
//					.build(); // 객체 생성
//			excelReadHandler.readExcel() // 엑셀 파일 읽기
//					.getRows() // 데이터 get List
//					.forEach(dataRow -> {
//						try {
//							/* 주의 엑셀 파일에 빈 데이터 체크 필요 */
//							excelData.add(excelReadHandler.objectCoyp(dataRow, ExcelReadDto.class));
//						} catch (Exception e) {
//							throw new RuntimeException(e);
//						}
//					});
//			excelReadHandler.rowSapnCoyp(excelData);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new KainosBusinessException("common.system.error");
//		}
//		return KainosResponseEntity.noneData();
//	}
}
