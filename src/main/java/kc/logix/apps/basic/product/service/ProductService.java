package kc.logix.apps.basic.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kainos.framework.core.lang.KainosBusinessException;
import kc.logix.apps.basic.product.repository.ProductRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicProduct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repository;
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<BasicProduct> selectProduct(BasicProduct paramDto) throws Exception {
		return repository.selectProduct(paramDto, false);
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void insertProduct(BasicProduct paramDto, SessionDto session)throws Exception {
		if(repository.selectProduct(paramDto, true).size() > 0) {
			throw new KainosBusinessException("basic.product.insert.duplicated");
		}
		else {
			paramDto.setUpdateUserId(session.getUserName());
			repository.insertProduct(paramDto);
		}
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void updateProduct(BasicProduct paramDto, SessionDto session)throws Exception {
		paramDto.setUpdateUserId(session.getUserName());
		repository.updateProduct(paramDto);
	}
	
	/**
	 * 
	 * @param paramDtos
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void deleteProduct(List<BasicProduct> paramDtos, SessionDto session)throws Exception {
		for (int i = 0; i < paramDtos.size(); i++) {
			BasicProduct paramDto = paramDtos.get(i);
			paramDto.setUpdateUserId(session.getUserName());
			repository.deleteProduct(paramDto);
		}
		
	}
}
