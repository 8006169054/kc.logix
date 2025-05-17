package kc.logix.apps.basic.port.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kc.logix.apps.basic.port.dto.PortDto;
import kc.logix.apps.basic.port.repository.PortRepository;
import kc.logix.apps.mdm.cargo.repository.CargoRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.util.JqFlag;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortService {

	private final PortRepository repository;
	private final CargoRepository cargrepository;
	
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<PortDto> selectWebsiteTerminalCode(PortDto paramDto) throws Exception {
		return repository.selectWebsiteTerminalCode(paramDto);
	}
	
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void uploadPort(List<PortDto> paramList, SessionDto session)throws Exception {
		String hbl = null;
		for (int i = 0; i < paramList.size(); i++) {
			PortDto dto = paramList.get(i);
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
	public void savePort(List<PortDto> paramList, SessionDto session)throws Exception {
		for (int i = 0; i < paramList.size(); i++) {
			PortDto dto = paramList.get(i);
			dto.setUpdateUserId(session.getUserId());
			dto.setItem(cargrepository.selectCargoCode(dto.getItem()));
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
