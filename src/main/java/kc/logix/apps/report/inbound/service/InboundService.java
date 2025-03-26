package kc.logix.apps.report.inbound.service;

import org.springframework.stereotype.Service;

import kc.logix.apps.report.inbound.repository.InboundRepository;
import lombok.RequiredArgsConstructor;

@Service(value = "RInboundService")
@RequiredArgsConstructor
public class InboundService {

	private final InboundRepository repository;
	
}
