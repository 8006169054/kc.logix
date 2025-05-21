package kc.logix.apps.management.website.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kc.logix.apps.management.website.dto.WebsiteDto;
import kc.logix.apps.management.website.repository.WebsiteRepository;
import kc.logix.apps.mdm.cargo.repository.CargoRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.util.JqFlag;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebsiteService {

	private final WebsiteRepository repository;
	private final CargoRepository cargrepository;
	
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<WebsiteDto> selectWebsiteTerminalCode(WebsiteDto paramDto) throws Exception {
		return repository.selectWebsiteTerminalCode(paramDto);
	}
	
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void uploadPort(List<WebsiteDto> paramList, SessionDto session)throws Exception {
		String hbl = null;
		for (int i = 0; i < paramList.size(); i++) {
			WebsiteDto dto = paramList.get(i);
			if(hbl == null || !dto.getHblNo().equals(hbl)) {
				// BL No 삭제
				hbl = dto.getHblNo();
				repository.excelUploadHblNoDelete(dto.getHblNo());
			}
			dto.setCreateUserId(session.getUserId());
			dto.setUpdateUserId(session.getUserId());
			if(dto.getJqFlag().equalsIgnoreCase(JqFlag.Insert)) {
				repository.insertWebsiteTerminalCode(dto);
			}
		}
	}
	
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void savePort(List<WebsiteDto> paramList, SessionDto session)throws Exception {
		for (int i = 0; i < paramList.size(); i++) {
			WebsiteDto dto = paramList.get(i);
			dto.setUpdateUserId(session.getUserId());
//			if(dto.getItem().indexOf("|") > 0) {
//				String[] items = dto.getItem().split("|");
//				dto.setItem(cargrepository.selectCargoCode(items[0], items[1], items[2]));
//			}
//			else {
//				dto.setItem(cargrepository.selectCargoCode(dto.getItem(), null, null));
//			}
			if(dto.getJqFlag().equalsIgnoreCase(JqFlag.Insert)) {
				repository.insertWebsiteTerminalCode(dto);
			} else if(dto.getJqFlag().equalsIgnoreCase(JqFlag.Update)) {
				repository.updateWebsiteTerminalCode(dto);
			} else if(dto.getJqFlag().equalsIgnoreCase(JqFlag.Delete)) {
				repository.deleteWebsiteTerminalCode(dto.getUuid());
			}
		}
	}
	
}
