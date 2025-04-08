package kc.logix.apps.basic.consignee.repository;

import static kc.logix.common.entity.QBasicConsignee.basicConsignee;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.common.entity.BasicConsignee;
import kc.logix.common.util.CodeGenerationUtil;

@Repository
public class ConsigneeRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param consigneeDto
	 * @param eq
	 * @return
	 * @throws Exception
	 */
	public List<BasicConsignee> selectConsignee(BasicConsignee consigneeDto, boolean eq) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(consigneeDto.getName()))
			if(!eq)
				where.and(basicConsignee.name.contains(consigneeDto.getName()));
			else
				where.and(basicConsignee.name.eq(consigneeDto.getName()));
		
		return selectFrom(basicConsignee).where(where).fetch();
	}
	
	/**
	 * 
	 * @param consigneeDto
	 * @throws Exception
	 */
	public void insertConsignee(BasicConsignee consigneeDto) throws Exception {
		insert(basicConsignee)
		.columns(
			basicConsignee.code,
			basicConsignee.name,
			basicConsignee.contactName,
			basicConsignee.contactPerson,
			basicConsignee.addressOne,
			basicConsignee.addressTwo,
			basicConsignee.etcOne,
			basicConsignee.etcTwo,
			basicConsignee.etcThree,
			basicConsignee.updateUserId
		).values(
			CodeGenerationUtil.createCode("CS"),
			consigneeDto.getName(),
			consigneeDto.getContactName(),
			consigneeDto.getContactPerson(),
			consigneeDto.getAddressOne(),
			consigneeDto.getAddressTwo(),
			consigneeDto.getEtcOne(),
			consigneeDto.getEtcTwo(),
			consigneeDto.getEtcThree(),
			consigneeDto.getUpdateUserId()
		).execute();
	}
	
	/**
	 * 
	 * @param consigneeDto
	 * @throws Exception
	 */
	public void updateConsignee(BasicConsignee consigneeDto) throws Exception {
		update(basicConsignee)
			.set(basicConsignee.name, consigneeDto.getName())
			.set(basicConsignee.contactName, consigneeDto.getContactName())
			.set(basicConsignee.contactPerson, consigneeDto.getContactPerson())
			.set(basicConsignee.addressOne, consigneeDto.getAddressOne())
			.set(basicConsignee.addressTwo, consigneeDto.getAddressTwo())
			.set(basicConsignee.etcOne, consigneeDto.getEtcOne())
			.set(basicConsignee.etcTwo, consigneeDto.getEtcTwo())
			.set(basicConsignee.etcThree, consigneeDto.getEtcThree())
			.set(basicConsignee.updateUserId, consigneeDto.getUpdateUserId())
			.set(basicConsignee.updateDate, LocalDateTime.now())
		.where(basicConsignee.code.eq(consigneeDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param consigneeDto
	 * @throws Exception
	 */
	public void deleteConsignee(BasicConsignee consigneeDto) throws Exception {
		delete(basicConsignee).where(basicConsignee.code.eq(consigneeDto.getCode())).execute();
	}
}
