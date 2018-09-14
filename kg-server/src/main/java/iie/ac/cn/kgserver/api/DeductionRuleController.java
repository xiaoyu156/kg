package iie.ac.cn.kgserver.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import iie.ac.cn.common.utils.Response;
import iie.ac.cn.kgserver.domain.DeductionRule;
import iie.ac.cn.kgserver.domain.DeductionType;
import iie.ac.cn.kgserver.servcie.IDeductionRuleService;
import iie.ac.cn.kgserver.servcie.IDeductionService;
import iie.ac.cn.kgserver.servcie.IDeductionTypeService;
import iie.ac.cn.kgserver.servcie.IOperateKgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deduction-rule")
@Slf4j
public class DeductionRuleController extends BaseController {

    public DeductionRuleController(IDeductionService deductionService, IDeductionTypeService deductionTypeService, IDeductionRuleService iDeductionRuleService, IOperateKgService operateKgService) {
        super(deductionService, deductionTypeService, iDeductionRuleService, operateKgService);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response addRule(@RequestBody String params) {
        Gson gson = new Gson();
        DeductionRule deductionRule = new DeductionRule();
        if (StringUtils.isBlank(params)) {
            return Response.paramError("params error!");
        }
        JsonObject jsonObject;
        try {
            jsonObject = gson.fromJson(params, JsonObject.class);
        } catch (Exception e) {
            log.error("json transfer error", e);
            return Response.paramError("json transfer error!");
        }
        //演绎规则描述
        if (jsonObject.get("rule_desc") == null || StringUtils.isBlank(jsonObject.get("rule_desc").getAsString())) {
            return Response.paramError("rule_desc can not null!");
        }
        deductionRule.setRuleDesc(jsonObject.get("rule_desc").getAsString());
        //演绎规则内容
        if (jsonObject.get("rule_content") == null || StringUtils.isBlank(jsonObject.get("rule_content").getAsString())) {
            return Response.paramError("rule_content can not null");
        }
        deductionRule.setRuleContent(jsonObject.get("rule_content").getAsString());
        //演绎级别
        if (jsonObject.get("level") == null) {
            return Response.paramError("level can not null");
        }
        deductionRule.setLevel(jsonObject.get("level").getAsInt());
        //yh等级
        if (jsonObject.get("harm_level") == null) {
            return Response.paramError("harm_level can not null");
        }

        if (jsonObject.get("type_id") != null && !StringUtils.isBlank(jsonObject.get("type_id").getAsString())) {
            String typeId = jsonObject.get("type_id").getAsString();
            DeductionType type = this.deductionTypeService.findById(typeId);
            if (type == null) {
                return Response.paramError("deductionType not exist ,please check type_id");
            }
            deductionRule.setDeductionType(type);
        }
        deductionRule.setHarmLevel(jsonObject.get("harm_level").getAsInt());
        this.iDeductionRuleService.saveRule(deductionRule);
        return Response.operateSucessNoData();
    }
}
