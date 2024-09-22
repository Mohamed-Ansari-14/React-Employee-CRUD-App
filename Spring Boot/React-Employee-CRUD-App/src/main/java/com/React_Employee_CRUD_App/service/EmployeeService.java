package com.React_Employee_CRUD_App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.React_Employee_CRUD_App.entity.Employee;
import com.React_Employee_CRUD_App.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public Employee postEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public void deleteEmployee(Long id) {
		if(!employeeRepository.existsById(id)) {
			throw new EntityNotFoundException("Employee with Id: "+ id + " Not Found");
		}
		
		employeeRepository.deleteById(id);
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(optionalEmployee.isPresent()) {
			Employee existingEmployee = optionalEmployee.get();
			
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setName(employee.getName());
			existingEmployee.setPhone(employee.getPhone());
			existingEmployee.setDepartment(employee.getDepartment());
			
			return employeeRepository.save(existingEmployee);
		}
		return null;
	}
}

















