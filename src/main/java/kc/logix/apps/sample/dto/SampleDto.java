package kc.logix.apps.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
public class SampleDto {

	@Data
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SelectBox {
		private String text;
		private String value;
		private boolean selected;
	}
}
