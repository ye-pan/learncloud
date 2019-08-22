package com.yp.learncloud.specialroute.controller;

import com.yp.learncloud.specialroute.model.AbTestingRoute;
import com.yp.learncloud.specialroute.service.AbTestingRouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/route/")
public class SpecialRouteController {

    private AbTestingRouteService abTestingRouteService;

    public SpecialRouteController(AbTestingRouteService abTestingRouteService) {
        this.abTestingRouteService = abTestingRouteService;
    }

    @GetMapping("/abtesting/{serviceName}")
    public AbTestingRoute abTestingRoute(@PathVariable("serviceName") String serviceName) {
        return abTestingRouteService.getRoute(serviceName);
    }

    @GetMapping("/abtesting/all")
    public List<AbTestingRoute> all() {
        return abTestingRouteService.findAll();
    }

    @PostMapping("/abtesting")
    public void saveRoute(@RequestBody AbTestingRoute route) {
        abTestingRouteService.save(route);
    }
}
