package com.sanskar.practice.controller;

import com.sanskar.practice.model.Employee;
import com.sanskar.practice.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class Controller {

    private EmpService empService;

    public Controller(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public String getRequest(){
    return "hello Sanskar";
}

@PostMapping()
    public Employee postRequest(@RequestBody Employee e){
       return empService.createEmployee(e);
}

}
