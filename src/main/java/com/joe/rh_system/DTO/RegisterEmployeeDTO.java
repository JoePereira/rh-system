package com.joe.rh_system.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegisterEmployeeDTO {
    public Long id;
    public String name;
    public String email;
    public String password;
    public String confirmPassword;
    public String position;
    public BigDecimal salary;
    public LocalDate hireDate;
    public Long departmentId;
}
