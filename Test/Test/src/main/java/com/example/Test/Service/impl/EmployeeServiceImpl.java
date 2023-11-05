package com.example.Test.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.example.Test.DTO.EmployeeDto;
import com.example.Test.Entity.Employee;
import com.example.Test.Repository.EmployeeRepository;
import com.example.Test.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto mapEmployeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastname());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDtos.add(mapEmployeeToDto(employee));
        }

        return employeeDtos;
    }

    @Override
    public EmployeeDto findEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return mapEmployeeToDto(employee);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastname(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());

        employee = employeeRepository.save(employee);

        return mapEmployeeToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);

        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = existingEmployee.get();
            employeeToUpdate.setFirstName(updatedEmployeeDto.getFirstName());
            employeeToUpdate.setLastname(updatedEmployeeDto.getLastName());
            employeeToUpdate.setEmail(updatedEmployeeDto.getEmail());
            employeeToUpdate.setPhoneNumber(updatedEmployeeDto.getPhoneNumber());

            employeeToUpdate = employeeRepository.save(employeeToUpdate);

            return mapEmployeeToDto(employeeToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
