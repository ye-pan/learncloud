package com.yp.learncloud.organizationservice.repository;

import com.yp.learncloud.organizationservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
