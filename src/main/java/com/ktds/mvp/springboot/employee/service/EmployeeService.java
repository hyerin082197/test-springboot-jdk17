package com.ktds.mvp.springboot.employee.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ktds.mvp.springboot.employee.domain.Employee;
import com.ktds.mvp.springboot.employee.domain.EmployeeSearch;
import com.ktds.mvp.springboot.employee.repository.EmployeeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    public Employee findById(long id) {
        log.debug("Employee ID", id);
        return employeeMapper.findById(id);
    }

    public List<Employee> findBySearch(EmployeeSearch employeeSearch) {
        log.debug("Employee Search payload", employeeSearch);
        return employeeMapper.findBySearch(employeeSearch);
    }

    public List<HashMap<String, Object>> findByChartGender(HashMap<String, Object> map) {
        return employeeMapper.findByChartGender(map);
    }

    public List<HashMap<String, Object>> findByChartBloodType(HashMap<String, Object> map) {
        return employeeMapper.findByChartBloodType(map);
    }
    public List<HashMap<String, Object>> findByChartColumnLine(HashMap<String, Object> map) {
        return employeeMapper.findByChartColumnLine(map);
    }
}
