package org.arcsoft;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arcsoft.domain.Department;
import org.arcsoft.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repository;

    @Transactional
    public void listAllDepartments() {
        List<Department> departments = repository.findAll();
        for (Department department : departments) {
            log.info("Department: {}", department);
            log.info("Department users: {}", department.getUsers());
        }
    }
}
