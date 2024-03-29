package com.yp.learncloud.licensingservice.controller;

import com.yp.learncloud.licensingservice.config.ServiceConfig;
import com.yp.learncloud.licensingservice.model.License;
import com.yp.learncloud.licensingservice.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    private LicenseService licenseService;

    private ServiceConfig serviceConfig;

    public LicenseServiceController(LicenseService licenseService, ServiceConfig serviceConfig) {
        this.licenseService = licenseService;
        this.serviceConfig = serviceConfig;
    }

    @GetMapping("/")
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicenseByOrg(organizationId);
    }

    @GetMapping("/{licenseId}")
    public License getLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(organizationId, licenseId);
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.PUT)
    public String updateLicenses( @PathVariable("licenseId") String licenseId) {
        return "This is the put";
    }

    @PostMapping("/")
    public void saveLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicenses( @PathVariable("licenseId") String licenseId) {
        return "This is the Delete";
    }

    @GetMapping("/list")
    public List<License> list() {
        return licenseService.findAll();
    }
}
