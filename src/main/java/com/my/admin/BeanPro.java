package com.my.admin;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * spring启动时，在初始化bean之前所做的事情
 */
@Configuration
public class BeanPro implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        /*实现BeanDefinition实例化之前对BeanDefinition的删除操作*/
//        String[] beanDefinitionNames = beanDefinitionRegistry.getBeanDefinitionNames();
//        for (String beanName : beanDefinitionNames) {
//            beanDefinitionRegistry.removeBeanDefinition(beanName);
//        }
        /*实现BeanDefinition实例化之前对BeanDefinition的新增操作*/
//        // TODO: 2020/12/16 类中的@bean没有执行，等待研究
//        BeanDefinition beanDefinition = new RootBeanDefinition(InsertBean.class);
//        beanDefinitionRegistry.registerBeanDefinition("insertBean", beanDefinition);
        /*实现BeanDefinition实例化之前对BeanDefinition的更新操作*/
//        //对象要实现，get、set方法，才能拿到对应属性，并修改
//        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition("accountService");
//        MutablePropertyValues ds = beanDefinition.getPropertyValues();
//        ds.addPropertyValue("name", "1");
//        beanDefinitionRegistry.registerBeanDefinition("accountService", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("多少个："+configurableListableBeanFactory.getBeanDefinitionCount());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
