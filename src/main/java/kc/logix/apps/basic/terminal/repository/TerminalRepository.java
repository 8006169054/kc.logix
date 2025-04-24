package kc.logix.apps.basic.terminal.repository;

import static kc.logix.common.entity.QTerminal.terminal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.querydsl.core.BooleanBuilder;
import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosStringUtils;
import kc.logix.apps.basic.terminal.dto.TerminalDto;
import kc.logix.common.entity.Terminal;

@Repository
public class TerminalRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	public List<Terminal> selectTerminal(TerminalDto paramDto) throws Exception {
		BooleanBuilder where = new BooleanBuilder();
		if(!KainosStringUtils.isEmpty(paramDto.getCode()))
			where.and(terminal.code.contains(paramDto.getCode()));
		else if(!KainosStringUtils.isEmpty(paramDto.getName()))
			where.and(terminal.name.contains(paramDto.getName()));
		
		return selectFrom(terminal).where(where).fetch();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param userId
	 * @throws Exception
	 */
	public void insertTerminal(TerminalDto paramDto, String userId) throws Exception {
		insert(terminal)
		.columns(
			terminal.code,
			terminal.name,
			terminal.region,
			terminal.type,
			terminal.parkingLotCode,
			terminal.homepage,
			terminal.createUserId,
			terminal.createDate,
			terminal.updateUserId,
			terminal.updateDate
		).values(
			paramDto.getCode(),
			paramDto.getName(),
			paramDto.getRegion(),
			paramDto.getType(),
			paramDto.getParkingLotCode(),
			paramDto.getHomepage(),
			userId,
			new Date(),
			userId,
			new Date()
		).execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param userId
	 * @throws Exception
	 */
	public void updateTerminal(TerminalDto paramDto, String userId) throws Exception {
		update(terminal)
			.set(terminal.name, paramDto.getName())
			.set(terminal.region, paramDto.getRegion())
			.set(terminal.type, paramDto.getType())
			.set(terminal.parkingLotCode, paramDto.getParkingLotCode())
			.set(terminal.homepage, paramDto.getHomepage())
			.set(terminal.updateUserId, userId)
			.set(terminal.updateDate, new Date())
		.where(terminal.code.eq(paramDto.getCode()))
		.execute();
	}
	
	/**
	 * 
	 * @param paramDto
	 * @throws Exception
	 */
	public void deleteTerminal(TerminalDto paramDto) throws Exception {
		delete(terminal).where(terminal.code.eq(paramDto.getCode())).execute();
	}
	
}
