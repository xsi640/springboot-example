package com.suyang.invocation.demo;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class WebServiceRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware {

    private final ClassPathScanner classpathScanner;
    private ClassLoader classLoader;
    private ApplicationContext applicationContext;

    public WebServiceRegistrar() {
        classpathScanner = new ClassPathScanner(false);
        classpathScanner.addIncludeFilter(new AnnotationTypeFilter(WebService.class));
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableWebService.class.getName()));
        assert attributes != null;
        String[] scanBasePackages = (String[]) attributes.get("basePackages");
        assert scanBasePackages != null;
        for (String basePackage : scanBasePackages) {
            registryRestRepository(basePackage, registry);
        }
    }

    private void registryRestRepository(String basePackage, BeanDefinitionRegistry registry) {
        Set<BeanDefinition> beanDefinitions = classpathScanner.findCandidateComponents(basePackage);
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                assert beanDefinition.getBeanClassName() != null;
                Class<?> clazz = classLoader.loadClass(beanDefinition.getBeanClassName());
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(WebServiceBeanFactory.class);
                builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                builder.addPropertyValue("classLoader", classLoader);
                builder.addPropertyValue("objectType", clazz);
                AbstractBeanDefinition bean = builder.getBeanDefinition();
                BeanDefinitionHolder holder = new BeanDefinitionHolder(bean, beanDefinition.getBeanClassName());
                BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

