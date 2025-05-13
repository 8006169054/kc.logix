package kc.logix.apps.mdm.cargo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CargoDto {

	private String jqFlag;
	private String code;
	private String name;
	private String location;
	private String depot;
	private String cleaningCost;
	private String difficultLevel;
	private String remark1;
	private String remark2;
	private String createUserId;
	private Date createDate;
	private String updateUserId;
	private Date updateDate;
	
}
