package kc.logix.apps.basic.port.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PortDto {

	private String jqFlag;
	private String uuid;
	private String sales; //매출
	private String carryoverSales; //이월 매출
	private String arrivalNotice; //A/N&EDI
	private String invoice; //INVOICE
	private String concine; //CNEE
	private String profitDate; //PROFIT DATE
	private String domesticSales; //국내매출
	private String foreignSales; //해외매출
	private String quantity; //Q'ty
	private String partner; //Partner
	private String tankNo; //Tank no.
	private String term; //Term
	private String item; //ITEM
	private String vesselVoyage; //Vessel / Voyage
	private String carrier; //Carrier
	private String mblNo; //MBL NO
	private String hblNo; //HBL NO.
	private String pol; //POL
	private String pod; //POD
	private String terminal; //TERMINAL
	private String etd; //ETD
	private String eta; //ETA
	private String ata; //ATA
	private String remark; //비고
	private String ft; //F/T
	private String demRate; //DEM RATE
	private String endOfFt; //END OF F/T
	private String estimateReturnDate; //ESTIMATE RETURN DATE
	private String returnDate; //RETURN DATE
	private String demDays; //DEM DAYS
	private String totalDem; //TOTAL DEM
	private String returnDepot; //반납DEPOT
	private String demRcvd; //DEM RCVD
	private String demPrch; //DEM(USD)-매입
	private String demSales; //DEM 매출
	private String depotInDate; //DEPOT IN DATE
	private String repositionPrch; //REPOSITION 매입
	private String userId; //REPOSITION 매입
	
}
