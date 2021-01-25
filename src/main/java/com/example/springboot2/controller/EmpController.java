package com.example.springboot2.controller;

import com.example.springboot2.dao.JsonData;
import com.example.springboot2.pojo.EmpInfo;
import com.example.springboot2.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("/lisEmp")
    public JsonData lisEmp(){
        JsonData jsonData = empService.lisEmp();
        return jsonData;
    }

    @RequestMapping("/saveEmp")
    public JsonData saveEmp(EmpInfo emp){
        JsonData jsonData = empService.saveEmp(emp);
        return jsonData;

    }

    @RequestMapping("/delEmp")
    public JsonData delEmp(Integer empId){
        JsonData delete = empService.delEmp(empId);
        return delete;
    }
}
