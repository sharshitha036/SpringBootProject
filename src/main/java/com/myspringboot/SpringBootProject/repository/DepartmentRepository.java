package com.myspringboot.SpringBootProject.repository;

import com.myspringboot.SpringBootProject.entity.Department;
import com.myspringboot.SpringBootProject.entity.DepartmentRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    public Department findByDepartmentName(String departmentName);
    public List<Department> findAllByDepartmentNameIgnoreCase(String departmentName);
    public List<Department> findByDepartmentRating(DepartmentRating departmentRating);
    @Query("select d from Department d where d.departmentScore = ?1 ")
    Department getDepartmentByDepartmentScore(Long departmentScore);


}
