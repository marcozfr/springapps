package springapp1;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.spring.config.SpringAppConfig;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringAppConfig.class)
//@ContextConfiguration({
//    "classpath:context.xml"
//})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TheKillersDiscTest extends TestCase {

//    @Autowired
    @Inject
    private CompactDisc compactDisc;
    
    @Autowired
    private MediaPlayer lgmediaPlayer;
    
    @Autowired
    private MediaPlayer samsungmediaPlayer;
    
    @Autowired
    private ApplicationContext appContext;
    
    @Test
    public void testConfig(){
        System.out.println(Arrays.asList( appContext.getBeanDefinitionNames()));
        assertTrue(true);
    }
    
    @Test
    public void assertDiscExists(){
        assertEquals(compactDisc.play(), "Playing... Day & Age n... 1");
    }
    
    @Test
    public void assertLgPlayerPlays(){
        assertEquals(lgmediaPlayer.play(compactDisc), "Playing... Day & Age n... 2");
    }
    
    @Test
    public void assertSamsungPlayerPlays(){
        assertEquals(samsungmediaPlayer.play(compactDisc), "Playing... Day & Age n... 3");
    }
    
}
