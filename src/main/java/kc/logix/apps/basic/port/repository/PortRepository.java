package kc.logix.apps.basic.port.repository;

import static kc.logix.common.entity.QBasicPort.basicPort;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.common.entity.BasicPort;
import kc.logix.common.util.CodeGenerationUtil;

@Repository
public class PortRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @param eq
	 * @return
	 * @throws Exception
	 */
	public List<BasicPort> selectPort(BasicPort paramDto, boolean eq) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getName()))
			if(!eq)
				where.and(basicPort.name.contains(paramDto.getName()));
			else
				where.and(basicPort.name.eq(paramDto.getName()));
		
		return selectFrom(basicPort).where(where).fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void insertPort(BasicPort paramDto) throws Exception {
		insert(basicPort)
		.columns(
			basicPort.code,
			basicPort.name,
			basicPort.remark,
			basicPort.updateUserId
		).values(
			CodeGenerationUtil.createCode("PT"),
			paramDto.getName(),
			paramDto.getRemark(),
			paramDto.getUpdateUserId()
		).execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void updatePort(BasicPort paramDto) throws Exception {
		update(basicPort)
			.set(basicPort.name, paramDto.getName())
			.set(basicPort.updateUserId, paramDto.getUpdateUserId())
			.set(basicPort.updateDate, LocalDateTime.now())
		.where(basicPort.code.eq(paramDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void deletePort(BasicPort paramDto) throws Exception {
		delete(basicPort).where(basicPort.code.eq(paramDto.getCode())).execute();
	}
	
}
