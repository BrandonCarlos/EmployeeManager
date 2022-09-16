package tech.getarrays.employeemanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.getarrays.employeemanager.exception.UserNotFoundException;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repository.EmployeeRepo;

@Service
public class EmployeeService {//Este arquivo seria nosso Controller que aprendemos anteriormente
	private final EmployeeRepo employeeRepo; 
	
	@Autowired
	public EmployeeService(EmployeeRepo employeeRepo) {//para criar um objeto do EmployeeService é necessário passar o EmployeeRepo, e assim fazer operações no banco de dados
		this.employeeRepo = employeeRepo;
	}
	
	public Employee addEmployee(Employee employee) {//aqui estamos contando com que o usuario envie o "employee" com todos os dados preenchidos menos o "codeEmployee"
		employee.setEmployeeCode(UUID.randomUUID().toString());//UUID feito para gerar um número randomico e convertendo para String
		return employeeRepo.save(employee);//dando um INSERT de um novo Employee dentro do banco de dados
	}
	
	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public Employee findEmployeeById(Long id) {
		return employeeRepo.findEmployeeById(id)
				.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
	}
	
	public void deleteEmployee(Long id) {
		employeeRepo.deleteEmployeeById(id);
	}
	
}
