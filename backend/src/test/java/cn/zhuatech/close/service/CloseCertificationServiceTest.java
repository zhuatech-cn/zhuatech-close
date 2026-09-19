/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class CloseCertificationServiceTest {
    private final CloseCertificationService service = new CloseCertificationService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void certifiesControlledClosePeriod() {
        var result = service.assess(new CloseCertificationService.Request("2026-08", true, true, true,
                true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(CloseCertificationService.Decision.CERTIFY);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void routesIncompleteCloseWorkToReconciliation() {
        var result = service.assess(new CloseCertificationService.Request("2026-08", false, false, true,
                true, false, false, true, true, false, true));
        assertThat(result.actions()).hasSize(5);
        assertThat(result.decision()).isEqualTo(CloseCertificationService.Decision.RECONCILE);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnbalancedOrUncontrolledClose() {
        var result = service.assess(new CloseCertificationService.Request("", false, false, false,
                false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(6);
        assertThat(result.decision()).isEqualTo(CloseCertificationService.Decision.BLOCKED);
    }
}
