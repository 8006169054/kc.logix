package kc.logix.apps.basic.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.lang.KainosBusinessException;
import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import kc.logix.apps.basic.product.service.ProductService;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.entity.BasicProduct;
import kc.logix.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	private final MessageUtil message;
	
	@GetMapping(value = "/api/basic/product")
	public ResponseEntity<BasicProduct> selectProduct(@RequestParam(required = false) String name) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.selectProduct(BasicProduct.builder().name(name).build()))
				.close();
	}
	
	@PostMapping(value = "/api/basic/product")
	public ResponseEntity<Void> insertProduct(@RequestBody BasicProduct paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.insertProduct(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getInsertMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@PatchMapping(value = "/api/basic/product")
	public ResponseEntity<Void> updateProduct(@RequestBody BasicProduct paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.updateProduct(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getUpdateMessage(KainosResponseEntity.builder().build()).close();
	}
	
	@DeleteMapping(value = "/api/basic/product")
	public ResponseEntity<Void> deleteProduct(@RequestBody List<BasicProduct> paramDto, @KainosSession SessionDto session) throws Exception {
		try {
			service.deleteProduct(paramDto, session);
		} catch (KainosBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new KainosBusinessException("common.system.error");
		}
		return message.getDeleteMessage(KainosResponseEntity.builder().build()).close();
	}
}
