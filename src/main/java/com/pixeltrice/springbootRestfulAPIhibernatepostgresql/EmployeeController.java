package com.pixeltrice.springbootRestfulAPIhibernatepostgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping("/get-all-employees")
    public List<EmployeeEntity> getAllEmployee() {
        List<EmployeeEntity> allEmployeelist = employeeRepository.findAll();
        return allEmployeelist;

    }
        @GetMapping("/get-employee/{id}")
        public EmployeeEntity getEmployeebyId(@PathVariable(value = "id") Integer id) {
            EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

            return employeeEntity;

        }
            @PostMapping("/create-employees")
            public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {

            EmployeeEntity savedEmployee = employeeRepository.save(employee);

            return savedEmployee;

    }



    @PutMapping("/update-employees/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Integer id,
                                                         @RequestBody EmployeeEntity employeeDetails) {
        EmployeeEntity employee = employeeRepository.findById(id).get();

        employee.setEmailaddress(employeeDetails.getEmailaddress());
        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname(employeeDetails.getLastname());
        employee.setPhonenumber(employeeDetails.getPhonenumber());
        final EmployeeEntity updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete-employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer id)
    {
        EmployeeEntity employee = employeeRepository.findById(id).get();

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}