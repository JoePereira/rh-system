package com.joe.rh_system.Controller;

import com.joe.rh_system.DTO.DepartmentDTO;
import com.joe.rh_system.Service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service){
        this.service = service;
    }

    @GetMapping
    public List<DepartmentDTO> listAll() {
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> save(@RequestBody DepartmentDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }
}
