package com.yp.learncloud.licensingservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yp.learncloud.commons.utils.UserContextHolder;
import com.yp.learncloud.licensingservice.clients.*;
import com.yp.learncloud.licensingservice.config.ServiceConfig;
import com.yp.learncloud.licensingservice.model.License;
import com.yp.learncloud.licensingservice.model.Organization;
import com.yp.learncloud.licensingservice.repository.LicenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class LicenseService {

    private LicenseRepository licenseRepository;

    private ServiceConfig serviceConfig;

    private OrganizationDisconveryClient organizationDisconveryClient;

    private OrganizationRibbonClient organizationRibbonClient;

    private OrganizationFeignClient organizationFeignClient;

    private OrganizationOauth2Client organizationOauth2Client;

    public LicenseService(LicenseRepository licenseRepository, ServiceConfig serviceConfig,
                          OrganizationDisconveryClient organizationDisconveryClient,
                          OrganizationRibbonClient organizationRibbonClient,
                          OrganizationFeignClient organizationFeignClient,
                          OrganizationOauth2Client organizationOauth2Client) {
        this.licenseRepository = licenseRepository;
        this.serviceConfig = serviceConfig;
        this.organizationDisconveryClient = organizationDisconveryClient;
        this.organizationRibbonClient = organizationRibbonClient;
        this.organizationFeignClient = organizationFeignClient;
        this.organizationOauth2Client = organizationOauth2Client;
    }

    private Organization retrieveOrgInfo(String organizationId, ClientType clientType) {
        switch(clientType) {
            case DISCONVERY_CLIENT:
                return organizationDisconveryClient.getOrganization(organizationId);
            case RIBBON:
                return organizationRibbonClient.getOrganization(organizationId);
            case FEIGN:
                return organizationFeignClient.getOrganization(organizationId);
            case OAUTH2:
                return organizationOauth2Client.getOrganization(organizationId);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public License getLicense(String orgainzationId, String id) {
        License license = licenseRepository.getOne(id);
        license.setComment(serviceConfig.getExampleProperty());
        Organization organization = retrieveOrgInfo(orgainzationId, ClientType.OAUTH2);
        license.setOrganization(organization);
        return license;
    }



    public void saveLicense(License license) {
        license.setId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }

    @HystrixCommand(
            commandProperties = {
                    //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")//定制断路器超时时间
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//开始检查断路器是否跳闸之前滚动窗口必须处理的最小请求数
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),//跳闸之前，滚动窗口内必须达到的故障百分比
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),//断路器跳闸之后，hystrix尝试进行服务调用之前将要等待的时间
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),//Hystrix收集和监控服务调用的统计信息的滚动窗口
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5"),//Hystrix在一个监控窗口中维护的度量桶的数量
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")//隔离策略，THREAD：每个Hystrix命令都在一个单独的线程池中运行，该线程池不与父线程共享它的上下文，可以通过HystrixConcurrencyStrategy自定义并发策略来将父线程的上下文注入由Hystrix命令管理的线程中
                                                                                            // SEMAPHORE：Hystrix管理由@HystrixCommand注解保护的分布式调用，而不需要启动一个新线程，并且如果调用超时，就会中断父线程
            },
            fallbackMethod = "getLicenseByOrgFallbackMethod",//后备模式，后备方法必须于主方法在同一个类种
            threadPoolKey = "licenseByOrgThreadPool", //舱壁模式，hystrix默认用同一个线程池处理所有调用。这里将该调用独立出一个线程池。
            threadPoolProperties = { //配置线程池行为
                    @HystrixProperty(name = "coreSize", value = "30"), //设置线程池的大小
                    @HystrixProperty(name = "maxQueueSize", value = "10") //设置线程池前面最大队列的大小，如果设置-1，则不使用队列
            }
    )
    public List<License> getLicenseByOrg(String organizationId) {
        log.info("getLicenseByOrg Correlation id:{}", UserContextHolder.getContext().getCorrelationId());
        randomTimeout();
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public List<License> findAll() {
        return licenseRepository.findAll();
    }

    /**
     * 随机进入sleep，模仿调用超时失败的情况
     */
    private void randomTimeout() {
        Random r = new Random();
        int num = r.nextInt(4);
        if(num == 3) {
            try {
                Thread.sleep(11000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 后备模式，当hystrix的熔断器中断调用后，选择执行该方法作为替代方案
     * @param organizationId
     * @return
     */
    private List<License> getLicenseByOrgFallbackMethod(String organizationId) {
        License license = new License();
        license.setProjectName("Sorry no licensing information currently available");
        license.setComment("后备方法执行");
        return List.of(license);
    }
}
