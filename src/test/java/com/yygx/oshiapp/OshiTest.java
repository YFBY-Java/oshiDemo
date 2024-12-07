package com.yygx.oshiapp;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.util.List;

public class OshiTest {

    // 创建 SystemInfo对象
    SystemInfo systemInfo = new SystemInfo();

    ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows  // 忽略异常
    @Test
    public void test1() {

        // 获取操作系统对象
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        List<OSProcess> processes = operatingSystem.getProcesses();
        System.out.println("进程总数：" + processes.size());

        for (OSProcess process : processes) {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(process);
            System.out.println(json);
        }
    }


    @SneakyThrows  // 忽略异常
    @Test
    public void testMemory() {
        while (true){
            Thread.sleep(1000);
            // 拿到所有硬件信息
            HardwareAbstractionLayer hardware = systemInfo.getHardware();
            // 获取内存信息
            GlobalMemory memory = hardware.getMemory();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(memory);
            System.out.println(json);
        }


    }



}