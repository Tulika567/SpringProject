package com.session.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.session.mvc.dao.EmployeeDAO;
import com.session.mvc.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	public EmployeeDAO employeeDAO;
	

	@Qualifier("employeeDAOImpl")
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public void addEmployee(Employee p) {
		this.employeeDAO.addEmployee(p);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee p) {
		this.employeeDAO.updateEmployee(p);
	}

	@Override
	@Transactional
	public List<Employee> listEmployees() {
		return this.employeeDAO.listEmployees();
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		return this.employeeDAO.getEmployeeById(id);
	}

	@Override
	@Transactional
	public void removeEmployee(int id) {
		this.employeeDAO.removeEmployee(id);
	}

}