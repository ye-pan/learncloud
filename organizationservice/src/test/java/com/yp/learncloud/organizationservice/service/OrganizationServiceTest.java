package com.yp.learncloud.organizationservice.service;

import com.yp.learncloud.organizationservice.model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("test")
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void test() {
        Organization org = new Organization();
        org.setName("test");
        org.setContactName("test");
        org.setContactEmail("test@qq.com");
        org.setContactPhone("test");
        organizationService.save(org);
        assertNotNull(org.getId());

        org = organizationService.get(org.getId());
        assertNotNull(org);
    }
}