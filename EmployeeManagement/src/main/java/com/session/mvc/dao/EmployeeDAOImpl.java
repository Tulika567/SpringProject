package com.session.mvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.session.mvc.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addEmployee(Employee p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		System.out.println("Employee saved successfully, Employee Details="+p);
//		logger.info("Employee saved successfully, Employee Details="+p);
	}

	@Override
	public void updateEmployee(Employee p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		System.out.println("Employee updated successfully, Employee Details="+p);
//		logger.info("Employee updated successfully, Employee Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployees() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> employeesList = session.createQuery("from Employee").list();
		for(Employee p : employeesList){
			System.out.println("Employee List::"+p);
//			logger.info("Employee List::"+p);
		}
		return employeesList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		System.out.println("Employee loaded successfully, Employee details="+p);
//		logger.info("Employee loaded successfully, Employee details="+p);
		return p;
	}

	@Override
	public void removeEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		System.out.println("Employee deleted successfully, employee details="+p);
//		logger.info("Employee deleted successfully, employee details="+p);
	}

}
