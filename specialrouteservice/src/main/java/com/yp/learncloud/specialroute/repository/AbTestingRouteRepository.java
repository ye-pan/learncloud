package com.yp.learncloud.specialroute.repository;

import com.yp.learncloud.specialroute.model.AbTestingRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbTestingRouteRepository extends JpaRepository<AbTestingRoute, String> {
}
