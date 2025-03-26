package kc.logix.apps.management.inbound.service;

import org.springframework.stereotype.Service;

import kc.logix.apps.management.inbound.repository.InboundRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InboundService {

	private final InboundRepository repository;
	
}
