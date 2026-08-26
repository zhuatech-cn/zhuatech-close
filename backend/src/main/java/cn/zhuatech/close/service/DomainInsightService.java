/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.close.service;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.*;
import java.util.*;
@Service
public class DomainInsightService {
    public Map<String,Object> analyze(InsightRequest req){
        Map<String,Object> result=new LinkedHashMap<>();
        if(req.completedTasks()>req.totalTasks())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"已完成任务不能超过任务总数");
BigDecimal rate=BigDecimal.valueOf(req.completedTasks()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(req.totalTasks()),2,RoundingMode.HALF_UP);
boolean blocked=req.unresolvedReconciliations()>0||req.unpostedJournals()>0||req.validationErrors()>0;
result.put("completionRate",rate);result.put("openIssues",req.unresolvedReconciliations()+req.unpostedJournals()+req.validationErrors());result.put("decision",blocked?"BLOCKED":rate.compareTo(BigDecimal.valueOf(100))==0?"READY_TO_CLOSE":"READY_WITH_WARNING");
        return result;
    }
    private BigDecimal rate(long numerator,long denominator){return denominator==0?BigDecimal.ZERO:BigDecimal.valueOf(numerator).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(denominator),2,RoundingMode.HALF_UP);}
    public record InsightRequest(@Positive int totalTasks, @PositiveOrZero int completedTasks, @PositiveOrZero int unresolvedReconciliations, @PositiveOrZero int unpostedJournals, @PositiveOrZero int validationErrors){}
}
