package com.example.spring.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FlatFilePartitioner implements Partitioner {

	private String classPathFile;

	public String getClassPathFile() {
		return classPathFile;
	}

	public void setClassPathFile(String classPathFile) {
		this.classPathFile = classPathFile;
	}

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {

		int count = 0;
		Map<String, ExecutionContext> ctxs = null;
		Resource resource = new ClassPathResource(classPathFile);
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
			while ((bufferedReader.readLine()) != null) {
				count++;
			}
			
			int off = count/gridSize;
			int i = 0;
			int reads = off;
			ctxs = new HashMap<>();
			while (i < gridSize) {
				ExecutionContext ctx = new ExecutionContext();
				ctx.putInt("lineOffset", reads);
				ctx.putString("fileName", classPathFile);
				reads += off;
				ctxs.put("partition"+i, ctx);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ctxs;
		
	}

}
