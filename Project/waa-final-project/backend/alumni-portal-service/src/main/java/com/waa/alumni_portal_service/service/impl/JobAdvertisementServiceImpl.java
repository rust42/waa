package com.waa.alumni_portal_service.service.impl;

import com.google.firebase.messaging.Notification;
import com.waa.alumni_portal_service.dto.JobAdvertisementDTO;
import com.waa.alumni_portal_service.dto.PagingDTO;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;
import com.waa.alumni_portal_service.dto.chart.JobByState;
import com.waa.alumni_portal_service.entity.JobAdvertisement;
import com.waa.alumni_portal_service.entity.Student;
import com.waa.alumni_portal_service.entity.Tag;
import com.waa.alumni_portal_service.repository.JobAdvertisementRepository;
import com.waa.alumni_portal_service.repository.StudentRepository;
import com.waa.alumni_portal_service.service.JobAdvertisementService;
import com.waa.alumni_portal_service.service.NotificationService;
import com.waa.alumni_portal_service.service.StudentService;
import com.waa.alumni_portal_service.util.UserDetailUtil;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final ModelMapper modelMapper;

    private final UserDetailUtil userDetailUtil;

    private final JobAdvertisementRepository jobAdvertisementRepository;

    private final StudentRepository studentRepository;

    private final NotificationService notificationService;

    static Specification<JobAdvertisement> hasCity(String city) {
        return (jobAdvertisementRoot, cq, cb) -> cb.like(jobAdvertisementRoot.get("city"), "%" + city + "%");
    }

    static Specification<JobAdvertisement> hasState(String state) {
        return (jobAdvertisementRoot, cq, cb) -> cb.like(jobAdvertisementRoot.get("state"), "%" + state + "%");
    }

    static Specification<JobAdvertisement> hasCompanyName(String companyName) {
        return (jobAdvertisementRoot, cq, cb) -> cb.like(jobAdvertisementRoot.get("companyName"), "%" + companyName + "%");
    }

    static Specification<JobAdvertisement> hasTags(String tags) {
        return (student, cq, cb) -> {
            Join<JobAdvertisement, Tag> tagsJoin = student.join("tags");
            Predicate equal = cb.like(tagsJoin.get("tagName"), "%" + tags + "%");
            cq.distinct(true);
            return equal;
        };
    }

    @Override
    public List<JobAdvertisementDTO> findAll() {
        var result = new ArrayList<JobAdvertisementDTO>();
        jobAdvertisementRepository.findAll().forEach(job -> result.add(modelMapper.map(job, JobAdvertisementDTO.class)));
        return result;
    }

    @Override
    public List<JobAdvertisementDTO> findFirst10OrderByCreationTimeDesc() {
        var data = jobAdvertisementRepository.findFirst10ByOrderByCreationTimeDesc();
        var result = new ArrayList<JobAdvertisementDTO>();
        data.forEach(item -> result.add(modelMapper.map(item, JobAdvertisementDTO.class)));
        return result;
    }

    @Override
    public void createNewJobAdvertisement(JobAdvertisementDTO jobAdvertisementDTO) {
        jobAdvertisementDTO.setStudent(userDetailUtil.getCurrentStudent());
        JobAdvertisement jobAdvertisement = modelMapper.map(jobAdvertisementDTO, JobAdvertisement.class);
        JobAdvertisement newJob = jobAdvertisementRepository.save(jobAdvertisement);
        sendNotificationToWhoInterestedTags(newJob);
    }

    @Override
    public boolean updateJobAdvertisement(JobAdvertisementDTO jobAdvertisementDTO) {
        JobAdvertisement jobAdvertisementCheck = jobAdvertisementRepository.findById(jobAdvertisementDTO.getId()).orElseGet(null);
        if (jobAdvertisementCheck == null) {
            return false;
        }
        jobAdvertisementDTO.setStudent(userDetailUtil.getCurrentStudent());
        JobAdvertisement jobAdvertisement = modelMapper.map(jobAdvertisementDTO, JobAdvertisement.class);
        jobAdvertisementRepository.save(jobAdvertisement);
        return true;
    }

    @Override
    public JobAdvertisementDTO findById(int id) {
        return modelMapper.map(jobAdvertisementRepository.findById(id).orElse(new JobAdvertisement()), JobAdvertisementDTO.class);
    }

    @Override
    public List<JobAdvertisementDTO> findByPosterId(Integer id) {
        return jobAdvertisementRepository.findByStudentId(id)
                .stream()
                .map(job -> modelMapper.map(job, JobAdvertisementDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PagingDTO<JobAdvertisementDTO> findAllBySearchParams(HashMap<String, String> searchParams) {
        String tags = searchParams.getOrDefault("tags", "");
        String state = searchParams.getOrDefault("state", "");
        String city = searchParams.getOrDefault("city", "");
        String companyName = searchParams.getOrDefault("companyName", "");
        String pageNumberString = searchParams.getOrDefault("page", "1");
        int pageNumber = Integer.parseInt(pageNumberString);
        String pageSizeString = searchParams.getOrDefault("pageSize", "10");
        int pageSize = Integer.parseInt(pageSizeString);

        Pageable paging = PageRequest.of(pageNumber, pageSize);

        Page<JobAdvertisement> advertisements = jobAdvertisementRepository
                .findAll(
                        hasCompanyName(companyName)
                                .and(hasCity(city))
                                .and(hasState(state)
                                        .and(hasTags(tags))),
                        paging);


        List<JobAdvertisementDTO> advertisementDTOS = advertisements.getContent()
                .stream().map(job ->
                        modelMapper.map(job, JobAdvertisementDTO.class)
                ).toList();
        return new PagingDTO<>(
            advertisements.getTotalPages(),
                advertisements.getTotalElements(),
                pageNumber,
                advertisementDTOS

        );
    }

    @Override
    public List<String> findAllStates() {
        return jobAdvertisementRepository.findAllStates();
    }

    @Override
    public EChartDTO getAllJobsByLocation() {
        var data = jobAdvertisementRepository.getAllJobsByState();
        return parse(data);
    }

    public EChartDTO parse(List<JobByState> data) {
        var names = new ArrayList<String>();
        var values = new ArrayList<Long>();
        for (JobByState item : data) {
            names.add(item.getName());
            values.add(item.getValue());
        }
        return new EChartDTO(names, values);
    }

    @Override
    public List<JobAdvertisementDTO> findRecentlyAppliedJob() {
        final Integer top10 = 10;
        return jobAdvertisementRepository.findRecentlyAppliedJob(top10)
                .stream().map(job -> modelMapper.map(job, JobAdvertisementDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllCities() {
        return jobAdvertisementRepository.findAllCities();
    }

    @Override
    public EChartDTO getTagsByJobs() {
        var data = jobAdvertisementRepository.getTagsByJobs();
        return parse(data);
    }

    @Override
    public EChartDTO getTagsByLocation(HashMap<String, String> params) {
        String state = params.getOrDefault("state", "");
        var data = jobAdvertisementRepository.getTagsByLocation(state);
        return parse(data);
    }

    @Override
    public EChartDTO getAllJobsByCity() {
        var data = jobAdvertisementRepository.getAllJobsByCity();
        return parse(data);
    }

    private void sendNotificationToWhoInterestedTags(JobAdvertisement jobAdvertisement){
        List<Integer> tagIds = jobAdvertisement.getTags()
                .stream().map( tag -> tag.getId()).collect(Collectors.toList());
        if(!tagIds.isEmpty()){
            List<Student> interestedTagStudents = studentRepository.getByInterestedTag(tagIds);
            String title = "New job posted";
            String body = String.format("New job with tag you interested just posted! %s - id: %s",
                    jobAdvertisement.getTitle(), jobAdvertisement.getId());
            Notification notification = new Notification(title, body);
            interestedTagStudents.forEach(student -> notificationService.sendNotificationToUser(notification, student.getId()));
            System.out.println(String.format("Send new job notification to %s students", interestedTagStudents.size()));
        }
    }
}
