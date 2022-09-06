package com.waa.alumni_portal_service.service.impl;

import com.google.firebase.messaging.Notification;
import com.waa.alumni_portal_service.dto.JobApplicationDTO;

import com.waa.alumni_portal_service.dto.JobApplicationRequest;
import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.entity.File;
import com.waa.alumni_portal_service.entity.JobApplication;
import com.waa.alumni_portal_service.repository.FileRepository;
import com.waa.alumni_portal_service.repository.JobAdvertisementRepository;
import com.waa.alumni_portal_service.repository.JobApplicationRepository;
import com.waa.alumni_portal_service.repository.StudentRepository;
import com.waa.alumni_portal_service.service.JobApplicationService;
import com.waa.alumni_portal_service.service.NotificationService;
import com.waa.alumni_portal_service.service.UploadStorageService;
import com.waa.alumni_portal_service.util.UserDetailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService  {

    private final UploadStorageService uploadStorageService;
    private final JobApplicationRepository applicationRepository;

    private final StudentRepository studentRepository;
    private final JobAdvertisementRepository advertisementRepository;

    private final FileRepository fileRepository;

    private final UserDetailUtil userDetailUtil;

    private final ModelMapper modelMapper;

    private final NotificationService notificationService;

    @Override
    public List<JobApplicationDTO> findByJobAdvId(Integer id) {
        return applicationRepository.findByJobAdvertisementIdOrderByCreationTimeAsc(id)
                .stream()
                .map(jobApplication -> modelMapper.map(jobApplication, JobApplicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<JobApplicationDTO> findById(Integer id) {
         return applicationRepository.findById(id)
                .map(jobApplication -> modelMapper.map(jobApplication, JobApplicationDTO.class));

    }

    @Override
    public void storeApplication(int advertisementId, JobApplicationRequest application) throws Exception {
        var optionalJobAdvertisement = advertisementRepository.findById(advertisementId);

        if (optionalJobAdvertisement.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid application id");
        }

        var advertisement = optionalJobAdvertisement.get();

        StudentDTO details = userDetailUtil.getCurrentStudent();

        var optionalStudent = studentRepository.findById(details.getId());

        if (optionalStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid applicant");
        }

        var student = optionalStudent.get();


        JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicant(student);
        jobApplication.setJobAdvertisement(advertisement);
        jobApplication.setCoverLetter(application.getCoverLetter());
        jobApplication.setTitle(application.getTitle());
        jobApplication = applicationRepository.save(jobApplication);


        var filePath = uploadStorageService.store(application.getFile());

        File upload = new File();
        upload.setPath(filePath);
        upload.setJobApplication(jobApplication);
        upload = fileRepository.save(upload);

        jobApplication.addAttachment(upload);
        student.addApplication(jobApplication);
        sendNotificationToOwner(jobApplication);
    }

    private void sendNotificationToOwner(JobApplication jobApplication){
        Integer jobOwnerId = jobApplication.getJobAdvertisement().getStudent().getId();
        String title = "New Application";
        String body = String.format("Your job %s, just have new application from %s",
                jobApplication.getJobAdvertisement().getTitle(),
                jobApplication.getApplicant().getFirstName()
                + " " +jobApplication.getApplicant().getLastName());
        Notification notification = new Notification(title, body);
        notificationService.sendNotificationToUser(notification, jobOwnerId);
    }
}
