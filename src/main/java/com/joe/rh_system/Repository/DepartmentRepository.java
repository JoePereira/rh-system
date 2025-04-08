package com.joe.rh_system.Repository;

import com.joe.rh_system.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
