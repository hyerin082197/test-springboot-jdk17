package com.ktds.mvp.springboot.employee.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktds.mvp.springboot.employee.domain.Employee;
import com.ktds.mvp.springboot.employee.domain.EmployeeSearch;

@Mapper
public interface EmployeeMapper {

    // Sample API
    public Employee findById(long id);

    // Grid
    public List<Employee> findBySearch(EmployeeSearch employeeSearch);
    
    // Chart
    public List<HashMap<String, Object>> findByChartGender(HashMap<String, Object> map);
    public List<HashMap<String, Object>> findByChartBloodType(HashMap<String, Object> map);
    public List<HashMap<String, Object>> findByChartColumnLine(HashMap<String, Object> map);
}
