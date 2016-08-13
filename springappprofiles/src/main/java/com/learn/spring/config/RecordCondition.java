package com.learn.spring.config;

import java.util.Arrays;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RecordCondition implements Condition {

    @Override
    public boolean matches(ConditionContext arg0, AnnotatedTypeMetadata arg1) {
        System.out.println(arg0.getEnvironment().toString() + "##");
        return Arrays.asList(arg0.getEnvironment().getActiveProfiles()).contains("qa");
    }
    
}
