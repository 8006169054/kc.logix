package kc.logix.apps.basic.port.repository;

import static kc.logix.common.entity.QTerminal.terminal;
import static kc.logix.common.entity.QWebsiteTerminalCode.websiteTerminalCode;

import java.util.Date;
import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosDateUtil;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.apps.basic.port.dto.PortDto;

@Repository
public class PortRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	public List<PortDto> selectWebsiteTerminalCode(PortDto paramDto) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getHblNo()))
			where.and(websiteTerminalCode.hblNo.contains(paramDto.getHblNo()));
		
		if(!KainosStringUtils.isEmpty(paramDto.getArrivalNotice()) && (paramDto.getArrivalNotice().equals("1") || paramDto.getArrivalNotice().equals("0")))
			where.and(websiteTerminalCode.arrivalNotice.contains(paramDto.getArrivalNotice()));
		
		return select(Projections.bean(PortDto.class,
					websiteTerminalCode.uuid,
					websiteTerminalCode.sales,
					websiteTerminalCode.carryoverSales,
					websiteTerminalCode.arrivalNotice,
					websiteTerminalCode.invoice,
					websiteTerminalCode.concine,
					websiteTerminalCode.profitDate,
					websiteTerminalCode.domesticSales,
					websiteTerminalCode.foreignSales,
					websiteTerminalCode.quantity,
					websiteTerminalCode.partner,
					websiteTerminalCode.tankNo,
					websiteTerminalCode.term,
					websiteTerminalCode.item,
					websiteTerminalCode.vesselVoyage,
					websiteTerminalCode.carrier,
					websiteTerminalCode.mblNo,
					websiteTerminalCode.hblNo,
					websiteTerminalCode.pol,
					websiteTerminalCode.pod,
					websiteTerminalCode.terminal,
//					ExpressionUtils.as(JPAExpressions.select(terminal.homepage).from(terminal).where(websiteTerminalCode.pod.eq(terminal.region)), "homepage"),
					websiteTerminalCode.etd,
					websiteTerminalCode.eta,
					websiteTerminalCode.ata,
					websiteTerminalCode.remark,
					websiteTerminalCode.ft,
					websiteTerminalCode.demRate,
					websiteTerminalCode.endOfFt,
					websiteTerminalCode.estimateReturnDate,
					websiteTerminalCode.returnDate,
					websiteTerminalCode.demReceived,
					websiteTerminalCode.totalDem,
					websiteTerminalCode.returnDepot,
					websiteTerminalCode.demRcvd,
					websiteTerminalCode.demPrch,
					websiteTerminalCode.demSales,
					websiteTerminalCode.depotInDate,
					websiteTerminalCode.repositionPrch,
					websiteTerminalCode.createUserId,
					Expressions.stringTemplate("to_char({0}, {1})", websiteTerminalCode.createDate, "YYYY-MM-DD").as("createDate"),
					websiteTerminalCode.updateUserId,
					Expressions.stringTemplate("to_char({0}, {1})", websiteTerminalCode.updateDate, "YYYY-MM-DD").as("updateDate")
				)).from(websiteTerminalCode)
				.where(where)
				.orderBy(websiteTerminalCode.uuid.asc())
				.fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void insertWebsiteTerminalCode(PortDto paramDto) throws Exception {
		insert(websiteTerminalCode)
		.columns(
			websiteTerminalCode.uuid,
			websiteTerminalCode.sales,
			websiteTerminalCode.carryoverSales,
			websiteTerminalCode.arrivalNotice,
			websiteTerminalCode.invoice,
			websiteTerminalCode.concine,
			websiteTerminalCode.profitDate,
			websiteTerminalCode.domesticSales,
			websiteTerminalCode.foreignSales,
			websiteTerminalCode.quantity,
			websiteTerminalCode.partner,
			websiteTerminalCode.tankNo,
			websiteTerminalCode.term,
			websiteTerminalCode.item,
			websiteTerminalCode.vesselVoyage,
			websiteTerminalCode.carrier,
			websiteTerminalCode.mblNo,
			websiteTerminalCode.hblNo,
			websiteTerminalCode.pol,
			websiteTerminalCode.pod,
			websiteTerminalCode.terminal,
			websiteTerminalCode.etd,
			websiteTerminalCode.eta,
			websiteTerminalCode.ata,
			websiteTerminalCode.remark,
			websiteTerminalCode.ft,
			websiteTerminalCode.demRate,
			websiteTerminalCode.endOfFt,
			websiteTerminalCode.estimateReturnDate,
			websiteTerminalCode.returnDate,
			websiteTerminalCode.demReceived,
			websiteTerminalCode.totalDem,
			websiteTerminalCode.returnDepot,
			websiteTerminalCode.demRcvd,
			websiteTerminalCode.demPrch,
			websiteTerminalCode.demSales,
			websiteTerminalCode.depotInDate,
			websiteTerminalCode.repositionPrch,
			websiteTerminalCode.createUserId,
			websiteTerminalCode.createDate,
			websiteTerminalCode.updateUserId,
			websiteTerminalCode.updateDate
		).values(
			(KainosDateUtil.getCurrentDay("yyyyMMddHHmmssSSS") + new RandomDataGenerator().nextSecureHexString(3)),
			paramDto.getSales(),
			paramDto.getCarryoverSales(),
			(paramDto.getArrivalNotice().equals("OK") ? "1" : "0"),
			paramDto.getInvoice() == "OK" ? "1" : "0",
			paramDto.getConcine(),
			paramDto.getProfitDate().replaceAll("-", ""),
			paramDto.getDomesticSales().replaceAll("-", ""),
			paramDto.getForeignSales().replaceAll("-", ""),
			paramDto.getQuantity(),
			paramDto.getPartner(),
			paramDto.getTankNo(),
			paramDto.getTerm(),
			paramDto.getItem(),
			paramDto.getVesselVoyage(),
			paramDto.getCarrier(),
			paramDto.getMblNo(),
			paramDto.getHblNo().replaceAll("-", ""),
			paramDto.getPol(),
			paramDto.getPod(),
			paramDto.getTerminal(),
			paramDto.getEtd(),
			paramDto.getEta(),
			paramDto.getAta(),
			paramDto.getRemark(),
			paramDto.getFt().replaceAll("-", ""),
			paramDto.getDemRate().replaceAll("-", ""),
			paramDto.getEndOfFt().replaceAll("N/A", ""),
			paramDto.getEstimateReturnDate().replaceAll("-", ""),
			paramDto.getReturnDate().replaceAll("-", ""),
			paramDto.getDemReceived().replaceAll("N/A", ""),
			paramDto.getTotalDem().replaceAll("N/A", ""),
			paramDto.getReturnDepot().replaceAll("-", ""),
			paramDto.getDemRcvd().replaceAll("N/A", ""),
			paramDto.getDemPrch().replaceAll("N/A", ""),
			paramDto.getDemSales().replaceAll("N/A", ""),
			paramDto.getDepotInDate(),
			paramDto.getRepositionPrch(),
			paramDto.getCreateUserId(),
			new Date(),
			paramDto.getCreateUserId(),
			new Date()
		).execute();
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
			.set(websiteTerminalCode.domesticSales,       paramDto.getDomesticSales().replaceAll("US\\$", ""))
			.set(websiteTerminalCode.foreignSales,        paramDto.getForeignSales().replaceAll("US\\$", ""))
			.set(websiteTerminalCode.quantity,            paramDto.getQuantity())
			.set(websiteTerminalCode.partner,             paramDto.getPartner())
			.set(websiteTerminalCode.tankNo,              paramDto.getTankNo())
			.set(websiteTerminalCode.term,                paramDto.getTerm())
			.set(websiteTerminalCode.item,                paramDto.getItem())
			.set(websiteTerminalCode.vesselVoyage,        paramDto.getVesselVoyage())
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
			.set(websiteTerminalCode.demReceived,         paramDto.getDemReceived())
			.set(websiteTerminalCode.totalDem,            paramDto.getTotalDem().replaceAll("US\\$", ""))
			.set(websiteTerminalCode.returnDepot,         paramDto.getReturnDepot())
			.set(websiteTerminalCode.demRcvd,             paramDto.getDemRcvd())
			.set(websiteTerminalCode.demPrch,             paramDto.getDemPrch().replaceAll("US\\$", ""))
			.set(websiteTerminalCode.demSales,            paramDto.getDemSales().replaceAll("US\\$", ""))
			.set(websiteTerminalCode.depotInDate,         paramDto.getDepotInDate())
			.set(websiteTerminalCode.repositionPrch, 	  paramDto.getRepositionPrch())
			.set(websiteTerminalCode.updateUserId, 		  paramDto.getUpdateUserId())
			.set(websiteTerminalCode.updateDate, 			new Date())
		.where(websiteTerminalCode.uuid.eq(paramDto.getUuid()))
		.execute();
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void excelUploadHblNoDelete(String mblNo) throws Exception {
		delete(websiteTerminalCode).where(websiteTerminalCode.mblNo.eq(mblNo)).execute();
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
