package com.joe.rh_system.Service;

import com.joe.rh_system.DTO.EmployeeDTO;
import com.joe.rh_system.DTO.RegisterEmployeeDTO;
import com.joe.rh_system.Exception.ResourceNotFoundException;
import com.joe.rh_system.Model.Department;
import com.joe.rh_system.Model.Employee;
import com.joe.rh_system.Repository.DepartmentRepository;
import com.joe.rh_system.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<EmployeeDTO> listAll() {
        return employeeRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));
        return toDTO(employee);
    }

    public EmployeeDTO save(RegisterEmployeeDTO dto){
        if (!dto.password.equals(dto.confirmPassword)) {
            throw new IllegalArgumentException("As senhas não coincidem.");
        }

        departmentRepository.findById(dto.departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado"));

        Employee employee = toEntity(dto);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setPassword(encoder.encode(dto.password));

        employeeRepository.save(employee);
        return toDTO(employee);
    }


    public EmployeeDTO update(Long id, EmployeeDTO dto){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        employee.setName(dto.name);
        employee.setEmail(dto.email);
        employee.setPosition(dto.position);
        employee.setSalary(dto.salary);
        employee.setHireDate(dto.hireDate);
        employee.setDepartment(departmentRepository.findById(dto.departmentId).orElse(null));

        employeeRepository.save(employee);
        return toDTO(employee);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO toDTO(Employee e){
        EmployeeDTO dto = new EmployeeDTO();

        dto.id = e.getId();
        dto.name = e.getName();
        dto.email = e.getEmail();
        dto.password = e.getPassword();
        dto.position = e.getPosition();
        dto.salary = e.getSalary();
        dto.hireDate = e.getHireDate();
        dto.departmentId = e.getDepartment() != null ? e.getDepartment().getId() : null;

        return dto;
    }

    private Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.name);
        employee.setEmail(dto.email);
        employee.setPassword(dto.password);
        employee.setPosition(dto.position);
        employee.setSalary(dto.salary);
        employee.setHireDate(dto.hireDate);
        employee.setDepartment(departmentRepository.findById(dto.departmentId).orElse(null));
        return employee;
    }

    private Employee toEntity(RegisterEmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.name);
        employee.setEmail(dto.email);
        employee.setPosition(dto.position);
        employee.setSalary(dto.salary);
        employee.setHireDate(dto.hireDate);
        employee.setDepartment(departmentRepository.findById(dto.departmentId).orElse(null));
        return employee;
    }


}
