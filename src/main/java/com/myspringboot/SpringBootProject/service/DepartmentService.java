package com.myspringboot.SpringBootProject.service;

import com.myspringboot.SpringBootProject.entity.Department;
import com.myspringboot.SpringBootProject.entity.DepartmentRating;
import com.myspringboot.SpringBootProject.error.DepartmentNotFoundException;
import com.myspringboot.SpringBootProject.model.RatingCounts;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> getDepartments();

    public Department getDepartmentByID(Long departmentID) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartmentDetails(Long departmentId, Department department);

    public List<Department> getDepartmentByName(String departmentName);

    public List<String> getDepartmentNames();

    public List<Long> getDepartmentFromOneToFive();

    public List<String> getDepartmentNamesStartingWithE();

    public List<Long> getAllDepartmentIdsLessThanThatID(long deptId);

    public Long getSumOfAllDepartmentScores();

    public List<Long> getAllDepartmentScoresLessThan();

    public List<Long> getAllDepartmentScoresMoreThan();

    public RatingCounts getAllDepartmentRating();

   public int getAllDepartmentIds();


    public long getAllDepartmentIdseg(long deptId);

    public List<Long> getAllDepartmentScoresEqualTo();

    Department updateDepartmentRating(Long departmentId, Department department);

    List<Department> getDepartmentRating(DepartmentRating departmentRating);

    public void deleteDepartment(Department department);

    public Long getDepartmentCount(Department department);
}
