package iie.ac.cn.kgserver.servcie.impl;

import iie.ac.cn.kgserver.config.SystemConfig;
import iie.ac.cn.kgserver.domain.DeductionRule;
import iie.ac.cn.kgserver.repository.DeductionRuleRepository;
import iie.ac.cn.kgserver.repository.DeductionTypeRepository;
import iie.ac.cn.kgserver.servcie.IDeductionRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeductionRuleServiceImpl extends BaseService implements IDeductionRuleService {


    public DeductionRuleServiceImpl(DeductionRuleRepository deductionRuleRepository, DeductionTypeRepository deductionTypeRepository, SystemConfig systemConfig) {
        super(deductionRuleRepository, deductionTypeRepository, systemConfig);
    }

    public DeductionRule saveRule(DeductionRule deductionRule) {
        return this.deductionRuleRepository.save(deductionRule);
    }

    public List<DeductionRule> queryAll() {
        return this.deductionRuleRepository.findAll();
    }

    public DeductionRule queryById(String id) {
        return this.deductionRuleRepository.getOne(id);
    }


}
