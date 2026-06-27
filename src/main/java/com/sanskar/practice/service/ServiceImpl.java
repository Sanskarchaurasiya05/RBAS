package com.sanskar.practice.service;

import com.sanskar.practice.model.Employee;
import com.sanskar.practice.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements EmpService{

    @Autowired
    private EmpRepository empRepository;
    @Override
    public Employee createEmployee(Employee e) {
       return empRepository.save(e);
    }
}
