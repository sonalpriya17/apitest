package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.IObjectFactory;
import org.testng.ITestObjectFactory;
import org.testng.annotations.ObjectFactory;

import java.lang.reflect.Constructor;

@Component
public class SpringObjectFactory implements IObjectFactory, ITestObjectFactory {

    @Autowired
    private ApplicationContext context;

    @Override
    public Object newInstance(Constructor constructor, Object... params) {
        return context.getBean(constructor.getDeclaringClass());
    }

    @ObjectFactory
    public ITestObjectFactory getObjectFactory() {
        return this;
    }
}
