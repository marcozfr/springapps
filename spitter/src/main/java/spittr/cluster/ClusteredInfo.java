package spittr.cluster;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Component
@Profile("hz")
public class ClusteredInfo {
	
	private HazelcastInstance instance; 
	
	public ClusteredInfo() {
		Config cfg = new Config();
		instance = Hazelcast.newHazelcastInstance(cfg);
	}
	
	public Map<Object,Object> getMap(String mapName){
		return instance.getMap(mapName);
	}

}
