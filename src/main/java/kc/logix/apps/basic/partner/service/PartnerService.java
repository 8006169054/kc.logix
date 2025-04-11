package kc.logix.apps.basic.partner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kainos.framework.core.lang.KainosBusinessException;
import kc.logix.apps.basic.partner.repository.PartnerRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicPartner;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerService {

	private final PartnerRepository repository;

	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<BasicPartner> selectPartner(BasicPartner paramDto) throws Exception {
		return repository.selectPartner(paramDto, false);
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void insertPartner(BasicPartner paramDto, SessionDto session)throws Exception {
		if(repository.selectPartner(paramDto, true).size() > 0) {
			throw new KainosBusinessException("basic.partner.insert.duplicated");
		}
		else {
			paramDto.setUpdateUserId(session.getUserName());
			repository.insertPartner(paramDto);
		}
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void updatePartner(BasicPartner paramDto, SessionDto session)throws Exception {
		paramDto.setUpdateUserId(session.getUserName());
		repository.updatePartner(paramDto);
	}
	
	/**
	 * 
	 * @param paramDtos
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void deletePartner(List<BasicPartner> paramDtos, SessionDto session)throws Exception {
		for (int i = 0; i < paramDtos.size(); i++) {
			BasicPartner paramDto = paramDtos.get(i);
			paramDto.setUpdateUserId(session.getUserName());
			repository.deletePartner(paramDto);
		}
		
	}
	
}
