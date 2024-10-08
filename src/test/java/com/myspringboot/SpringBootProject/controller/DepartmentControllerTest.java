package com.myspringboot.SpringBootProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myspringboot.SpringBootProject.entity.Department;
import com.myspringboot.SpringBootProject.entity.DepartmentRating;
import com.myspringboot.SpringBootProject.error.DepartmentNotFoundException;
import com.myspringboot.SpringBootProject.service.DepartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;
    private Department department;
    Department departmentOne;
    Department departmentTwo;
    List<Department> departmentList= new ArrayList<>();

    @BeforeEach
    void setUp() {
        departmentOne = new Department(1l,"IT","Mumbai","IT-06",2l, DepartmentRating.HIGH);
        departmentTwo = new Department(2l,"ECE","Agra","ECE-04",5l, DepartmentRating.LOW);
        departmentList.add(departmentOne);
        departmentList.add(departmentTwo);

    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void getDepartments() throws Exception {

        when(departmentService.getDepartments()).thenReturn(departmentList);
        this.mockMvc.perform(get("/departments")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getDepartmentById() throws Exception {
        when(departmentService.getDepartmentByID(1l)).thenReturn(departmentOne);
        this.mockMvc.perform(get("/departments/"+ "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteDepartmentById() throws Exception {
       doNothing().when(departmentService).deleteDepartmentById(1l);

       this.mockMvc.perform(delete("/departments/"+1l)).andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string("Department Deleted Successfully"));
    }

    @Test
    void updateDepartmentDetails() throws Exception {

        when(departmentService.updateDepartmentDetails(1l,departmentOne)).thenReturn(departmentOne);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson=ow.writeValueAsString(departmentOne);


        this.mockMvc.perform(put("/departments/"+ 1l).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getDepartmentByName() throws Exception {
        when(departmentService.getDepartmentByName("IT")).thenReturn(Collections.singletonList(departmentOne));

        this.mockMvc.perform(get("/departments/"+ "name/" + "IT"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getDepartmentNames() {
    }

    @Test
    void getDepartmentFromOneToFive() {
    }

    @Test
    void getDepartmentNamesStartingWithE() {
    }

    @Test
    void getAllDepartmentIdsLessThanThatID() {
    }

    @Test
    void getSumOfALLDepartmentScores() {
    }

    @Test
    void getAllDepartmentScoresLessThan() {
    }

    @Test
    void getAllDepartmentScoresMoreThan() {
    }

    @Test
    void getAllDepartmentRating() {
    }

    @Test
    void getAllDepartmentIds() {
    }

    @Test
    void getAllDepartmentIdseg() {
    }

    @Test
    void getAllDepartmentScoresEqualTo() {
    }

    @Test
    void updateDepartmentRating() {
    }

    @Test
    void saveDepartment() {
    }

    @Test
    void getDepartmentRating() {
    }

    @Test
    void deleteAllDepartment() {
    }

    @Test
    void getDepartmentCount() {
    }

    /*@BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
         Department inputDepartment = Department.builder()
                .departmentId(1l)
                .departmentAddress("Agra")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentScore(2L)
                .departmentRating(DepartmentRating.HIGH)
                .build();

        when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"IT\",\n" +
                        "\t\"departmentAddress\":\"Ahmedabad\",\n" +
                        "\t\"departmentCode\":\"IT-06\"\n" +
                        "}"))
                .andExpect(status().isOk());

    }

    @Test
    void getDepartmentByID() throws Exception {
        when(departmentService.getDepartmentByID(2l))
                .thenReturn(department);

        mockMvc.perform(get("/departments/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").
                        value("IT"));
    }


 @Test
    void deleteDepartmentByID() throws Exception{
        Long departmentId = 1l;

        when(departmentService.deleteDepartmentById(1l))
                .thenReturn(true);


        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .andExpect(status().isOk())
                .andExpect(jsonPath("Department Deleted Successfully")));

    }

    void deleteDepartmentById() throws Exception {
        Long departmentId = 1L;

        // Mocking the service method call
        when(departmentService.deleteDepartmentById(departmentId)).thenReturn(true);

        // Performing the DELETE request
        mockMvc.perform(delete("/departments/{Id}", departmentId))
                .andExpect(status().isOk())
                .andExpect(content().string("Department Deleted Successfully"));

        // Verifying that the service method was called with the correct departmentId
        verify(departmentService, times(1)).deleteDepartmentById(departmentId);
    }

*/

}
