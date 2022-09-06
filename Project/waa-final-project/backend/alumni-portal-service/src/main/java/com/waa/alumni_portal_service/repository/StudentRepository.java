package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.dto.chart.JobByState;
import com.waa.alumni_portal_service.dto.chart.StudentPerState;
import com.waa.alumni_portal_service.entity.Department;
import com.waa.alumni_portal_service.entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    List<Student> findAllByCityAndMajorName(String city, String department);

    List<Student> findAllByMajorName(String city);

    List<Student> findAllByCity(String city);

    @Query(value = "SELECT DISTINCT s.city from Student s", nativeQuery = true)
    List<String> findAllCities();
    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.StudentPerState( s.state, COUNT(s.state) ) " +
            "from Student AS s GROUP BY s.state")
    List<StudentPerState>getStudentsPerState();

    @Query(value = "SELECT DISTINCT s.state from Student s", nativeQuery = true)
    List<String> findAllState();

    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.StudentPerState( s.city, COUNT(s.city) ) " +
            "from Student AS s WHERE s.state =:state GROUP BY s.city")
    List<StudentPerState>findStudentByCity(String state);

    @Query("SELECT DISTINCT s FROM Student AS s JOIN s.interestedTags t WHERE t.id IN (:tagIds)")
    List<Student> getByInterestedTag(List<Integer> tagIds);
}
