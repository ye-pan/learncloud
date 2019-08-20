package com.yp.learncloud.licensingservice.repository;

import com.yp.learncloud.licensingservice.config.ServiceConfig;
import com.yp.learncloud.licensingservice.model.License;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class LicenseRepositoryTest {

    @Autowired
    private LicenseRepository licenseRepository;

    @MockBean
    private ServiceConfig serviceConfig;

    @Test
    public void testSave() {
        License license = new License();
        license.setOrganizationId(UUID.randomUUID().toString());
        license.setProjectName("test");
        license.setLicenseType("G");
        license.setLicenseMax(0);
        license.setLicenseAllocated(0);
        license.setComment("test");
        licenseRepository.save(license);

        String id = license.getId();
        assertNotNull(id);
    }
}