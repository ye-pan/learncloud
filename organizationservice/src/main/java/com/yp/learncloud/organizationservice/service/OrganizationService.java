package com.yp.learncloud.organizationservice.service;

import com.yp.learncloud.organizationservice.model.Organization;
import com.yp.learncloud.organizationservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization get(String id) {
        return organizationRepository.getOne(id);
    }

    public void save(Organization org) {
        organizationRepository.save(org);
    }

    public void update(Organization org) {
        organizationRepository.save(org);
    }

    public void delete(String orgId) {
        organizationRepository.deleteById(orgId);
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }
}
