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
public class BasicDepot {

	@Id
	private String code;
	private String name;
	private String contactName;
	private String contactPerson;
	private String address;
	private String etcOne;
	private String etcTwo;
	private String updateUserId;
	private String updateDate;

}
