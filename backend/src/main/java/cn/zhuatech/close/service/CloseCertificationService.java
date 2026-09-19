/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class CloseCertificationService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.periodId() == null || request.periodId().isBlank()) blockers.add("关账期间不能为空");
        if (!request.intercompanyBalanced()) blockers.add("内部往来未平衡");
        if (!request.trialBalanceBalanced()) blockers.add("试算平衡未通过");
        if (!request.materialAdjustmentsApproved()) blockers.add("重大调整分录未审批");
        if (!request.reviewerSeparated()) blockers.add("制单与关账复核未职责分离");
        if (!request.auditReady()) blockers.add("关账认证证据不完整");
        if (!request.subledgersClosed()) actions.add("完成子账关账");
        if (!request.bankReconciled()) actions.add("完成银行对账");
        if (!request.eliminationsApproved()) actions.add("审批合并抵销分录");
        if (!request.taxReviewed()) actions.add("完成税务复核");
        if (!request.managementSignoff()) actions.add("取得管理层签署");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.CERTIFY : Decision.RECONCILE;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { CERTIFY, RECONCILE, BLOCKED }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(String periodId, boolean subledgersClosed, boolean bankReconciled,
                          boolean intercompanyBalanced, boolean trialBalanceBalanced,
                          boolean eliminationsApproved, boolean taxReviewed,
                          boolean materialAdjustmentsApproved, boolean reviewerSeparated,
                          boolean managementSignoff, boolean auditReady) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
