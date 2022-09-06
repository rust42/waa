package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> findAllBySearchTextAndCityAndMajorName(String searchText, String city, String major) ;
}
