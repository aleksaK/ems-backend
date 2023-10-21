package net.javaguides.ems.services;

import lombok.AllArgsConstructor;
import net.javaguides.ems.domain.Department;
import net.javaguides.ems.domain.Employee;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.exceptions.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.javaguides.ems.mapper.DepartmentMapper.*;
import static net.javaguides.ems.mapper.EmployeeMapper.*;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final String MESSAGE = "No employee found with given ID: ";

    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = toEmployee(employeeDto);
        Department department = getDepartmentById(employeeDto);

        employee.setDepartment(department);

        return toEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        return toEmployeeDto(getById(employeeId));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toEmployeeDto)
                .toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = getById(employeeId);
        Department department = getDepartmentById(updatedEmployee);

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(department);

        employeeRepository.save(employee);
        return toEmployeeDto(employee);
    }

    @Override
    public EmployeeDto deleteEmployeeById(Long employeeId) {
        Employee employee = getById(employeeId);
        employeeRepository.delete(employee);
        return toEmployeeDto(employee);
    }

    private Employee getById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + employeeId));
    }

    private Department getDepartmentById(EmployeeDto employeeDto) {
        return toDepartment(departmentService.getDepartmentById(employeeDto.getDepartmentId()));
    }

}