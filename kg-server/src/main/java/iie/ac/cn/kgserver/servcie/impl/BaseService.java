package iie.ac.cn.kgserver.servcie.impl;

import iie.ac.cn.kgserver.config.SystemConfig;
import iie.ac.cn.kgserver.repository.DeductionRuleRepository;
import iie.ac.cn.kgserver.repository.DeductionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 服务层基类
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/10 0010 下午 15:37
 * @Version: 1.0
 */
public class BaseService {

    DeductionRuleRepository deductionRuleRepository;
    DeductionTypeRepository deductionTypeRepository;
    SystemConfig systemConfig;

    @Autowired

    public BaseService(DeductionRuleRepository deductionRuleRepository, DeductionTypeRepository deductionTypeRepository, SystemConfig systemConfig) {
        this.deductionRuleRepository = deductionRuleRepository;
        this.deductionTypeRepository = deductionTypeRepository;
        this.systemConfig = systemConfig;
    }
}
