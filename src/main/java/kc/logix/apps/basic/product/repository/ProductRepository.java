package kc.logix.apps.basic.product.repository;

import static kc.logix.common.entity.QBasicProduct.basicProduct;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.common.entity.BasicProduct;
import kc.logix.common.util.CodeGenerationUtil;

@Repository
public class ProductRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @param eq
	 * @return
	 * @throws Exception
	 */
	public List<BasicProduct> selectProduct(BasicProduct paramDto, boolean eq) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getName()))
			if(!eq)
				where.and(basicProduct.name.contains(paramDto.getName()));
			else
				where.and(basicProduct.name.eq(paramDto.getName()));
		
		return selectFrom(basicProduct).where(where).fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void insertProduct(BasicProduct paramDto) throws Exception {
		insert(basicProduct)
		.columns(
			basicProduct.code,
			basicProduct.name,
			basicProduct.updateUserId
		).values(
			CodeGenerationUtil.createCode("PD"),
			paramDto.getName(),
			paramDto.getUpdateUserId()
		).execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void updateProduct(BasicProduct paramDto) throws Exception {
		update(basicProduct)
			.set(basicProduct.name, paramDto.getName())
			.set(basicProduct.updateUserId, paramDto.getUpdateUserId())
			.set(basicProduct.updateDate, LocalDateTime.now())
		.where(basicProduct.code.eq(paramDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void deleteProduct(BasicProduct paramDto) throws Exception {
		delete(basicProduct).where(basicProduct.code.eq(paramDto.getCode())).execute();
	}
}
