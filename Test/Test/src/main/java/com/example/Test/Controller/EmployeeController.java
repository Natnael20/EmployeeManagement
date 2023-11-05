package com.example.Test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Test.DTO.EmployeeDto;
import com.example.Test.Service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/Employee")
public class EmployeeController {

	@Autowired
	public EmployeeServiceImpl employeeServiceImpl;

	public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
		super();
		this.employeeServiceImpl = employeeServiceImpl;
	}
	
	@GetMapping("/index")
	public String mainPage() {
		return "index";
	}
	
	 @GetMapping
	    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
	        List<EmployeeDto> employees = employeeServiceImpl.findAllEmployees();
	        return ResponseEntity.ok(employees);
	    }

	    @GetMapping("/{employeeId}")
	    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable(value = "id") Long employeeId) {
	        EmployeeDto employee = employeeServiceImpl.findEmployeeById(employeeId);
	        if (employee != null) {
	            return ResponseEntity.ok(employee);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping
	    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
	        EmployeeDto createdEmployee = employeeServiceImpl.createEmployee(employeeDto);
	        return ResponseEntity.ok(createdEmployee);
	    }

	    @PutMapping("/{employeeId}")
	    public ResponseEntity<EmployeeDto> updateEmployee(
	            @PathVariable(value = "id") Long employeeId,
	            @RequestBody EmployeeDto updatedEmployeeDto
	    ) {
	        EmployeeDto updatedEmployee = employeeServiceImpl.updateEmployee(employeeId, updatedEmployeeDto);
	        if (updatedEmployee != null) {
	            return ResponseEntity.ok(updatedEmployee);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    @DeleteMapping("/{employeeId}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
	    	employeeServiceImpl.deleteEmployee(employeeId);
	        return ResponseEntity.noContent().build();
	    }
	
}
