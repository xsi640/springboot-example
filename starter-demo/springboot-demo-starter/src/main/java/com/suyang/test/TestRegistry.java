package com.suyang.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestRegistry implements ApplicationContextAware {

    private final Set<String> names = new HashSet<>();
    private ImmutableList<String> sortedNames;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        final Map<String, TestNameConfigurer> map = this.applicationContext.getBeansOfType(TestNameConfigurer.class);
        map.forEach((k, v) -> {
            v.addName(this);
        });
    }

    public TestRegistry addName(final String name) {
        this.sortedNames = null;
        this.names.add(name);
        return this;
    }

    public TestRegistry removeName(final String name) {
        this.sortedNames = null;
        this.names.remove(name);
        return this;
    }

    public ImmutableList<String> getNames() {
        if (this.sortedNames == null) {
            List<String> temp = Lists.newArrayList(this.sortedNames);
            sortNames(temp);
            this.sortedNames = ImmutableList.copyOf(temp);
        }
        return this.sortedNames;
    }

    public void sortNames(List<String> names) {
        names.sort(AnnotationAwareOrderComparator.INSTANCE);
    }
}
