package kc.logix.apps.basic.shipping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kainos.framework.core.lang.KainosBusinessException;
import kc.logix.apps.basic.shipping.repository.ShippingRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicShipping;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingService {

	private final ShippingRepository repository;
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<BasicShipping> selectShipping(BasicShipping paramDto) throws Exception {
		return repository.selectShipping(paramDto, false);
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void insertShipping(BasicShipping paramDto, SessionDto session)throws Exception {
		if(repository.selectShipping(paramDto, true).size() > 0) {
			throw new KainosBusinessException("basic.shipping.insert.duplicated");
		}
		else {
			paramDto.setUpdateUserId(session.getUserName());
			repository.insertShipping(paramDto);
		}
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void updateShipping(BasicShipping paramDto, SessionDto session)throws Exception {
		paramDto.setUpdateUserId(session.getUserName());
		repository.updateShipping(paramDto);
	}
	
	/**
	 * 
	 * @param paramDtos
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void deleteShipping(List<BasicShipping> paramDtos, SessionDto session)throws Exception {
		for (int i = 0; i < paramDtos.size(); i++) {
			BasicShipping paramDto = paramDtos.get(i);
			paramDto.setUpdateUserId(session.getUserName());
			repository.deleteShipping(paramDto);
		}
		
	}
}
