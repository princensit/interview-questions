package com.prince.spring.database_propagation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prince.spring.database_propagation.dao.DatabaseDao;

/**
 * @author Prince Raj
 */
@Service
public class Service1 {

    @Autowired
    private DatabaseDao databaseDao;

    @Autowired
    private Service2 service2;

    @Transactional
    public void method1() {
        databaseDao.insertName();

        service2.method2();
    }
}
