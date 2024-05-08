package com.session.mvc;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.session.mvc.model.Employee;
import com.session.mvc.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired(required=true)
	@Qualifier("employeeServiceImpl")
	public EmployeeService employeeService;
	
	
//	@Qualifier(value="employeeService")
//	public void setEmployeeService(EmployeeService ps){
//		this.employeeService = ps;
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showEmployees(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", this.employeeService.listEmployees());
		return "employee";
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listemployees(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", this.employeeService.listEmployees());
		return "employee";
	}
	
	//For add and update employee both
	@RequestMapping(value= "/employee/add", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") Employee p){
		
		if(p.getId() == 0){
			//new employee, add it
			this.employeeService.addEmployee(p);
		}else{
			//existing employee, call update
			this.employeeService.updateEmployee(p);
		}
		
		return "redirect:/employees";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeemployee(@PathVariable("id") int id){
		
        this.employeeService.removeEmployee(id);
        return "redirect:/employees";
    }
 
    @RequestMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model){
        model.addAttribute("employee", this.employeeService.getEmployeeById(id));
        model.addAttribute("listEmployees", this.employeeService.listEmployees());
        return "employee";
    }
	
}