package com.sanskar.practice.repository;

import com.sanskar.practice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends JpaRepository<Employee,Integer> {
}
