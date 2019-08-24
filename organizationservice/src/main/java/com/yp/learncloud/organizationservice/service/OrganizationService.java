package com.yp.learncloud.organizationservice.service;

import com.yp.learncloud.organizationservice.event.source.SimpleSourceBean;
import com.yp.learncloud.organizationservice.model.Organization;
import com.yp.learncloud.organizationservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private OrganizationRepository organizationRepository;

    private SimpleSourceBean simpleSourceBean;

    public OrganizationService(OrganizationRepository organizationRepository, SimpleSourceBean simpleSourceBean) {
        this.organizationRepository = organizationRepository;
        this.simpleSourceBean = simpleSourceBean;
    }

    public Organization get(String id) {
        return organizationRepository.getOne(id);
    }

    public void save(Organization org) {
        organizationRepository.save(org);
        simpleSourceBean.publishOrgChange("SAVE", org.getId());
    }

    public void update(Organization org) {
        organizationRepository.save(org);
        simpleSourceBean.publishOrgChange("UPDATE", org.getId());
    }

    public void delete(String orgId) {
        organizationRepository.deleteById(orgId);
        simpleSourceBean.publishOrgChange("DELETE", orgId);
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }
}
