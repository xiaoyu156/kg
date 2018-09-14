package iie.ac.cn.kgserver.servcie;

import iie.ac.cn.kgserver.domain.DeductionRule;

import java.util.List;

public interface IDeductionRuleService {
    public DeductionRule saveRule(DeductionRule deductionRule);

    public List<DeductionRule> queryAll();

    public DeductionRule queryById(String id);
}
