package kc.logix.apps.basic.port.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kc.logix.apps.basic.port.dto.PortDto;
import kc.logix.apps.basic.port.repository.PortRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicPort;
import kc.logix.common.util.JqFlag;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortService {

	private final PortRepository repository;
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<BasicPort> selectPort(BasicPort paramDto) throws Exception {
		return repository.selectPort(paramDto, false);
	}
	
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void savePort(List<PortDto> paramList, SessionDto session)throws Exception {
		for (int i = 0; i < paramList.size(); i++) {
			PortDto dto = paramList.get(i);
			dto.setUserId(session.getUserId());
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
