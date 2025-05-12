package kc.logix.apps.mdm.cargo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import kc.logix.apps.mdm.cargo.dto.CargoDto;
import kc.logix.apps.mdm.cargo.repository.CargoRepository;
import kc.logix.common.dto.SessionDto;
import kc.logix.common.util.JqFlag;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargoService {

	private final CargoRepository repository;
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<CargoDto> selectCargo(CargoDto paramDto) throws Exception {
		return repository.selectCargo(paramDto);
	}
	
	/**
	 * 
	 * @param paramDto
	 * @param session
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default, rollbackFor = Exception.class)
	public void saveCargo(List<CargoDto> paramList, SessionDto session) throws Exception {
		String userId = session.getUserId();
		for (int i = 0; i < paramList.size(); i++) {
			CargoDto dto = paramList.get(i);
			if(dto.getJqFlag().equalsIgnoreCase(JqFlag.Insert)) {
				repository.insertCargo(dto, userId);
			} else if(dto.getJqFlag().equalsIgnoreCase(JqFlag.Update)) {
				repository.updateCargo(dto, userId);
			} else if(dto.getJqFlag().equalsIgnoreCase(JqFlag.Delete)) {
				repository.deleteCargo(dto);
			}
		}
	}
	
}
