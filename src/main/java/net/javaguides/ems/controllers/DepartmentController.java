package net.javaguides.ems.controllers;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "id") Long departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable(name = "id") Long departmentId,
            @RequestBody DepartmentDto updatedDepartmentDto
    ) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, updatedDepartmentDto));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<DepartmentDto> deleteDepartmentById(@PathVariable(name = "id") Long departmentId) {
        return ResponseEntity.ok(departmentService.deleteDepartmentById(departmentId));
    }

}