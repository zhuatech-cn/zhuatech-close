/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.config;
import cn.zhuatech.close.model.*;
import cn.zhuatech.close.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Bean CommandLineRunner seed(BusinessRecordRepository records,SystemSettingRepository settings){return args->{
        if(records.count()>0)return;
            settings.save(new SystemSetting("closePeriod","2026-08"));
    settings.save(new SystemSetting("currency","CNY"));
    settings.save(new SystemSetting("materiality","10000"));
    settings.save(new SystemSetting("consolidationMode","全资子公司全额合并"));
            records.save(new BusinessRecord("CLS-20260826-001","CHECKLIST","2026年8月集团关账清单","集团财务","关账经理","处理中",new BigDecimal("0"),46,LocalDate.now().plusDays(2),"关注","剩余6项任务未完成"));
    records.save(new BusinessRecord("CLS-20260826-002","RECONCILIATION","上海主体银行账户对账","上海主体","总账会计","待复核",new BigDecimal("12800"),8,LocalDate.now().plusDays(1),"正常","两笔在途款已说明"));
    records.save(new BusinessRecord("CLS-20260826-003","JOURNAL","集团内部服务费抵销","上海与杭州主体","合并会计","待开始",new BigDecimal("960000"),12,LocalDate.now().plusDays(3),"正常","等待双方往来确认"));
    records.save(new BusinessRecord("CLS-20260826-004","REPORT","八月集团合并财务报表","集团合并范围","财务负责人","已关闭",new BigDecimal("28600000"),9,LocalDate.now().plusDays(-4),"正常","资产负债表勾稽通过"));
    };}
}
