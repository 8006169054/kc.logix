package kc.logix.apps.partner.home.repository;

import static kc.logix.common.entity.QComUser.comUser;
import static kc.logix.common.entity.QMdmCargo.mdmCargo;
import static kc.logix.common.entity.QMdmPartner.mdmPartner;
import static kc.logix.common.entity.QMdmTerminal.mdmTerminal;
import static kc.logix.common.entity.QWebsiteTerminalCode.websiteTerminalCode;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.apps.partner.home.dto.HomeDto;
import kc.logix.apps.partner.home.dto.HomeExcelDownDto;

@Repository
public class HomeRepository extends KainosRepositorySupport {
	
	public String selectWebsiteTerminalCodeGridCol(String userId) {
		return select(mdmPartner.importMoniterRoleCode)
		.from(mdmPartner)
		.innerJoin(comUser).on(comUser.id.eq(userId).and(comUser.partnerCode.eq(mdmPartner.code)))
		.fetchOne();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	public List<HomeExcelDownDto> selectWebsiteTerminalCodeExcel(HomeDto paramDto) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getHblNo()))
			where.and(websiteTerminalCode.hblNo.contains(paramDto.getHblNo()));
		
		return select(Projections.bean(HomeExcelDownDto.class,
					websiteTerminalCode.sales,
					websiteTerminalCode.carryoverSales,
					new CaseBuilder().when(websiteTerminalCode.arrivalNotice.eq("1")).then(Expressions.constant("SEND")).otherwise(Expressions.constant("")).as("arrivalNotice"),
					new CaseBuilder().when(websiteTerminalCode.invoice.eq("1")).then(Expressions.constant("SEND")).otherwise(Expressions.constant("")).as("invoice"),
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
					new CaseBuilder().when(mdmTerminal.region.isNull()).then(websiteTerminalCode.pod.upper()).otherwise(mdmTerminal.region.upper()).as("pod"),
					mdmTerminal.code.as("terminalCode"),
					mdmTerminal.name.as("terminalName"),
					mdmTerminal.homepage.as("terminalHomepage"),
					mdmCargo.code.as("cargo"),
					mdmCargo.cargoDate.upper().as("cargoDate"),
					mdmCargo.location.upper().as("location"),
					new CaseBuilder().when(mdmCargo.name.isNull()).then(websiteTerminalCode.item.upper()).otherwise(mdmCargo.name.upper()).as("item"),
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
					websiteTerminalCode.repositionPrch
				)).from(websiteTerminalCode)
				.innerJoin(mdmPartner).on(mdmPartner.code.eq(paramDto.getPartner()).and(websiteTerminalCode.partner.eq(mdmPartner.name)))
				.leftJoin(mdmCargo).on(websiteTerminalCode.item.eq(mdmCargo.code))
				.leftJoin(mdmTerminal).on(websiteTerminalCode.terminal.eq(mdmTerminal.code))
				.where(where)
				.orderBy(websiteTerminalCode.uuid.asc(), websiteTerminalCode.seq.asc())
				.fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	public List<HomeDto> selectWebsiteTerminalCode(HomeDto paramDto) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getHblNo()))
			where.and(websiteTerminalCode.hblNo.contains(paramDto.getHblNo()));
		
		return select(Projections.bean(HomeDto.class,
					websiteTerminalCode.sales,
					websiteTerminalCode.carryoverSales,
					new CaseBuilder().when(websiteTerminalCode.arrivalNotice.eq("1")).then(Expressions.constant("SEND")).otherwise(Expressions.constant("")).as("arrivalNotice"),
					new CaseBuilder().when(websiteTerminalCode.invoice.eq("1")).then(Expressions.constant("SEND")).otherwise(Expressions.constant("")).as("invoice"),
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
					new CaseBuilder().when(mdmTerminal.region.isNull()).then(websiteTerminalCode.pod.upper()).otherwise(mdmTerminal.region.upper()).as("pod"),
					mdmTerminal.code.as("terminalCode"),
					mdmTerminal.name.as("terminalName"),
					mdmTerminal.homepage.as("terminalHomepage"),
					mdmCargo.code.as("cargo"),
					mdmCargo.cargoDate.upper().as("cargoDate"),
					mdmCargo.location.upper().as("location"),
					new CaseBuilder().when(mdmCargo.name.isNull()).then(websiteTerminalCode.item.upper()).otherwise(mdmCargo.name.upper()).as("item"),
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
					websiteTerminalCode.repositionPrch
				)).from(websiteTerminalCode)
				.innerJoin(mdmPartner).on(mdmPartner.code.eq(paramDto.getPartner()).and(websiteTerminalCode.partner.eq(mdmPartner.name)))
				.leftJoin(mdmCargo).on(websiteTerminalCode.item.eq(mdmCargo.code))
				.leftJoin(mdmTerminal).on(websiteTerminalCode.terminal.eq(mdmTerminal.code))
				.where(where)
				.orderBy(websiteTerminalCode.uuid.asc(), websiteTerminalCode.seq.asc())
				.fetch();
	}
	
}
