package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.JobAdvertisementDTO;
import com.waa.alumni_portal_service.dto.PagingDTO;
import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;
import com.waa.alumni_portal_service.dto.chart.StudentPerState;
import com.waa.alumni_portal_service.entity.Department;
import com.waa.alumni_portal_service.entity.Student;
import com.waa.alumni_portal_service.repository.StudentRepository;
import com.waa.alumni_portal_service.security.SecurityUtil;
import com.waa.alumni_portal_service.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    private final SecurityUtil securityUtil;

    static Specification<Student> hasFirstName(String name) {
        return (student, cq, cb) -> cb.like(student.get("firstName"), "%" + name + "%");
    }

    static Specification<Student> hasLastName(String name) {
        return (student, cq, cb) -> cb.like(student.get("lastName"), "%" + name + "%");
    }

    static Specification<Student> hasCity(String city) {
        return (student, cq, cb) -> cb.like(student.get("city"), "%" + city + "%");
    }

    static Specification<Student> hasState(String state) {
        return (student, cq, cb) -> cb.like(student.get("state"), "%" + state + "%");
    }

    static Specification<Student> hasStudentId(String studentId) {
        int search = 0;
        if (studentId.length() != 0) {
            try {
                search = Integer.parseInt(studentId);
            } catch (Exception e) {
                throw new IllegalArgumentException("Not valid student ID");
            }

        }
        int finalSearch = search;
        return (student, cq, cb) -> cb.equal(student.get("id"), finalSearch);
    }

    static Specification<Student> hasDepartment(String department) {
        return (student, cq, cb) -> {
            Join<Student, Department> departmentJoin = student.join("major");
            Predicate equal = cb.like(departmentJoin.get("name"), "%" + department + "%");

            cq.distinct(true);
            return equal;
        };
    }

    @Override
    public Optional<StudentDTO> findById(Integer id) {
        return studentRepository.findById(id).map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public PagingDTO<StudentDTO> findAll(HashMap<String, String> searchParams) {
        String city = searchParams.getOrDefault("city", "");
        String department = searchParams.getOrDefault("department", "");
        String name = searchParams.getOrDefault("searchText", "");
        String state = searchParams.getOrDefault("state", "");
        String studentId = searchParams.getOrDefault("studentId", "");

        String pageNumberString = searchParams.getOrDefault("page", "0");
        int pageNumber = Integer.parseInt(pageNumberString);
        String pageSizeString = searchParams.getOrDefault("pageSize", "10");
        int pageSize = Integer.parseInt(pageSizeString);
        Pageable paging = PageRequest.of(pageNumber, pageSize);

        Page<Student> students;

        if (studentId.isBlank()) {
            students = studentRepository.findAll(
                    hasFirstName(name).or(hasLastName(name))
                            .and(hasCity(city))
                            .and(hasDepartment(department))
                            .and(hasState(state)), paging);
        } else {
            students = studentRepository
                    .findAll(
                            hasFirstName(name).or(hasLastName(name))
                                    .and(hasCity(city))
                                    .and(hasDepartment(department))
                                    .and(hasState(state))
                                    .and(hasStudentId(studentId)), paging);
        }

        List<StudentDTO> studentsDTOs = students.getContent()
                .stream().map(student ->
                        modelMapper.map(student, StudentDTO.class)
                ).toList();
        return new PagingDTO<>(
                students.getTotalPages(),
                students.getTotalElements(),
                pageNumber,
                studentsDTOs);
    }

    @Override
    public void save(StudentDTO studentDTO) {
        studentRepository.save(modelMapper.map(studentDTO, Student.class));
    }

    @Override
    public void update(StudentDTO studentDTO) {
        if (studentDTO.getId() != securityUtil.getCurrentUser().getId())
            throw new IllegalArgumentException("Not valid user");
        findById(studentDTO.getId()).map(existedStudent -> {
            studentDTO.setEmail(existedStudent.getEmail());
            save(studentDTO);
            return existedStudent;
        });
    }

    @Override
    public EChartDTO getStudentsPerState() {
        var data = studentRepository.getStudentsPerState();
        return parse(data);
    }

    @Override
    public List<String> getState() {
        return studentRepository.findAllState();
    }

    @Override
    public EChartDTO getStudentByCity(HashMap<String, String> params) {
        String state = params.getOrDefault("state", "");
        var data = studentRepository.findStudentByCity(state);
        return parse(data);
    }

    public EChartDTO parse(List<StudentPerState> data) {
        var names = new ArrayList<String>();
        var values = new ArrayList<Long>();
        for (StudentPerState item : data) {
            names.add(item.getName());
            values.add(item.getValue());
        }
        return new EChartDTO(names, values);
    }

    @Override
    public List<String> findAllCities() {
        return studentRepository.findAllCities();
    }
}
