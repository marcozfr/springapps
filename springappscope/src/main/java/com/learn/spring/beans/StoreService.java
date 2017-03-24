package com.learn.spring.beans;

import org.springframework.beans.factory.annotation.Value;

import com.learn.spring.interfaces.ShoppingCart;

public class StoreService {
    
    private ShoppingCart shoppingCart;
    
    @Value("#{ '${lang.default}' eq 'en' ? 'English' : 'Spanish'}")
    private String defLang;
    
    public void setLocalShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    public String getDefLang(){
        return defLang;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    
    
}
