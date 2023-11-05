package com.example.Test.Service;

import java.util.List;

import com.example.Test.DTO.EmployeeDto;
import com.example.Test.Entity.Employee;

public interface EmployeeService {
	
	// Method names should follow Java naming conventions.
	//CRUD operation
	
	List<EmployeeDto> findAllEmployees();

    EmployeeDto findEmployeeById(Long employeeId);

    EmployeeDto createEmployee(EmployeeDto employee);

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeId);



}
