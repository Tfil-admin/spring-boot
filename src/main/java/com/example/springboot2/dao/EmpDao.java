package com.example.springboot2.dao;

import com.example.springboot2.pojo.EmpInfo;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmpDao {
    private static Map<Integer, EmpInfo> empInfoMap=null;
    private EmpInfo empInfo = new EmpInfo();

//    public static void main(String[] args) {
//        lisEmp();
//    }

    static{
        empInfoMap=new HashMap<Integer, EmpInfo>();
        empInfoMap.put(1,new EmpInfo(1,"张三",18));
        empInfoMap.put(2,new EmpInfo(2,"张三1",19));
        empInfoMap.put(3,new EmpInfo(3,"张三2",20));
    }

    public  Collection<EmpInfo> lisEmp(){

      //  System.out.println( empInfoMap.values());

        return empInfoMap.values();

    }

    private static Integer Eid = 4;
        public  void saveEmp (EmpInfo emp){

            if (emp.getEmpId()==null){
                emp.setEmpId(Eid++);
            }
            empInfoMap.put(emp.getEmpId(),emp);

        }

        public void delete(Integer empId){
            empInfoMap.remove(empId);
        }

}
