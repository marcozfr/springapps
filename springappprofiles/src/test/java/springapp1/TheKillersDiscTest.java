package springapp1;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.spring.beans.CDMemoryRecord;
import com.learn.spring.config.SpringAppConfig;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;
import com.learn.spring.interfaces.MemoryRecord;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringAppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("qa")
public class TheKillersDiscTest extends TestCase {

    @Autowired(required=false)
    MemoryRecord memoryRecord;
    
    @Autowired
    private CompactDisc compactDisc;
    
    @Autowired
    @Qualifier("lgCDPlayer")
    private MediaPlayer lgmediaPlayer;
    
    @Autowired 
    @Qualifier("samsungCDPlayer")
    private MediaPlayer samsungmediaPlayer;
    
    @Autowired
    private ApplicationContext appContext;
    
    @Test
    public void testConfig(){
        //System.out.println(Arrays.asList(appContext.getBean("compactDisc")));
        assertTrue(true);
    }
    
    @Test
    public void assertDiscExists(){
        assertEquals(compactDisc.play(), "Playing... Day & Age");
    }
    
    @Test
    public void assertLgPlayerPlays(){
        assertEquals(lgmediaPlayer.play(compactDisc), "Playing... Day & Age on lg");
    }
    
    @Test
    public void assertSamsungPlayerPlays(){
        assertEquals(samsungmediaPlayer.play(compactDisc), "Playing... Day & Age on samsung");
    }
    
    @Test
    public void assertRecordOnPlayback(){
        //assertNull(memoryRecord); // when p rofile is default
        assertNotNull(memoryRecord); // when profile is qa
    }
    
}
