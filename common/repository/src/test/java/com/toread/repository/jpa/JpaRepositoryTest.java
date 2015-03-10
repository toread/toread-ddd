package com.toread.repository.jpa;

import com.toread.ioc.factory.InstanceFactory;
import com.toread.ioc.provider.SpringInstanceProvider;
import com.toread.repository.SpringConfig;
import com.toread.repository.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@TestExecutionListeners(listeners={
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class JpaRepositoryTest {
    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }
    @Autowired
    protected ApplicationContext ctx;

    private JpaRepository repository;

    @Before
    public void init() {
        SpringInstanceProvider springInstanceProvider = new SpringInstanceProvider(ctx);
        InstanceFactory.setInstanceProvider(springInstanceProvider);
        repository = new JpaRepository();
    }

    @Test
    @Transactional
    public void testAdd() {
        Person person = new Person();
        person.setName("传奇世界");
        repository.add(person);
        Person person1 = new Person();
        person1.setName("传奇世界");
        repository.add(person1);
        Person person2 = new Person();
        person2.setName("传奇世界");
        repository.add(person2);
        throw new IllegalArgumentException();
    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testLoad() throws Exception {

    }

    @Test
    public void testCriteriaQuery() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testEntityManager() throws Exception {

    }
}