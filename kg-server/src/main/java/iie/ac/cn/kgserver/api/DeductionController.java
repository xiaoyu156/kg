package iie.ac.cn.kgserver.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import iie.ac.cn.common.utils.Response;
import iie.ac.cn.kgserver.config.Constant;
import iie.ac.cn.kgserver.servcie.IDeductionRuleService;
import iie.ac.cn.kgserver.servcie.IDeductionService;
import iie.ac.cn.kgserver.servcie.IDeductionTypeService;
import iie.ac.cn.kgserver.servcie.IOperateKgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/deduction")
@Slf4j
public class DeductionController extends BaseController {

    public DeductionController(IDeductionService deductionService, IDeductionTypeService deductionTypeService, IDeductionRuleService iDeductionRuleService, IOperateKgService operateKgService) {
        super(deductionService, deductionTypeService, iDeductionRuleService, operateKgService);
    }

    @RequestMapping(value = "/keywords", method = RequestMethod.POST)
    public Response deduction(@RequestBody String params) {
        Gson gson = new Gson();
        //数据解析校验
        if (StringUtils.isBlank(params)) {
            return Response.paramError("params can not null");
        }
        JsonObject jsonObject;
        try {
            jsonObject = gson.fromJson(params, JsonObject.class);
        } catch (Exception e) {
            log.error("json transfer error", e);
            return Response.paramError("json transfer error");
        }
        //获取原始词
        if (jsonObject.get("base_word") == null || StringUtils.isBlank(jsonObject.get("base_word").getAsString())) {
            return Response.paramError("base_word can not null");
        }
        String baseWord = jsonObject.get("base_word").getAsString();

        //获取本体类型
        if (jsonObject.get("search_type") == null || StringUtils.isBlank(jsonObject.get("search_type").getAsString())) {
            return Response.paramError("search_type can not null");
        }
        String saerchType = jsonObject.get("search_type").getAsString();

        Object result = null;
        if (Constant.PEOPLE_WORD.equalsIgnoreCase(saerchType)) {
            if (jsonObject.get("level") == null) {
                return Response.paramError("level can not null");
            }
            int level = jsonObject.get("level").getAsInt();
            JsonElement ids = jsonObject.get("type_ids");
            if (ids == null || !ids.isJsonArray()) {
                return Response.paramError("type_ids valid");
            }
            List<String> typeList = gson.fromJson(ids.getAsJsonArray(), new TypeToken<List<String>>() {
            }.getType());
            result = this.deductionService.deductionPerson(baseWord, typeList, level);
        } else if (Constant.ORGANIZATION_WORD.equalsIgnoreCase(saerchType)) {
            result = this.deductionService.deductionOrganization(baseWord);
        } else if (Constant.PLACE_WORD.equalsIgnoreCase(saerchType)) {
            result = this.deductionService.deductionPlace(baseWord);
        } else {
            return Response.paramError("search_type valid");
        }
        return Response.operateSucessAndHaveData(result);
    }

}
