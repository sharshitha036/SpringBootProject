/*
package com.myspringboot.SpringBootProject.service;

import com.myspringboot.SpringBootProject.entity.Department;
import com.myspringboot.SpringBootProject.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    DepartmentService departmentService;

    @MockBean
    DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .departmentName("CSE")
                        .departmentAddress("Hyderabad")
                        .departmentCode("CSE-04")
                        .departmentId(1L)
                        .build();
        Mockito.when(departmentRepository.findAllByDepartmentNameIgnoreCase("CSE"))
                .thenReturn(department);
    }
    @Test
    @DisplayName("Get Data based on Valida Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
        String departmentName = "CSE";
        Department found =
                departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }
}*/
