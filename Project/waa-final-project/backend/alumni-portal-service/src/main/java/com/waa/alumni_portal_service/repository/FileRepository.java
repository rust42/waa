package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Integer> {
}
