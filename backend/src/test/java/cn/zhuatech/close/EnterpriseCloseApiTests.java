/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc class EnterpriseCloseApiTests {@Autowired MockMvc mvc;
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void balancedEntitiesAndEliminationsCanClose() throws Exception {mvc.perform(post("/api/enterprise/close/validate-trial-balance").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"period":"2026-08","eliminationDebits":100,"eliminationCredits":100,"lines":[{"entityCode":"SH","accountCode":"1001","debit":1000,"credit":0,"currency":"CNY","exchangeRate":1},{"entityCode":"SH","accountCode":"6001","debit":0,"credit":1000,"currency":"CNY","exchangeRate":1}]}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").value("READY_TO_CLOSE")).andExpect(jsonPath("$.data.entities[0].difference").value(0.0));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void imbalanceBlocksClose() throws Exception {mvc.perform(post("/api/enterprise/close/validate-trial-balance").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"period":"2026-08","eliminationDebits":0,"eliminationCredits":10,"lines":[{"entityCode":"SH","accountCode":"1001","debit":1000,"credit":900,"currency":"CNY","exchangeRate":1}]}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").value("BLOCKED")).andExpect(jsonPath("$.data.errors.length()").value(2));}
}
