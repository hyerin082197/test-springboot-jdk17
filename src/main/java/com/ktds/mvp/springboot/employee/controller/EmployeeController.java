package com.ktds.mvp.springboot.employee.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.mvp.springboot.employee.domain.Employee;
import com.ktds.mvp.springboot.employee.domain.EmployeeSearch;
import com.ktds.mvp.springboot.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;

	@GetMapping(value = "/{id}")
	public Employee findEmployeeById(@PathVariable long id) {
		return employeeService.findById(id);
	}

	@PostMapping(value = "/search")
	public List<Employee> findEmployeeBySearch(@RequestBody EmployeeSearch keyword) {
		return employeeService.findBySearch(keyword);
	}

	@PostMapping(value = "/chart/gender")
	public List<HashMap<String, Object>> findEmployeeChartByGender(@RequestBody HashMap<String, Object> map) {
		return employeeService.findByChartGender(map);
	}

	@PostMapping(value = "/chart/bloodtype")
	public List<HashMap<String, Object>> findEmployeeChartByBloodType(@RequestBody HashMap<String, Object> map) {
		return employeeService.findByChartBloodType(map);
	}

	@PostMapping(value = "/chart/combination")
	public List<HashMap<String, Object>> findEmployeeChartByCombination(@RequestBody HashMap<String, Object> map) {
		return employeeService.findByChartColumnLine(map);
	}
}
