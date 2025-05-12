package kc.logix.apps.mdm.partner.repository;

import static kc.logix.common.entity.QMdmPartner.mdmPartner;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.apps.mdm.partner.dto.PartnerDto;
import kc.logix.common.entity.MdmPartner;

@Repository
public class PartnerRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @param eq
	 * @return
	 * @throws Exception
	 */
	public List<MdmPartner> selectPartner(PartnerDto paramDto, boolean eq) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getName()))
			if(!eq)
				where.and(mdmPartner.name.contains(paramDto.getName()));
			else
				where.and(mdmPartner.name.eq(paramDto.getName()));
		
		return selectFrom(mdmPartner).where(where).fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void insertPartner(PartnerDto paramDto) throws Exception {
		insert(mdmPartner)
		.columns(
				mdmPartner.code,
				mdmPartner.name,
				mdmPartner.company,
				mdmPartner.pic,
				mdmPartner.representativeEml,
				mdmPartner.createUserId,
				mdmPartner.createDate,
				mdmPartner.updateUserId,
				mdmPartner.updateDate
		).values(
			paramDto.getCode(),
			paramDto.getName(),
			paramDto.getCompany(),
			paramDto.getPic(),
			paramDto.getRepresentativeEml(),
			paramDto.getCreateUserId(),
			new Date(),
			paramDto.getUpdateUserId(),
			new Date()
		).execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void updatePartner(PartnerDto paramDto) throws Exception {
		update(mdmPartner)
			.set(mdmPartner.name, paramDto.getName())
			.set(mdmPartner.company, paramDto.getCompany())
			.set(mdmPartner.pic, paramDto.getPic())
			.set(mdmPartner.representativeEml, paramDto.getRepresentativeEml())
			.set(mdmPartner.updateUserId, paramDto.getUpdateUserId())
			.set(mdmPartner.updateDate, new Date())
		.where(mdmPartner.code.eq(paramDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void deletePartner(PartnerDto paramDto) throws Exception {
		delete(mdmPartner).where(mdmPartner.code.eq(paramDto.getCode())).execute();
	}
	
}
