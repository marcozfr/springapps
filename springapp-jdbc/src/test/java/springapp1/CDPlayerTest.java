package springapp1;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.spring.config.SpringAppConfig;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringAppConfig.class)
public class CDPlayerTest {

	@Autowired
    @Qualifier("lgCDPlayer")
    private MediaPlayer lgmediaPlayer;
    
    @Autowired 
    @Qualifier("samsungCDPlayer")
    private MediaPlayer samsungmediaPlayer;
    
    @Autowired
    private CompactDisc compactDisc;
    
    @Test
    public void assertLgPlayerPlays(){
        assertEquals(lgmediaPlayer.play(compactDisc), "Playing... Day & Age on lg");
    }
    
    @Test
    public void assertSamsungPlayerPlays(){
        assertEquals(samsungmediaPlayer.play(compactDisc), "Playing... Day & Age on samsung");
    }
    
    @Test
    public void verifyContents(){
    	List<String> myList = Arrays.asList("LG", "Samsung", "Daewoo", "Oster");
    	assertThat(myList,hasItems("LG", "Daewoo"));
    }
	
}
