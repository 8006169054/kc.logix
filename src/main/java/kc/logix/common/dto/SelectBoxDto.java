package kc.logix.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class SelectBoxDto {

	@Builder
	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Autocomplete {
		private String value;
		private String label;
		private String code;
	}
}
