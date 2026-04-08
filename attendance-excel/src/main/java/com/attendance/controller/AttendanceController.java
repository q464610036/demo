package com.attendance.controller;

import com.attendance.service.AttendanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 考勤控制器
 * 提供Excel上传/处理/导出接口
 */
@RestController
@RequestMapping("/att")
public class AttendanceController {

    // 注入业务层（构造器注入，推荐）
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * 考勤处理核心接口
     * @param checkFile 打卡记录Excel（参数名：checkFile）
     * @param leaveFile 请假记录Excel（参数名：leaveFile）
     * @param outFile 外出记录Excel（参数名：outFile）
     * @param tripFile 出差记录Excel（参数名：tripFile）
     * @param overtimeFile 加班记录Excel（参数名：overtimeFile）
     * @param response 响应对象（自动下载Excel）
     */
    @PostMapping("/process")
    public void processAttendance(
            @RequestParam("checkFile") MultipartFile checkFile,
            @RequestParam("leaveFile") MultipartFile leaveFile,
            @RequestParam("outFile") MultipartFile outFile,
            @RequestParam("tripFile") MultipartFile tripFile,
            @RequestParam(value = "overtimeFile", required = false) MultipartFile overtimeFile,
            HttpServletResponse response) throws Exception {
        attendanceService.processAttendance(checkFile, leaveFile, outFile, tripFile, overtimeFile, response);
    }
}