package com.yygx.oshiapp.controller;


import com.yygx.oshiapp.common.Response;
import com.yygx.oshiapp.service.ICpuLoadRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cpu")
public class CpuLoadRestController {


    @Autowired
    private ICpuLoadRestService cpuLoadRestService;

    @RequestMapping("/cpuLoad")
    public Response getCpuLoad() {
        return Response.success(cpuLoadRestService.getCpuLoad());
    }

}