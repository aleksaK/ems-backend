package net.javaguides.ems.mapper;

import net.javaguides.ems.domain.Department;
import net.javaguides.ems.dto.DepartmentDto;

public class DepartmentMapper {

    public static DepartmentDto toDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    public static Department toDepartment(DepartmentDto departmentDto) {
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }

}