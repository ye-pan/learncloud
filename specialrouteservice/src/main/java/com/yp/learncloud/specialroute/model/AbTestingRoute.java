package com.yp.learncloud.specialroute.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abtesting")
@Data
public class AbTestingRoute {
    @Id
    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "active")
    private String active;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "weight")
    private Integer weight;
}
