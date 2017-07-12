package springapp1;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.spring.config.SpringAppConfig;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MemoryRecord;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringAppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("qa")
public class CompactDiscTest extends TestCase {

    @Autowired(required=false)
    MemoryRecord memoryRecord;
    
    @Autowired
    private CompactDisc compactDisc;
    
    @Test
    public void testConfig(){
        //System.out.println(Arrays.asList(appContext.getBean("compactDisc")));
        assertTrue(true);
    }
    
    @Test
    public void assertDiscExists(){
//        assertEquals(compactDisc.play(), "Playing... Day & Age");
    	assertThat(compactDisc.play(),CoreMatchers.equalTo("Playing... Day & Age"));
    }
    
    @Test
    public void assertRecordOnPlayback(){
        //assertNull(memoryRecord); // when p rofile is default
        assertNotNull(memoryRecord); // when profile is qa
    }
    
}
