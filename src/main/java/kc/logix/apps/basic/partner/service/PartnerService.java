package kc.logix.apps.basic.partner.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kc.logix.apps.basic.partner.dto.PartnerDto;
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
	public List<BasicPartner> insertPartner(List<PartnerDto> paramDto, SessionDto session)throws Exception {
		List<BasicPartner> returnDtos = new ArrayList<>();
		for (Iterator<PartnerDto> iterator = paramDto.iterator(); iterator.hasNext();) {
			PartnerDto partner = iterator.next();
			BasicPartner basicPartner = BasicPartner.builder().build(); 
			BeanUtils.copyProperties(basicPartner, partner);
			if(repository.selectPartner(basicPartner, true).size() > 0) {
				returnDtos.add(basicPartner);
//				throw new KainosBusinessException("basic.partner.insert.duplicated");
			}
			else {
				basicPartner.setUpdateUserId(session.getUserName());
				if(partner.getJqFlag().equalsIgnoreCase("C"))
					repository.insertPartner(basicPartner);
				else if(partner.getJqFlag().equalsIgnoreCase("U"))
					repository.updatePartner(basicPartner);
				else if(partner.getJqFlag().equalsIgnoreCase("D"))
					repository.deletePartner(basicPartner);
			}
		}
		return returnDtos;
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
