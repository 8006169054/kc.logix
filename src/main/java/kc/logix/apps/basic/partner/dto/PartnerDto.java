package kc.logix.apps.basic.partner.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class PartnerDto {

	private String jqFlag;
	private String code;
	private String name;
	private String contactName;
	private String contactPerson;
	private String addressOne;
	private String addressTwo;
	private String etcOne;
	private String etcTwo;
	private String etcThree;
	private String updateUserId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDate;
}
