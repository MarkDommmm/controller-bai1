package hh.controller;


import hh.entity.Employee;
import hh.exception.CustomsException;
import hh.service.IEmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employee")
public class HomeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeesById(@PathVariable Long id) throws CustomsException {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) throws CustomsException {
        employeeService.save(employee);
        return new ResponseEntity<>("Create Employee Success!!!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) throws CustomsException {
        employeeService.update(employee, id);
        return new ResponseEntity<>("Update employee success!!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) throws CustomsException {
        employeeService.remove(id);
        return new ResponseEntity<>("Delete employee success!!!", HttpStatus.OK);
    }
}
