package com.myspringboot.SpringBootProject.controller;

import com.myspringboot.SpringBootProject.entity.Department;
import com.myspringboot.SpringBootProject.entity.DepartmentRating;
import com.myspringboot.SpringBootProject.error.DepartmentNotFoundException;
import com.myspringboot.SpringBootProject.model.RatingCounts;
import com.myspringboot.SpringBootProject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);


    @GetMapping("/departments")
    public List<Department> getDepartments(){
        LOGGER.info("Inside getDepartmentsList of DepartmentController");
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{Id}")
    public Department getDepartmentById(@PathVariable("Id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.getDepartmentByID(departmentId);
    }
    @DeleteMapping("/departments/{Id}")
    public String deleteDepartmentById(@PathVariable("Id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department Deleted Successfully";
    }
    @PutMapping("/departments/{Id}")
    public Department updateDepartmentDetails(@PathVariable("Id") Long departmentId, @RequestBody Department department ){
        Department d = departmentService.updateDepartmentDetails(departmentId, department);
        return  d;
    }
    @GetMapping("/departments/name/{name}")
    public List<Department> getDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.getDepartmentByName(departmentName);
    }
    @GetMapping("/departmentNames")
    public List<String> getDepartmentNames() {
        LOGGER.info("Inside getDepartmentnamessList of DepartmentController");
        return departmentService.getDepartmentNames();
    }
    @GetMapping("/departmentIds")
    public List<Long> getDepartmentFromOneToFive() {
        return departmentService.getDepartmentFromOneToFive();
    }
    @GetMapping("/departmentNamesWithE")
    public List<String> getDepartmentNamesStartingWithE() {
        return departmentService.getDepartmentNamesStartingWithE();
    }

    @GetMapping("/departmentIdsLessThan")
    public List<Long> getAllDepartmentIdsLessThanThatID(@RequestParam("Id") long deptId) {
        return departmentService.getAllDepartmentIdsLessThanThatID(deptId);
    }

    @GetMapping("/departmentSum")
    public Long getSumOfALLDepartmentScores(){
        return departmentService.getSumOfAllDepartmentScores();
    }
    @GetMapping("/departmentScores")
    public List<Long> getAllDepartmentScoresLessThan(){
        return departmentService.getAllDepartmentScoresLessThan();
    }
    @GetMapping("/departmentScoresMore")
    public List<Long> getAllDepartmentScoresMoreThan(){
        return departmentService.getAllDepartmentScoresMoreThan();
    }
    @GetMapping("/departmentsRating")
    public RatingCounts getAllDepartmentRating(){
        return departmentService.getAllDepartmentRating();
    }

    @GetMapping("/example")
    public  int getAllDepartmentIds(){
    return departmentService.getAllDepartmentIds();}

    @GetMapping("/departmenteg")
    public long getAllDepartmentIdseg(@RequestParam("Id") long deptId) {
        return departmentService.getAllDepartmentIdseg(deptId);
    }
    @GetMapping("/departmentScores1")
    public List<Long> getAllDepartmentScoresEqualTo(){
        return departmentService.getAllDepartmentScoresEqualTo();}


    @PutMapping("/departmentRating/{Id}")
    public Department updateDepartmentRating(@PathVariable("Id") Long departmentId, @RequestBody Department department ){
        return departmentService.updateDepartmentRating(departmentId, department);
   }

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("departments12/rating/{rating}")
    public List<Department> getDepartmentRating(@PathVariable("rating") DepartmentRating departmentRating){
        return departmentService.getDepartmentRating(departmentRating);
    }

    @DeleteMapping("/departmentsdel")
    public void deleteAllDepartment(Department department){
        departmentService.deleteDepartment(department); }

    @GetMapping("departmentcount")
    public  Long getDepartmentCount(Department department){
        return departmentService.getDepartmentCount(department);
    }

}
