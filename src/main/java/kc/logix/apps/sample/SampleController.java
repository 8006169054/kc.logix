package kc.logix.apps.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import kc.logix.apps.sample.dto.SampleDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SampleController {
	
	@GetMapping(value = "/api/sample/selectbox")
	public ResponseEntity<SampleDto.SelectBox> selectbox() throws Exception {
		List<SampleDto.SelectBox> boxDatas = new ArrayList<>();
		boxDatas.add(SampleDto.SelectBox.builder().text("Option1").value("1").build());
		boxDatas.add(SampleDto.SelectBox.builder().text("Option2").value("2").build());
		boxDatas.add(SampleDto.SelectBox.builder().text("Option3").value("3").build());
		return KainosResponseEntity.builder().build().addData(boxDatas).close();
	}
}
