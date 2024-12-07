package com.yygx.oshiapp.service.impl;

import com.yygx.oshiapp.service.ICpuLoadRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;


@Service
public class CpuLoadRestServiceImpl implements ICpuLoadRestService {

    @Autowired
    SystemInfo systemInfo;

    @Override
    public double[] getCpuLoad() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        return processor.getProcessorCpuLoad(1000);
    }
}