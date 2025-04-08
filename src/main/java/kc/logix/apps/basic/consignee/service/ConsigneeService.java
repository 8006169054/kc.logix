package kc.logix.apps.basic.consignee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kainos.framework.core.lang.KainosBusinessException;
import kc.logix.apps.basic.consignee.repository.ConsigneeRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicConsignee;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsigneeService {

	private final ConsigneeRepository repository;

	/**
	 * 
	 * @param consigneeDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<BasicConsignee> selectConsignee(BasicConsignee consigneeDto) throws Exception {
		return repository.selectConsignee(consigneeDto, false);
	}
	
	/**
	 * 수하인 등록
	 * @param consigneeDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void insertConsignee(BasicConsignee consigneeDto, SessionDto session)throws Exception {
		if(repository.selectConsignee(consigneeDto, true).size() > 0) {
			throw new KainosBusinessException("basic.consignee.insert.duplicated");
		}
		else {
			consigneeDto.setUpdateUserId(session.getUserId());
			repository.insertConsignee(consigneeDto);
		}
	}
	
	/**
	 * 수하인 수정
	 * @param consigneeDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void updateConsignee(BasicConsignee consigneeDto, SessionDto session)throws Exception {
		consigneeDto.setUpdateUserId(session.getUserId());
		repository.updateConsignee(consigneeDto);
	}
	
	/**
	 * 수하인 삭제
	 * @param consigneeDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void deleteConsignee(BasicConsignee consigneeDto, SessionDto session)throws Exception {
		consigneeDto.setUpdateUserId(session.getUserId());
		repository.deleteConsignee(consigneeDto);
	}
}
