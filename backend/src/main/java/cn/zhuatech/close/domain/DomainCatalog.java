/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String,WorkflowAction> actions=new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog(){
        actions.put("START", new WorkflowAction("START", "开始处理", List.of("待开始"), "处理中"));
actions.put("RECONCILE", new WorkflowAction("RECONCILE", "完成对账", List.of("处理中"), "待复核"));
actions.put("REVIEW", new WorkflowAction("REVIEW", "复核通过", List.of("待复核"), "已复核"));
actions.put("CLOSE", new WorkflowAction("CLOSE", "确认关账", List.of("已复核"), "已关闭"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName(){return "知华科技财务关账与合并报表系统";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene(){return "关账任务、科目对账、调整分录、内部抵销和合并报表管理";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus(){return "待开始";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel(){return "公司/账套";} /**
                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                 */
public String amountLabel(){return "影响金额";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel(){return "任务数";} /**
                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                  */
public String dueLabel(){return "关账截止日";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules(){return List.of(
        new ModuleDefinition("CHECKLIST","关账清单","按期间组织关账任务、责任人和截止时间"),
    new ModuleDefinition("RECONCILIATION","科目对账","处理银行、往来、存货和总分账差异"),
    new ModuleDefinition("JOURNAL","调整与抵销","登记调整分录、内部交易和抵销分录"),
    new ModuleDefinition("REPORT","合并报表","完成汇率折算、合并校验和报表发布")
    );}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String,WorkflowAction> actions(){return Collections.unmodifiableMap(actions);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to){}
}
