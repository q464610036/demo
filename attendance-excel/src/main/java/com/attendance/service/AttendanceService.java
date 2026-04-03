package com.attendance.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 考勤核心业务接口
 * 处理Excel上传、解析、考勤计算、Excel导出
 */
public interface AttendanceService {
    /**
     * 处理考勤主方法
     * @param checkFile 打卡记录Excel
     * @param leaveFile 请假记录Excel
     * @param outFile 外出记录Excel
     * @param tripFile 出差记录Excel
     * @param response 响应对象（用于导出Excel）
     */
    void processAttendance(MultipartFile checkFile,
                           MultipartFile leaveFile,
                           MultipartFile outFile,
                           MultipartFile tripFile,
                           HttpServletResponse response) throws Exception;
}