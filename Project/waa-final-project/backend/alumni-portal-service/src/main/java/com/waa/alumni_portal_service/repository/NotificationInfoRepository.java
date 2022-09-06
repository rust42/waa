package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.NotificationInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NotificationInfoRepository extends CrudRepository<NotificationInfo, Integer> {
    Optional<NotificationInfo> findByUserId(Integer userId);
}