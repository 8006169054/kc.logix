package kc.logix.apps.basic.depot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kainos.framework.core.lang.KainosBusinessException;
import kc.logix.apps.basic.depot.repository.DepotRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicDepot;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepotService {

	private final DepotRepository repository;
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<BasicDepot> selectDepot(BasicDepot paramDto) throws Exception {
		return repository.selectDepot(paramDto, false);
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void insertDepot(BasicDepot paramDto, SessionDto session)throws Exception {
		if(repository.selectDepot(paramDto, true).size() > 0) {
			throw new KainosBusinessException("basic.depot.insert.duplicated");
		}
		else {
			paramDto.setUpdateUserId(session.getUserName());
			repository.insertDepot(paramDto);
		}
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void updateDepot(BasicDepot paramDto, SessionDto session)throws Exception {
		paramDto.setUpdateUserId(session.getUserName());
		repository.updateDepot(paramDto);
	}
	
	/**
	 * 
	 * @param paramDtos
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void deleteDepot(List<BasicDepot> paramDtos, SessionDto session)throws Exception {
		for (int i = 0; i < paramDtos.size(); i++) {
			BasicDepot paramDto = paramDtos.get(i);
			paramDto.setUpdateUserId(session.getUserName());
			repository.deleteDepot(paramDto);
		}
		
	}
}
