package kc.logix.apps.basic.partner.repository;

import static kc.logix.common.entity.QBasicPartner.basicPartner;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.common.entity.BasicPartner;
import kc.logix.common.util.CodeGenerationUtil;

@Repository
public class PartnerRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @param eq
	 * @return
	 * @throws Exception
	 */
	public List<BasicPartner> selectPartner(BasicPartner paramDto, boolean eq) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getName()))
			if(!eq)
				where.and(basicPartner.name.contains(paramDto.getName()));
			else
				where.and(basicPartner.name.eq(paramDto.getName()));
		
		return selectFrom(basicPartner).where(where).fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void insertPartner(BasicPartner paramDto) throws Exception {
		insert(basicPartner)
		.columns(
			basicPartner.code,
			basicPartner.name,
			basicPartner.contactName,
			basicPartner.contactPerson,
			basicPartner.addressOne,
			basicPartner.addressTwo,
			basicPartner.etcOne,
			basicPartner.etcTwo,
			basicPartner.etcThree,
			basicPartner.updateUserId
		).values(
			CodeGenerationUtil.createCode("PN"),
			paramDto.getName(),
			paramDto.getContactName(),
			paramDto.getContactPerson(),
			paramDto.getAddressOne(),
			paramDto.getAddressTwo(),
			paramDto.getEtcOne(),
			paramDto.getEtcTwo(),
			paramDto.getEtcThree(),
			paramDto.getUpdateUserId()
		).execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void updatePartner(BasicPartner paramDto) throws Exception {
		update(basicPartner)
			.set(basicPartner.name, paramDto.getName())
			.set(basicPartner.contactName, paramDto.getContactName())
			.set(basicPartner.contactPerson, paramDto.getContactPerson())
			.set(basicPartner.addressOne, paramDto.getAddressOne())
			.set(basicPartner.addressTwo, paramDto.getAddressTwo())
			.set(basicPartner.etcOne, paramDto.getEtcOne())
			.set(basicPartner.etcTwo, paramDto.getEtcTwo())
			.set(basicPartner.etcThree, paramDto.getEtcThree())
			.set(basicPartner.updateUserId, paramDto.getUpdateUserId())
			.set(basicPartner.updateDate, LocalDateTime.now())
		.where(basicPartner.code.eq(paramDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void deletePartner(BasicPartner paramDto) throws Exception {
		delete(basicPartner).where(basicPartner.code.eq(paramDto.getCode())).execute();
	}
	
}
