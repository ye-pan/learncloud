package com.yp.learncloud.specialroute.service;

import com.yp.learncloud.specialroute.exception.NoRouteFound;
import com.yp.learncloud.specialroute.model.AbTestingRoute;
import com.yp.learncloud.specialroute.repository.AbTestingRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbTestingRouteService {
    private AbTestingRouteRepository abTestingRouteRepository;

    public AbTestingRouteService(AbTestingRouteRepository abTestingRouteRepository) {
        this.abTestingRouteRepository = abTestingRouteRepository;
    }

    public void save(AbTestingRoute route) {
        abTestingRouteRepository.save(route);
    }

    public AbTestingRoute getRoute(String serviceName) {
        return abTestingRouteRepository.findById(serviceName).orElseGet(() -> {
            throw new NoRouteFound();
        });
    }

    public void deleteRoute(String serviceName) {
        abTestingRouteRepository.deleteById(serviceName);
    }

    public List<AbTestingRoute> findAll() {
        return abTestingRouteRepository.findAll();
    }
}
