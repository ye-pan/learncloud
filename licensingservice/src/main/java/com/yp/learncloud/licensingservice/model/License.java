package com.yp.learncloud.licensingservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "licenses")
public class License {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "license_id")
    private String id;

    @Column(name = "organization_id")
    private String organizationId;

    @Column(name = "product_name")
    private String projectName;

    @Column(name = "license_type")
    private String licenseType;

    @Column(name = "license_max")
    private Integer licenseMax;

    @Column(name = "license_allocated")
    private Integer licenseAllocated;

    @Column(name = "comment")
    private String comment;
}
