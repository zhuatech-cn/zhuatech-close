/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EnterpriseCloseService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public ValidationResult validate(@Valid TrialBalanceRequest req){
  Map<String,BigDecimal> debits=new LinkedHashMap<>(),credits=new LinkedHashMap<>();List<String> errors=new ArrayList<>();
  for(var line:req.lines()){
   BigDecimal rate=line.exchangeRate();if(rate.signum()<=0){errors.add(line.entityCode()+"/"+line.accountCode()+" 汇率无效");continue;}
   debits.merge(line.entityCode(),line.debit().multiply(rate),BigDecimal::add);credits.merge(line.entityCode(),line.credit().multiply(rate),BigDecimal::add);
  }
  List<EntityBalance> entities=new ArrayList<>();Set<String> codes=new LinkedHashSet<>(debits.keySet());codes.addAll(credits.keySet());
  for(String code:codes){BigDecimal debit=money(debits.getOrDefault(code,BigDecimal.ZERO));BigDecimal credit=money(credits.getOrDefault(code,BigDecimal.ZERO));BigDecimal diff=money(debit.subtract(credit));if(diff.abs().compareTo(new BigDecimal(".01"))>0)errors.add(code+" 试算不平衡");entities.add(new EntityBalance(code,debit,credit,diff));}
  BigDecimal eliminationDiff=money(req.eliminationDebits().subtract(req.eliminationCredits()));if(eliminationDiff.abs().compareTo(new BigDecimal(".01"))>0)errors.add("抵销分录不平衡");
  return new ValidationResult(req.period(),entities,eliminationDiff,errors,errors.isEmpty()?"READY_TO_CLOSE":"BLOCKED");
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private BigDecimal money(BigDecimal v){return v.setScale(2,RoundingMode.HALF_UP);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record TrialBalanceRequest(@NotBlank @Pattern(regexp="\\d{4}-(0[1-9]|1[0-2])") String period,@NotEmpty List<@Valid AccountLine> lines,
  @NotNull @DecimalMin("0") BigDecimal eliminationDebits,@NotNull @DecimalMin("0") BigDecimal eliminationCredits){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record AccountLine(@NotBlank String entityCode,@NotBlank String accountCode,@NotNull @DecimalMin("0") BigDecimal debit,@NotNull @DecimalMin("0") BigDecimal credit,@NotBlank String currency,@NotNull BigDecimal exchangeRate){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record EntityBalance(String entityCode,BigDecimal debit,BigDecimal credit,BigDecimal difference){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ValidationResult(String period,List<EntityBalance> entities,BigDecimal eliminationDifference,List<String> errors,String decision){}
}
