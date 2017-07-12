package com.example.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class ChunkCompletionNotifListener implements ChunkListener{

	private static final Logger log = LoggerFactory.getLogger(ChunkCompletionNotifListener.class);
	
	@Override
	public void beforeChunk(ChunkContext context) {
		log.info("Starting chunk from " + context.getStepContext().getStepExecution().getWriteCount());
	}

	@Override
	public void afterChunk(ChunkContext context) {
		log.info("Finishing chunk " + context.getStepContext().getStepExecution().getWriteCount());
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		log.warn("Error on chunk.");
	}

}
