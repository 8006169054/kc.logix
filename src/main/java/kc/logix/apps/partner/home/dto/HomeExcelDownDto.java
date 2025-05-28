package kc.logix.apps.partner.home.dto;

import kainos.framework.core.support.jqgrid.dto.RowSpan;
import kc.logix.common.util.excel.Field;
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
public class HomeExcelDownDto {

	@Field(value = "A", merge = true) 
	private String sales; //매출
	
	@Field(value = "B", merge = true)
	private String carryoverSales; //이월 매출
	
	@Field(value = "C", merge = true)
	private String arrivalNotice; //A/N&EDI
	
	@Field(value = "D", merge = true)
	private String invoice; //INVOICE
	
	@Field(value = "E", merge = true)
	private String concine; //CNEE
	
	@Field(value = "F", merge = true)
	private String profitDate; //PROFIT DATE
	
	@Field(value = "G", merge = true)
	private String domesticSales; //국내매출
	
	@Field(value = "H", merge = true)
	private String foreignSales; //해외매출
	
	@Field(value = "I", merge = true)
	private String quantity; //Q'ty
	
	@Field(value = "J")
	private String partner; //Partner
	
	@Field(value = "K")
	private String tankNo; //Tank no.
	
	@Field(value = "L", merge = true)
	private String term; //Term
	
	@Field(value = "M", merge = true)
	private String item; //ITEM
	
	@Field(value = "N", merge = true) 
	private String cargoDate;
	
	@Field(value = "O", merge = true) 
	private String location;
	
	@Field(value = "P", merge = true)
	private String vesselVoyage; //Vessel / Voyage
	
	@Field(value = "Q", merge = true)
	private String carrier; //Carrier
	
	@Field(value = "R", merge = true)
	private String mblNo; //MBL NO
	
	@Field(value = "S", mergeOrder = 0, merge = true)
	private String hblNo; //HBL NO.
	
	@Field(value = "T", merge = true)
	private String pol; //POL
	
	@Field(value = "U")
	private String pod; //POD
	
	@Field(value = "V")
	private String terminalName; //TERMINAL code
	
	@Field(value = "W")
	private String etd; //ETD
	
	@Field(value = "X")
	private String eta; //ETA
	
	@Field(value = "Y")
	private String ata; //ATA
	
	@Field(value = "Z", merge = true)
	private String remark; //비고
	
	@Field(value = "AA")
	private String ft; //F/T
	
	@Field(value = "AB")
	private String demRate; //DEM RATE
	
	@Field(value = "AC")
	private String endOfFt; //END OF F/T
	
	@Field(value = "AD")
	private String estimateReturnDate; //ESTIMATE RETURN DATE
	
	@Field(value = "AE")
	private String returnDate; //RETURN DATE
	
	@Field(value = "AF")
	private String returnDepot; //RETURN DEPOT
	
	@Field(value = "AG")
	private String totalDem; //TOTAL DEM
	
	@Field(value = "AH")
	private String demReceived; //DEM RECEIVED
	
	@Field(value = "AI")
	private String demRcvd; //DEM RCVD
	
	@Field(value = "AJ")
	private String demPrch; //DEM(USD)-매입
	
	@Field(value = "AK")
	private String demSales; //DEM 매출
	
	@Field(value = "AL")
	private String depotInDate; //DEPOT IN DATE
	
	@Field(value = "AM")
	private String repositionPrch; //REPOSITION 매입
	
	@Builder.Default /* 필드명을 rowspan 해야 함 필수 */
	private RowSpanOtion rowspan = RowSpanOtion.builder().build();
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RowSpanOtion{
		@Builder.Default
		private RowSpan cargoDate = RowSpan.builder().build();
		@Builder.Default
		private RowSpan location = RowSpan.builder().build();
		@Builder.Default
		private RowSpan sales = RowSpan.builder().build();
		@Builder.Default
		private RowSpan carryoverSales = RowSpan.builder().build();
		@Builder.Default
		private RowSpan arrivalNotice = RowSpan.builder().build();
		@Builder.Default
		private RowSpan invoice = RowSpan.builder().build();
		@Builder.Default
		private RowSpan concine = RowSpan.builder().build();
		@Builder.Default
		private RowSpan profitDate = RowSpan.builder().build();
		@Builder.Default
		private RowSpan domesticSales = RowSpan.builder().build();
		@Builder.Default
		private RowSpan foreignSales = RowSpan.builder().build();
		@Builder.Default
		private RowSpan quantity = RowSpan.builder().build();
		@Builder.Default
		private RowSpan term = RowSpan.builder().build();
		@Builder.Default
		private RowSpan item = RowSpan.builder().build();
		@Builder.Default
		private RowSpan vesselVoyage = RowSpan.builder().build();
		@Builder.Default
		private RowSpan carrier = RowSpan.builder().build();
		@Builder.Default
		private RowSpan mblNo = RowSpan.builder().build();
		@Builder.Default
		private RowSpan hblNo = RowSpan.builder().build();
		@Builder.Default
		private RowSpan pol = RowSpan.builder().build();
		@Builder.Default
		private RowSpan remark = RowSpan.builder().build();
	};
}
