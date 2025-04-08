package kc.logix.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BasicShipping {

	@Id
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
	private String updateDate;

}
