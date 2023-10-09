package net.javaguides.ems.repositories;

import net.javaguides.ems.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}