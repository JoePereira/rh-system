package com.joe.rh_system.Service;

import com.joe.rh_system.DTO.DepartmentDTO;
import com.joe.rh_system.Model.Department;
import com.joe.rh_system.Repository.DepartmentRepository;
import com.joe.rh_system.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository){
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<DepartmentDTO> listAll() {
        return departmentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public DepartmentDTO save(DepartmentDTO dto){
        Department department = toEntity(dto);

        departmentRepository.save(department);
        return toDTO(department);
    }

    private DepartmentDTO toDTO(Department d){
        DepartmentDTO dto = new DepartmentDTO();

        dto.id = d.getId();
        dto.name = d.getName();
        dto.accountable = d.getAccountable();

        return dto;
    }

    private Department toEntity(DepartmentDTO dto){
        Department department = new Department();

        department.setName(dto.name);
        department.setAccountable(dto.accountable);

        return department;
    }
}
