package spittr.web;

import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spittr.cluster.ClusteredInfo;

@RestController
@RequestMapping("/cluster")
@Profile("hz")
public class ClusteredController {
	
	@Inject
	ClusteredInfo clusteredInfo;

	@RequestMapping("/map/{map}")
	public String getMap(@PathVariable("map") String mapName){
		String maps = clusteredInfo.getMap(mapName).entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining(","));
		return "{"+maps+"}";
	}
	
	@RequestMapping("/map/put/{map}/{key}/{value}")
	public Object putMap(
			@PathVariable("map") String mapName, 
			@PathVariable("key") String key, 
			@PathVariable("value") String value){
		
		clusteredInfo.getMap(mapName).put(key, value);
		
		return getMap(mapName);
	}
	
}
