package kc.logix.apps.basic.port.repository;

import static kc.logix.common.entity.QBasicPort.basicPort;
import static kc.logix.common.entity.QWebsiteTerminalCode.websiteTerminalCode;

import java.util.Date;
import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosDateUtil;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.apps.basic.port.dto.PortDto;
import kc.logix.common.entity.BasicPort;
import kc.logix.common.entity.WebsiteTerminalCode;

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
	public void insertWebsiteTerminalCode(PortDto paramDto) throws Exception {
		WebsiteTerminalCode table = WebsiteTerminalCode.builder().build();
		
		BeanUtils.copyProperties(paramDto, table);
		table.setUuid(KainosDateUtil.getCurrentDay("yyyyMMddHHmmssSSS") + new RandomDataGenerator().nextSecureHexString(3));
		table.setCreateUserId(paramDto.getUserId());
		table.setUpdateUserId(paramDto.getUserId());
		table.setCreateDate(new Date());
		table.setUpdateDate(new Date());
		insert(table);
		
//		insert(websiteTerminalCode)
//		.columns(
//			websiteTerminalCode.uuid,
//			websiteTerminalCode.sales,
//			websiteTerminalCode.carryoverSales,
//			websiteTerminalCode.arrivalNotice,
//			websiteTerminalCode.invoice,
//			websiteTerminalCode.concine,
//			websiteTerminalCode.profitDate,
//			websiteTerminalCode.domesticSales,
//			websiteTerminalCode.foreignSales,
//			websiteTerminalCode.quantity,
//			websiteTerminalCode.partner,
//			websiteTerminalCode.tankNo,
//			websiteTerminalCode.term,
//			websiteTerminalCode.item,
//			websiteTerminalCode.vslVoy,
//			websiteTerminalCode.carrier,
//			websiteTerminalCode.mblNo,
//			websiteTerminalCode.hblNo,
//			websiteTerminalCode.pol,
//			websiteTerminalCode.pod,
//			websiteTerminalCode.terminal,
//			websiteTerminalCode.etd,
//			websiteTerminalCode.eta,
//			websiteTerminalCode.ata,
//			websiteTerminalCode.remark,
//			websiteTerminalCode.ft,
//			websiteTerminalCode.demRate,
//			websiteTerminalCode.endOfFt,
//			websiteTerminalCode.estimateReturnDate,
//			websiteTerminalCode.returnDate,
//			websiteTerminalCode.demDays,
//			websiteTerminalCode.totalDem,
//			websiteTerminalCode.returnDepot,
//			websiteTerminalCode.demRcvd,
//			websiteTerminalCode.demPrch,
//			websiteTerminalCode.demSales,
//			websiteTerminalCode.depotInDate,
//			websiteTerminalCode.repositionPrch,
//			websiteTerminalCode.createUserId,
//			websiteTerminalCode.createDate,
//			websiteTerminalCode.updateUserId,
//			websiteTerminalCode.updateDate
//		).values(
//			(KainosDateUtil.getCurrentDay("yyyyMMddHHmmssSSS") + new RandomDataGenerator().nextSecureHexString(3)),
//			paramDto.getSales(),
//			paramDto.getCarryoverSales(),
//			paramDto.getArrivalNotice(),
//			paramDto.getInvoice(),
//			paramDto.getConcine(),
//			paramDto.getProfitDate(),
//			paramDto.getDomesticSales(),
//			paramDto.getForeignSales(),
//			paramDto.getQuantity(),
//			paramDto.getPartner(),
//			paramDto.getTankNo(),
//			paramDto.getTerm(),
//			paramDto.getItem(),
//			paramDto.getVesselVoyage(),
//			paramDto.getCarrier(),
//			paramDto.getMblNo(),
//			paramDto.getHblNo(),
//			paramDto.getPol(),
//			paramDto.getPod(),
//			paramDto.getTerminal(),
//			paramDto.getEtd(),
//			paramDto.getEta(),
//			paramDto.getAta(),
//			paramDto.getRemark(),
//			paramDto.getFt(),
//			paramDto.getDemRate(),
//			paramDto.getEndOfFt(),
//			paramDto.getEstimateReturnDate(),
//			paramDto.getReturnDate(),
//			paramDto.getDemDays(),
//			paramDto.getTotalDem(),
//			paramDto.getTotalDem(),
//			paramDto.getDemRcvd(),
//			paramDto.getDemPrch(),
//			paramDto.getDemSales(),
//			paramDto.getDepotInDate(),
//			paramDto.getRepositionPrch(),
//			paramDto.getUserId(),
//			new Date(),
//			paramDto.getUserId(),
//			new Date()
//		).execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void updateWebsiteTerminalCode(PortDto paramDto) throws Exception {
		update(websiteTerminalCode)
			.set(websiteTerminalCode.sales,				paramDto.getSales())
			.set(websiteTerminalCode.carryoverSales,      paramDto.getCarryoverSales())
			.set(websiteTerminalCode.arrivalNotice,       paramDto.getArrivalNotice())
			.set(websiteTerminalCode.invoice,             paramDto.getInvoice())
			.set(websiteTerminalCode.concine,             paramDto.getConcine())
			.set(websiteTerminalCode.profitDate,          paramDto.getProfitDate())
			.set(websiteTerminalCode.domesticSales,       paramDto.getDomesticSales())
			.set(websiteTerminalCode.foreignSales,        paramDto.getForeignSales())
			.set(websiteTerminalCode.quantity,            paramDto.getQuantity())
			.set(websiteTerminalCode.partner,             paramDto.getPartner())
			.set(websiteTerminalCode.tankNo,              paramDto.getTankNo())
			.set(websiteTerminalCode.term,                paramDto.getTerm())
			.set(websiteTerminalCode.item,                paramDto.getItem())
			.set(websiteTerminalCode.vslVoy,         		paramDto.getVesselVoyage())
			.set(websiteTerminalCode.carrier,             paramDto.getCarrier())
			.set(websiteTerminalCode.mblNo,               paramDto.getMblNo())
			.set(websiteTerminalCode.hblNo,               paramDto.getHblNo())
			.set(websiteTerminalCode.pol,                 paramDto.getPol())
			.set(websiteTerminalCode.pod,                 paramDto.getPod())
			.set(websiteTerminalCode.terminal,            paramDto.getTerminal())
			.set(websiteTerminalCode.etd,                 paramDto.getEtd())
			.set(websiteTerminalCode.eta,                 paramDto.getEta())
			.set(websiteTerminalCode.ata,                 paramDto.getAta())
			.set(websiteTerminalCode.remark,              paramDto.getRemark())
			.set(websiteTerminalCode.ft,                  paramDto.getFt())
			.set(websiteTerminalCode.demRate,             paramDto.getDemRate())
			.set(websiteTerminalCode.endOfFt,             paramDto.getEndOfFt())
			.set(websiteTerminalCode.estimateReturnDate,  paramDto.getEstimateReturnDate())
			.set(websiteTerminalCode.returnDate,          paramDto.getReturnDate())
			.set(websiteTerminalCode.demDays,             paramDto.getDemDays())
			.set(websiteTerminalCode.totalDem,            paramDto.getTotalDem())
			.set(websiteTerminalCode.returnDepot,         paramDto.getTotalDem())
			.set(websiteTerminalCode.demRcvd,             paramDto.getDemRcvd())
			.set(websiteTerminalCode.demPrch,             paramDto.getDemPrch())
			.set(websiteTerminalCode.demSales,            paramDto.getDemSales())
			.set(websiteTerminalCode.depotInDate,         paramDto.getDepotInDate())
			.set(websiteTerminalCode.repositionPrch, 		paramDto.getRepositionPrch())
			.set(websiteTerminalCode.updateUserId, 		paramDto.getUserId())
			.set(websiteTerminalCode.updateDate, 			new Date())
		.where(websiteTerminalCode.uuid.eq(paramDto.getUuid()))
		.execute();
	}
	
	/**
	 * 
	 * @param uuid
	 * @throws Exception
	 */
	public void deleteWebsiteTerminalCode(String uuid) throws Exception {
		delete(websiteTerminalCode).where(websiteTerminalCode.uuid.eq(uuid)).execute();
	}
	
}
