package com.yygx.oshiapp.controller;


import com.yygx.oshiapp.common.Response;
import com.yygx.oshiapp.service.ICpuLoadRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@CrossOrigin  // 允许跨域
@RestController
@RequestMapping("/metrics")
public class CpuLoadRestController {


    private static final Logger log = LoggerFactory.getLogger(CpuLoadRestController.class);
    @Autowired
    private ICpuLoadRestService cpuLoadRestService;

    @RequestMapping("/cpuLoad")
    public Response getCpuLoad() {
        double[] cpuLoad = cpuLoadRestService.getCpuLoad();
        log.info("cpuLoad: {}", Arrays.toString(cpuLoad));
        return Response.success(cpuLoad);
    }

}