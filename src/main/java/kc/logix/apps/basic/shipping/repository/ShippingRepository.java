package kc.logix.apps.basic.shipping.repository;

import static kc.logix.common.entity.QBasicShipping.basicShipping;

import java.time.LocalDateTime;
import java.util.List;

//import static kc.logix.common.entity.QAccount.account
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.common.entity.BasicShipping;
import kc.logix.common.util.CodeGenerationUtil;

@Repository
public class ShippingRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @param eq
	 * @return
	 * @throws Exception
	 */
	public List<BasicShipping> selectShipping(BasicShipping paramDto, boolean eq) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getName()))
			if(!eq)
				where.and(basicShipping.name.contains(paramDto.getName()));
			else
				where.and(basicShipping.name.eq(paramDto.getName()));
		
		return selectFrom(basicShipping).where(where).fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void insertShipping(BasicShipping paramDto) throws Exception {
		insert(basicShipping)
		.columns(
			basicShipping.code,
			basicShipping.name,
			basicShipping.contactName,
			basicShipping.contactPerson,
			basicShipping.addressOne,
			basicShipping.addressTwo,
			basicShipping.etcOne,
			basicShipping.etcTwo,
			basicShipping.etcThree,
			basicShipping.updateUserId
		).values(
			CodeGenerationUtil.createCode("SP"),
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
	public void updateShipping(BasicShipping paramDto) throws Exception {
		update(basicShipping)
			.set(basicShipping.name, paramDto.getName())
			.set(basicShipping.contactName, paramDto.getContactName())
			.set(basicShipping.contactPerson, paramDto.getContactPerson())
			.set(basicShipping.addressOne, paramDto.getAddressOne())
			.set(basicShipping.addressTwo, paramDto.getAddressTwo())
			.set(basicShipping.etcOne, paramDto.getEtcOne())
			.set(basicShipping.etcTwo, paramDto.getEtcTwo())
			.set(basicShipping.etcThree, paramDto.getEtcThree())
			.set(basicShipping.updateUserId, paramDto.getUpdateUserId())
			.set(basicShipping.updateDate, LocalDateTime.now())
		.where(basicShipping.code.eq(paramDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void deleteShipping(BasicShipping paramDto) throws Exception {
		delete(basicShipping).where(basicShipping.code.eq(paramDto.getCode())).execute();
	}
	
}
