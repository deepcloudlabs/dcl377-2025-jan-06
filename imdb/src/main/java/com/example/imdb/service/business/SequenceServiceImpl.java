package com.example.imdb.service.business;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.service.SequenceService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * 
 * @author Binnur Kurt
 *
 */
@ConditionalOnProperty(name="sequence",havingValue = "inmemory")
@Service
@RequestScope
public class SequenceServiceImpl implements SequenceService {
	private Map<String, AtomicLong> sequences = new ConcurrentHashMap<String, AtomicLong>();

	@PostConstruct
	public void init() {
		System.err.println("initializing sequence service...");
	}
	
	@PreDestroy
	public void destroy() {
		System.err.println("destroying sequence service...");
	}
	
	@Override
	public long nextId(String sequenceName) {
		return getAtomicLong(sequenceName).incrementAndGet();
	}

	@Override
	public String nextId(String sequenceName, String prefix) {
		return prefix + nextId(sequenceName);
	}

	@Override
	public long nextId(String sequenceName, int step) {
		return getAtomicLong(sequenceName).addAndGet(step);
	}

	@Override
	public String nextId(String sequenceName, String prefix, int step) {
		return prefix + nextId(sequenceName, step);
	}

	private AtomicLong getAtomicLong(String sequenceName) {
		AtomicLong atomicLong = sequences.get(sequenceName);
		if (atomicLong == null) {
			atomicLong = new AtomicLong(0);
			sequences.put(sequenceName, atomicLong);
		}
		return atomicLong;
	}
}
