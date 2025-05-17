package kc.logix.apps.history.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kc.logix.apps.history.dto.HistoryDto;
import kc.logix.apps.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryService {

	private final HistoryRepository repository;
	
	/**
	 * 
	 * @param paramDto
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<HistoryDto.CargoDto> selectCargo(HistoryDto.CargoDto paramDto) throws Exception {
		return repository.selectCargo(paramDto);
	}
}
