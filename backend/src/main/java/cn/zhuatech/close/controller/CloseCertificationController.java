/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.controller;

import cn.zhuatech.close.common.ApiResponse;
import cn.zhuatech.close.service.CloseCertificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/close")
public class CloseCertificationController {
    private final CloseCertificationService service;
    public CloseCertificationController(CloseCertificationService service) { this.service = service; }

    @PostMapping("/period-certification")
    public ApiResponse<?> assess(@RequestBody CloseCertificationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
