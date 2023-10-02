package hh.service;

import hh.entity.Employee;
import hh.exception.CustomsException;
import hh.model.Roles;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class IEmployeeServiceIml implements IEmployeeService<Employee, Long> {
    private static ArrayList<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(1L, "DOM", "MarkDommm@gmail.com", "CEO", Collections.singletonList(String.valueOf(Roles.ROLE_ADMIN))));
        employees.add(new Employee(2L, "John", "John1999@gmail.com", "Java Developer", Collections.singletonList(String.valueOf(Roles.ROLE_USER))));
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean checkExistsByMail(String email) throws CustomsException {
        for (Employee e : employees) {
            if (e.getEmail().equals(email)) {
                throw new CustomsException("Employee Email is Exists!!!");
            }
        }
        return true;
    }

    public boolean checkExistsByRole(List<String> roles) throws CustomsException {
        for (String roleEmployee : roles) {
            boolean roleFound = false;

            for (Roles r : Roles.values()) {
                if (r.name().equals(roleEmployee)) {
                    roleFound = true;
                    break;
                }
            }

            if (!roleFound) {
                throw new CustomsException("Roles not found: " + roleEmployee);
            }
        }

        return true;
    }



    @Override
    public void save(Employee employee) throws CustomsException {
        if (checkExistsByMail(employee.getEmail()) && checkExistsByRole(employee.getRoles())) {
            Long newId = getNewId();
            employees.add(new Employee(newId, employee.getName(), employee.getEmail(), employee.getDepartment(), employee.getRoles()));
        }

    }

    @Override
    public void update(Employee employee, Long id) throws CustomsException {
        if (checkExistsByMail(employee.getEmail())) {
            Employee selectEmployee = findById(id);
            selectEmployee.setName(employee.getName());
            selectEmployee.setEmail(employee.getEmail());
            selectEmployee.setRoles(employee.getRoles());
            selectEmployee.setDepartment(employee.getDepartment());
        }

    }


    @Override
    public Employee findById(Long id) throws CustomsException {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        throw new CustomsException("Employee not found");
    }

    @Override
    public void remove(Long id) throws CustomsException {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employees.remove(employee);
            }
        }
        throw new CustomsException("Employee not found");
    }

    public Long getNewId() {
        Long maxId = 0L;
        for (Employee employee : employees) {
            if (employee != null && employee.getId() > maxId) {
                maxId = employee.getId();
            }
        }
        return maxId + 1;
    }
}
