/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.controller;import cn.zhuatech.close.common.ApiResponse;import cn.zhuatech.close.service.EnterpriseCloseService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/close") public class EnterpriseCloseController {private final EnterpriseCloseService service;/**
                                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                               */
public EnterpriseCloseController(EnterpriseCloseService service){this.service=service;}/**
                                                                                                                                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                      */
@PostMapping("/validate-trial-balance") ApiResponse<EnterpriseCloseService.ValidationResult> validate(@Valid @RequestBody EnterpriseCloseService.TrialBalanceRequest request){return ApiResponse.ok(service.validate(request));}}
