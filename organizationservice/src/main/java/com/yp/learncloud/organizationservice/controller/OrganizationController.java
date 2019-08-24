package com.yp.learncloud.organizationservice.controller;

import com.yp.learncloud.organizationservice.model.Organization;
import com.yp.learncloud.organizationservice.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/organizations")
public class OrganizationController {
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{organizationId}")
    public Organization get(@PathVariable("organizationId") String id) {
        return organizationService.get(id);
    }

    @PutMapping("/")
    public void update(@RequestBody  Organization org) {
        organizationService.update(org);
    }

    @PostMapping("/")
    public void save(Organization org) {
        organizationService.save(org);
    }

    @DeleteMapping("/{organizationId}")
    public void delete(@PathVariable("organizationId") String id) {
        organizationService.delete(id);
    }

    @GetMapping("/list")
    public List<Organization> getAll() {
        return organizationService.findAll();
    }
}
