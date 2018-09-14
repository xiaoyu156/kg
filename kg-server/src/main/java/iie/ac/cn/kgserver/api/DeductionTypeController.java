package iie.ac.cn.kgserver.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import iie.ac.cn.common.utils.Response;
import iie.ac.cn.kgserver.domain.DeductionType;
import iie.ac.cn.kgserver.servcie.IDeductionRuleService;
import iie.ac.cn.kgserver.servcie.IDeductionService;
import iie.ac.cn.kgserver.servcie.IDeductionTypeService;
import iie.ac.cn.kgserver.servcie.IOperateKgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deduction-type ")
public class DeductionTypeController extends BaseController {


    public DeductionTypeController(IDeductionService deductionService, IDeductionTypeService deductionTypeService, IDeductionRuleService iDeductionRuleService, IOperateKgService operateKgService) {
        super(deductionService, deductionTypeService, iDeductionRuleService, operateKgService);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody String params) {
        Gson gson = new Gson();
        DeductionType deductionType = new DeductionType();
        if (StringUtils.isBlank(params)) {
            return Response.paramError("params error!");
        }
        JsonObject jsonObject = null;
        try {
            jsonObject = gson.fromJson(params, JsonObject.class);
        } catch (Exception e) {
            return Response.paramError("json transfer error!");
        }
        //演绎类型
        if (jsonObject.get("name") == null || StringUtils.isBlank(jsonObject.get("name").getAsString())) {
            return Response.paramError("name can not null!");
        }
        deductionType.setName(jsonObject.get("name").getAsString());
        //演绎类型包含字根类型
        if (jsonObject.get("rootList") == null || StringUtils.isBlank(jsonObject.get("rootList").getAsString())) {
            return Response.paramError("rootList can not null");
        }
        deductionType.setRootList(jsonObject.get("rootList").getAsString());
        DeductionType type = this.deductionTypeService.saveDeductionType(deductionType);
        return Response.operateSucessNoData();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response list() {
        return Response.operateSucessAndHaveData(this.deductionTypeService.findAll());
    }

}
