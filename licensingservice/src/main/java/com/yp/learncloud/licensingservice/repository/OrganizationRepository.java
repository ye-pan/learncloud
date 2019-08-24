package com.yp.learncloud.licensingservice.repository;

import com.yp.learncloud.licensingservice.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, String> {
}
