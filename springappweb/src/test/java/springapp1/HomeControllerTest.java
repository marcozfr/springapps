package springapp1;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import com.learn.spring.config.RootConfig;
import com.learn.spring.data.beans.Spittle;
import com.learn.spring.data.service.SpittleRepository;
import com.learn.spring.web.config.WebConfig;
import com.learn.spring.web.controller.HomeController;
import com.learn.spring.web.controller.SpitterController;
import com.learn.spring.web.controller.SpittleController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WebConfig.class,RootConfig.class})
@WebAppConfiguration
@ActiveProfiles("mock")
public class HomeControllerTest {
    
    @Test
    public void testHomePage() throws Exception{
      HomeController homeController = new HomeController();
      MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
      mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
    
    @Test
    public void testShowRecentSpittles() throws Exception{
        
        List<Spittle> expectedSpittles = mockSpittleList(20);
        
        SpittleRepository mockRepository = mock(SpittleRepository.class);
            when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).
            thenReturn(expectedSpittles);
        
        SpittleController spittlerController = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittlerController).
                setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).
                build();
        
        mockMvc.perform(get("/spittles")).
            andExpect(view().name("spittles")).
            andExpect(model().attributeExists("spittleList")).
            andExpect(model().attribute("spittleList",hasItems(expectedSpittles.toArray())));
        
    }
    
    @Test
    public void testShowPagedSpittles() throws Exception{
        
        List<Spittle> expectedSpittles = mockSpittleList(50);
        
        SpittleRepository mockRepository = mock(SpittleRepository.class);
            when(mockRepository.findSpittles(Long.MAX_VALUE, 50)).
            thenReturn(expectedSpittles);
        
        SpittleController spittleController = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittleController).
                setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).
                build();
        
        mockMvc.perform(get("/spittles?max=238900&count=50")).
            andExpect(view().name("spittles")).
            andExpect(model().attributeExists("spittleList")).
            andExpect(model().attribute("spittleList",hasItems(expectedSpittles.toArray())));
        
    }
    
    @Test
    public void testSpittle() throws Exception{
        
        Spittle spittle = new Spittle("cool", new Date());
        
        SpittleRepository mockRepository = mock(SpittleRepository.class);
            when(mockRepository.findOne(2)).thenReturn(spittle);
        
        SpittleController spittleController = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittleController).
                setSingleView(new InternalResourceView("/WEB-INF/views/spittle.jsp")).
                build();
        
        mockMvc.perform(get("/spittles/2")).
            andExpect(view().name("spittle")).
            andExpect(model().attributeExists("spittle")).
            andExpect(model().attribute("spittle",spittle));
        
    }
    
    @Test
    public void testRegisterForm() throws Exception{
        SpitterController spitterController = new SpitterController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spitterController).
                setSingleView(new InternalResourceView("/WEB-INF/views/registerForm.jsp")).
                build();
        
        mockMvc.perform(get("/spitter/register"))
            .andExpect(view().name("registerForm"));
    }
    
    public List<Spittle> mockSpittleList(int count){
        List<Spittle> list = new ArrayList<>();
        new Thread(){
            public void run() {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                };
            };
        };
        try{
            
        }finally{
            
        }
        for (int i = 0; i < list.size(); i++) {
            list.add(new Spittle("Spittle x", new Date()));
        }
        return list;
    }

}
