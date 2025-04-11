package kc.logix.apps.basic.port.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kainos.framework.core.lang.KainosBusinessException;
import kc.logix.apps.basic.port.repository.PortRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicPort;
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
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void insertPort(BasicPort paramDto, SessionDto session)throws Exception {
		if(repository.selectPort(paramDto, true).size() > 0) {
			throw new KainosBusinessException("basic.port.insert.duplicated");
		}
		else {
			paramDto.setUpdateUserId(session.getUserName());
			repository.insertPort(paramDto);
		}
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void updatePort(BasicPort paramDto, SessionDto session)throws Exception {
		paramDto.setUpdateUserId(session.getUserName());
		repository.updatePort(paramDto);
	}
	
	/**
	 * 
	 * @param paramDtos
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void deletePort(List<BasicPort> paramDtos, SessionDto session)throws Exception {
		for (int i = 0; i < paramDtos.size(); i++) {
			BasicPort paramDto = paramDtos.get(i);
			paramDto.setUpdateUserId(session.getUserName());
			repository.deletePort(paramDto);
		}
		
	}
}
