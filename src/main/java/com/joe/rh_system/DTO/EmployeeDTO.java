package com.joe.rh_system.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDTO {

    public Long id;
    public String name;
    public String email;
    public String position;
    public BigDecimal salary;
    public LocalDate hireDate;
    public Long departmentId;
}
