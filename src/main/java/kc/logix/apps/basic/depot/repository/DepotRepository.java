package kc.logix.apps.basic.depot.repository;

import static kc.logix.common.entity.QBasicDepot.basicDepot;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.common.entity.BasicDepot;
import kc.logix.common.util.CodeGenerationUtil;

@Repository
public class DepotRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @param eq
	 * @return
	 * @throws Exception
	 */
	public List<BasicDepot> selectDepot(BasicDepot paramDto, boolean eq) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getName()))
			if(!eq)
				where.and(basicDepot.name.contains(paramDto.getName()));
			else
				where.and(basicDepot.name.eq(paramDto.getName()));
		
		return selectFrom(basicDepot).where(where).fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void insertDepot(BasicDepot paramDto) throws Exception {
		insert(basicDepot)
		.columns(
			basicDepot.code,
			basicDepot.name,
			basicDepot.contactName,
			basicDepot.contactPerson,
			basicDepot.address,
			basicDepot.etcOne,
			basicDepot.etcTwo,
			basicDepot.updateUserId
		).values(
			CodeGenerationUtil.createCode("DP"),
			paramDto.getName(),
			paramDto.getContactName(),
			paramDto.getContactPerson(),
			paramDto.getAddress(),
			paramDto.getEtcOne(),
			paramDto.getEtcTwo(),
			paramDto.getUpdateUserId()
		).execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void updateDepot(BasicDepot paramDto) throws Exception {
		update(basicDepot)
			.set(basicDepot.name, paramDto.getName())
			.set(basicDepot.contactName, paramDto.getContactName())
			.set(basicDepot.contactPerson, paramDto.getContactPerson())
			.set(basicDepot.address, paramDto.getAddress())
			.set(basicDepot.etcOne, paramDto.getEtcOne())
			.set(basicDepot.etcTwo, paramDto.getEtcTwo())
			.set(basicDepot.updateUserId, paramDto.getUpdateUserId())
			.set(basicDepot.updateDate, LocalDateTime.now())
		.where(basicDepot.code.eq(paramDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void deleteDepot(BasicDepot paramDto) throws Exception {
		delete(basicDepot).where(basicDepot.code.eq(paramDto.getCode())).execute();
	}
	
}
