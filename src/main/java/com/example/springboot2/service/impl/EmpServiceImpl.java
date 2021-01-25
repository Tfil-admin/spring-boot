package com.example.springboot2.service.impl;


import com.example.springboot2.dao.EmpDao;
import com.example.springboot2.dao.JsonData;
import com.example.springboot2.pojo.EmpInfo;
import com.example.springboot2.service.EmpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    public JsonData lisEmp() {
//        PageHelper.startPage(page, limit);
//        PageInfo<EmpInfo> pageInfo=new PageInfo<EmpInfo>(integerEmpInfoMap);

        Collection<EmpInfo> empInfos = empDao.lisEmp();
        return JsonData.buildSuccess(empInfos,"success");

    }

    @Override
    public JsonData saveEmp(EmpInfo emp) {
                empDao.saveEmp(emp);

        return JsonData.buildSuccess("success");
    }

    @Override
    public JsonData delEmp(Integer empId) {
        empDao.delete(empId);

        return JsonData.buildSuccess("success");
    }


}
