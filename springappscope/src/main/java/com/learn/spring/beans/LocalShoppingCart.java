package com.learn.spring.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.learn.spring.interfaces.ShoppingCart;

@Component
//@Scope(value=WebApplicationContext.SCOPE_SESSION,
//       proxyMode=ScopedProxyMode.INTERFACES)
public class LocalShoppingCart implements ShoppingCart{
    
    @Value("${taxes.applied}")
    private boolean taxesApply;
    
    public boolean isTaxesApply() {
        return taxesApply;
    }
    
    
    
}
