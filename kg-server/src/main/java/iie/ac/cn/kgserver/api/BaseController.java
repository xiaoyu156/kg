package iie.ac.cn.kgserver.api;

import iie.ac.cn.kgserver.servcie.IDeductionRuleService;
import iie.ac.cn.kgserver.servcie.IDeductionService;
import iie.ac.cn.kgserver.servcie.IDeductionTypeService;
import iie.ac.cn.kgserver.servcie.IOperateKgService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/11 0011 上午 10:25
 * @Version: 1.0
 */
public class BaseController {

    IDeductionService deductionService;
    IDeductionTypeService deductionTypeService;
    IDeductionRuleService iDeductionRuleService;
    IOperateKgService operateKgService;

    @Autowired
    public BaseController(IDeductionService deductionService, IDeductionTypeService deductionTypeService,
                          IDeductionRuleService iDeductionRuleService, IOperateKgService operateKgService) {
        this.deductionService = deductionService;
        this.deductionTypeService = deductionTypeService;
        this.iDeductionRuleService = iDeductionRuleService;
        this.operateKgService = operateKgService;
    }
}
