package com.myspringboot.SpringBootProject.service;


import com.myspringboot.SpringBootProject.entity.Department;
import com.myspringboot.SpringBootProject.entity.DepartmentRating;
import com.myspringboot.SpringBootProject.error.DepartmentNotFoundException;
import com.myspringboot.SpringBootProject.model.RatingCounts;
import com.myspringboot.SpringBootProject.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    DepartmentRepository departmentRepository;


    @Override
    public List<Department> getDepartments()
    {

        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentByID(Long departmentID) throws DepartmentNotFoundException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentID);
       /* if (departmentOptional.isPresent()) {
            return departmentOptional.get();
        } else {
            return new Department();
        }*/
        if (!departmentOptional.isPresent())
        {
            throw new DepartmentNotFoundException("Department Not Available");
        }

        return departmentOptional.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentDetails(Long departmentId, Department department) {

        Department dept = departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName()))
        {
            dept.setDepartmentName(department.getDepartmentName());
        }

        if (Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode()))
        {
            dept.setDepartmentCode(department.getDepartmentCode());
        }

        if (Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress()))
        {
            dept.setDepartmentAddress(department.getDepartmentAddress());

        }
        dept.setDepartmentScore(department.getDepartmentScore());
        return departmentRepository.save(dept);
    }

    @Override
    public List<Department> getDepartmentByName(String departmentName)
    {
        return departmentRepository.findAllByDepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public List<String> getDepartmentNames() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<String> departmentNames = new ArrayList<>();
        /*for(int i=0; i<allDepartments.size();i++) {
            departmentNames.add(allDepartments.get(i).getDepartmentName());
        }*/
        for (Department i : allDepartments)
        {
            departmentNames.add(i.getDepartmentName());
        }
        return departmentNames;
    }

    @Override
    public List<Long> getDepartmentFromOneToFive() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Long> departmentsFromOneToFive = new ArrayList<>();
        for (int i = 0; i < allDepartments.size(); i++)
            if (allDepartments.get(i).getDepartmentId() <= 5)
            {
                departmentsFromOneToFive.add(allDepartments.get(i).getDepartmentId());
            }
        return departmentsFromOneToFive;
    }

    @Override
    public List<String> getDepartmentNamesStartingWithE() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<String> departmentNamesWithE = new ArrayList<>();

        for (int i = 0; i < allDepartments.size(); i++)
        {
            String dname = allDepartments.get(i).getDepartmentName();
            LOGGER.info("dname is :" + dname + "and Index is: "+ i );
            if (dname != null && dname.startsWith("E"))
            {
                departmentNamesWithE.add(dname);
            }
        }
        return departmentNamesWithE;
    }

    @Override
    public List<Long> getAllDepartmentIdsLessThanThatID(long deptId) {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Long> departmentIDs = new ArrayList<>();
        for (int i = 0; i < allDepartments.size(); i++)
        {
            long dId = allDepartments.get(i).getDepartmentId();
            LOGGER.info("dId is ===============:" + dId + "and Index is: " + i);

            if (dId < deptId)
            {
                departmentIDs.add(dId);
            }
        }
        return departmentIDs;
    }

    @Override
    public Long getSumOfAllDepartmentScores() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Long> departmentScore2 = new ArrayList<>();
        Long departmentScore1 = 0l;
       /* for (int i = 0; i < allDepartments.size(); i++) {
            departmentScore1 += allDepartments.get(i).getDepartmentScore();
        }*/
        for (int i = 0; i < allDepartments.size(); i++)
        {
            departmentScore1 = allDepartments.get(i).getDepartmentScore();
            departmentScore2.add(departmentScore1);
        }
        Long[] inputArray = departmentScore2.toArray(new Long[0]);
        System.out.println(inputArray);
        Long sum = 0l;
        for(int i=0; i< inputArray.length;i++)
        {
            sum += inputArray[i];
            LOGGER.info(String.valueOf(sum));
        }
        return sum;
    }

    @Override
    public List<Long> getAllDepartmentScoresLessThan() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Long> departmentScore3 = new ArrayList<>();
        Long departmentScore4 = 0l;
        for (int i = 0; i < allDepartments.size(); i++)
        {
            departmentScore4 = allDepartments.get(i).getDepartmentScore();
            if(departmentScore4 < 3)
            {
                departmentScore3.add(departmentScore4);
            }
        }
        return departmentScore3;

    }

    @Override
    public List<Long> getAllDepartmentScoresMoreThan() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Long> departmentScore5 = new ArrayList<>();
        Long departmentScore6 = 0l;
        for (int i = 0; i < allDepartments.size(); i++)
        {
            departmentScore6 = allDepartments.get(i).getDepartmentScore();
            if(departmentScore6 > 3)
            {
                departmentScore5.add(departmentScore6);
            }
        }
        return departmentScore5;
    }

    @Override
    public RatingCounts getAllDepartmentRating() {
        List<Department> allDepartments = departmentRepository.findAll();
        int high =0;
        int medium = 0;
        int low = 0;

        for (int i =0; i< allDepartments.size();i++)
        {
            DepartmentRating dr = allDepartments.get(i).getDepartmentRating();
            if(dr==null)
            {
                System.out.println("Do Nothing");
            }
            if(dr==DepartmentRating.HIGH)
            {
                  high++;
             }
            else if (dr==DepartmentRating.MEDIUM)
            {
                  medium++;
            }
            else if (dr==DepartmentRating.LOW)
            {
                 low++;
            }
        }
        RatingCounts ratingCounts = new RatingCounts(high,medium,low);
        return ratingCounts;
        }

    @Override
    public int getAllDepartmentIds() {
        System.out.println("Exampleee");
        return 29;
    }

    @Override
    public long getAllDepartmentIdseg(long deptId) {
        System.out.println("hellooo");
        return 1;
    }

    @Override
    public List<Long> getAllDepartmentScoresEqualTo() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Long> departmentScore8 = new ArrayList<>();
        Long departmentScore7 = 0l;
        for (int i = 0; i < allDepartments.size(); i++)
        {
            departmentScore7 = allDepartments.get(i).getDepartmentScore();
            if(departmentScore7 == 2)
            {
                departmentScore8.add(departmentScore7);
            }
        }
        return departmentScore8;

    }

    /*@Override
    public List<Long> saveDepartmentRating(Department department) {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Long> departmentRating = new ArrayList<>();
        Long departmentRatings = 2l;
        for(int i=0;i<allDepartments.size();i++){
        if(allDepartments.get(i).getDepartmentName() == "CSE"){
            departmentRating.add(departmentRatings);
        }
        }
        return departmentRating;
    }*/

    @Override
    public Department updateDepartmentRating(Long departmentId, Department department) {
        Department dept = departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getDepartmentRating()))
        {
            dept.setDepartmentRating(department.getDepartmentRating());
        }

        return departmentRepository.save(dept);
    }
    @Override
    public Department saveDepartment(Department department)
    {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartmentRating(DepartmentRating departmentRating){
            return departmentRepository.findByDepartmentRating(departmentRating);
    }

    @Override
    public void deleteDepartment(Department department) {
         departmentRepository.deleteAll();
        System.out.println("Deleted Successfully");
    }

    @Override
    public Long getDepartmentCount(Department department) {
        return departmentRepository.count();
    }

}