package com.yp.learncloud.licensingservice.controller;

import com.yp.learncloud.licensingservice.model.License;
import com.yp.learncloud.licensingservice.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    private LicenseService licenseService;

    public LicenseServiceController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        //return licenseService.getLicense(licenseId);
        return new License(licenseId, organizationId, "Teleco", "Seat");
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.PUT)
    public String updateLicenses( @PathVariable("licenseId") String licenseId) {
        return "This is the put";
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.POST)
    public String saveLicenses( @PathVariable("licenseId") String licenseId) {
        return "This is the post";
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicenses( @PathVariable("licenseId") String licenseId) {
        return "This is the Delete";
    }
}
