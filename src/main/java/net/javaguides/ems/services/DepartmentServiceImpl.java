package net.javaguides.ems.services;

import lombok.AllArgsConstructor;
import net.javaguides.ems.domain.Department;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.exceptions.ResourceNotFoundException;
import net.javaguides.ems.mapper.DepartmentMapper;
import net.javaguides.ems.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.javaguides.ems.mapper.DepartmentMapper.*;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final String MESSAGE = "No department found with given ID: ";

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = toDepartment(departmentDto);
        return toDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        return toDepartmentDto(getById(departmentId));
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toDepartmentDto)
                .toList();
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department = getById(departmentId);

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());

        return toDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto deleteDepartmentById(Long departmentId) {
        Department department = getById(departmentId);
        departmentRepository.delete(department);
        return toDepartmentDto(department);
    }

    private Department getById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + departmentId));
    }

}