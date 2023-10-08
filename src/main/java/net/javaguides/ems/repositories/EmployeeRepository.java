package net.javaguides.ems.repositories;

import net.javaguides.ems.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}