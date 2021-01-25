package com.example.springboot2.service;

import com.example.springboot2.dao.JsonData;
import com.example.springboot2.pojo.EmpInfo;

public interface EmpService {
    JsonData lisEmp();

//    JsonData delEmp(int empId);
//
//
    JsonData saveEmp(EmpInfo emp);

    JsonData  delEmp(Integer empId);

}
