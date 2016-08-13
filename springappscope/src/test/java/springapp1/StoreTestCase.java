package springapp1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.spring.beans.LocalShoppingCart;
import com.learn.spring.beans.StoreService;
import com.learn.spring.config.SpringAppConfig;
import com.learn.spring.interfaces.ShoppingCart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringAppConfig.class)
public class StoreTestCase {

    @Inject
    StoreService storeService;
    
    @Autowired 
    ShoppingCart shoppingCart;
    
    @Test
    public void test() {
        assertTrue(true);
    }
    
    @Test
    public void testDefLang() throws Exception {
        assertEquals("English",storeService.getDefLang()); 
    }
    
    @Test
    public void testShoppingCarTaxes() throws Exception {
        assertTrue(shoppingCart.isTaxesApply());
    }

}
