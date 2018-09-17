package iie.ac.cn.kgserver.servcie.impl;

import iie.ac.cn.common.utils.CartesianProduct;
import iie.ac.cn.common.utils.PdfUtil;
import iie.ac.cn.kgserver.config.SystemConfig;
import iie.ac.cn.kgserver.domain.DeductionRule;
import iie.ac.cn.kgserver.domain.DeductionType;
import iie.ac.cn.kgserver.model.DeductionResult;
import iie.ac.cn.kgserver.model.RootMap;
import iie.ac.cn.kgserver.repository.DeductionRuleRepository;
import iie.ac.cn.kgserver.repository.DeductionTypeRepository;
import iie.ac.cn.kgserver.servcie.IDeductionService;
import iie.ac.cn.kgserver.servcie.IDeductionTypeService;
import iie.ac.cn.kgserver.servcie.IOperateKgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static iie.ac.cn.kgserver.config.Constant.DEDUCTION_ELEMENT_SEPARATOR;
import static iie.ac.cn.kgserver.config.Constant.DEDUCTION_TYPE_SEPARATOR;


@Service
@Slf4j
public class DeductionServiceImpl extends BaseService implements IDeductionService {

    @Autowired
    private IOperateKgService operateKgService;

    @Autowired
    private IDeductionTypeService deductionTypeService;

    /**
     * 百家姓列表
     */
    private List<String> familyNameList;

    public DeductionServiceImpl(DeductionRuleRepository deductionRuleRepository, DeductionTypeRepository deductionTypeRepository, SystemConfig systemConfig) {
        super(deductionRuleRepository, deductionTypeRepository, systemConfig);
        familyNameList = PdfUtil.readPdfByLine(systemConfig.getFamilyNamePath());
    }

    @Override
    public DeductionResult deductionPerson(String baseWord, List<String> typeIds, int level) {
        /*
        1.用原始词到图谱中查询，如果演绎过返回结果，组织返回；如果没有演绎过，则开始演绎。
         */
        operateKgService.operateKgService("", "", "");
        /*
        2.用原始词到图谱中查询人物本体，得到人物的姓和名；如果图谱不存在则自己拆分；得到人物的姓和名
         */
        Map<String, Object> nameRoots = subNameRoots(baseWord);
        /*
        3.到数据库查询得到演绎类型以及规则
         */
        List<DeductionType> deductionTypes = this.deductionTypeService.findAllByIds(typeIds);
        /*
        4.构建字根map
        */
        RootMap rootMap = buildRootMap(baseWord, level, deductionTypes, nameRoots);
        /*
        5.开始演绎生成人物指代词
         */
        return deduction(deductionTypes, rootMap);
    }

    @Override
    public Object deductionPlace(String baseWord) {
        return null;
    }

    @Override
    public Object deductionOrganization(String baseWord) {
        return null;
    }

    /**
     * 人物指代词演绎生成
     *
     * @param deductionTypeList 演绎类型集合
     * @param rootMap           变异字根数据结构
     * @return iie.ac.cn.kgserver.model.DeductionResult
     * @date 2018/9/13 0013 下午 15:03
     */
    public DeductionResult deduction(List<DeductionType> deductionTypeList, RootMap rootMap) {
        int sum = 0;
        for (DeductionType deductionType : deductionTypeList) {
            log.info("解析演绎类型：" + deductionType);
            Set<DeductionRule> deductionRuleSet = deductionType.getDeductionRuleSet();
            List<String> keywords = new ArrayList<>();
            for (DeductionRule deductionRule : deductionRuleSet) {
                String ruleContent = deductionRule.getRuleContent();
                //以组合类型分开
                String[] typeTemp = ruleContent.split(DEDUCTION_TYPE_SEPARATOR);

                List<List<String>> cartansionParam = new ArrayList<>();
                List<String>[] familyArray = new List[2];
                familyArray[0] = new ArrayList<>();
                familyArray[1] = new ArrayList<>();
                List<String>[] lastArray = new List[2];
                lastArray[0] = new ArrayList<>();
                lastArray[1] = new ArrayList<>();
                List<String> other = new ArrayList<>();
                for (String rule : typeTemp) {
                    final String[] split = rule.split(DEDUCTION_ELEMENT_SEPARATOR);
                    for (String aSplit : split) {
                        String element = aSplit;
                        String regex1 = "F";
                        String regex2 = "L";
                        if (element.contains(regex1)) {
                            for (int i = 1; i <= rootMap.getFamilyNum(); i++) {
                                String tempStr = element + i;
                                System.out.println(tempStr);
                                List<String> temp = rootMap.getRootByKey(tempStr);
                                System.out.println(temp);
                                if (i == 1) {
                                    familyArray[0].addAll(temp);
                                }
                                if (i == 2) {
                                    familyArray[1].addAll(temp);
                                }
                            }
                        } else if (element.contains(regex2)) {
                            for (int i = 1; i <= rootMap.getLastNum(); i++) {
                                String tempStr = element + i;
                                List<String> temp = rootMap.getRootByKey(tempStr);
                                if (i == 1) {
                                    lastArray[0].addAll(temp);
                                }
                                if (i == 2) {
                                    lastArray[1].addAll(temp);
                                }
                            }
                        } else {
                            List<String> temp = rootMap.getRootByKey(element);
                            if (!temp.isEmpty())
                                other.addAll(temp);
                        }

                    }
                }
                if (!familyArray[0].isEmpty() && familyArray[0].size() > 0) {
                    cartansionParam.add(familyArray[0]);
                }
                if (!familyArray[1].isEmpty() && familyArray[1].size() > 0) {
                    cartansionParam.add(familyArray[1]);
                }
                if (!lastArray[0].isEmpty() && lastArray[0].size() > 0) {
                    cartansionParam.add(lastArray[0]);
                }
                if (!lastArray[1].isEmpty() && lastArray[1].size() > 0) {
                    cartansionParam.add(lastArray[1]);
                }
                if (!other.isEmpty()) {
                    cartansionParam.add(other);
                }
                List<String> result = CartesianProduct.cartesionProduct(cartansionParam);
                result.remove(rootMap.getOriginalWord());
                keywords.addAll(result);
                sum = +keywords.size();
            }
            deductionType.setKeywords(keywords);
        }
        //3.结果封装
        DeductionResult deductionResult = new DeductionResult();
        deductionResult.setKeywordNum(sum);
        deductionResult.setDeductionTypes(deductionTypeList);
        return deductionResult;
    }

    /**
     * 构建变异字根数据结构
     *
     * @param baseWord       原始词
     * @param deductionLevel 演绎级别
     * @param deductionTypes 演绎类型集合
     * @param subNameRoots   原始词字根组合
     * @return iie.ac.cn.kgserver.model.RootMap
     * @date 2018/9/13 0013 下午 15:05
     */
    private RootMap buildRootMap(String baseWord, int deductionLevel, List<DeductionType> deductionTypes,
                                 Map<String, Object> subNameRoots) {
        RootMap rootMap = new RootMap();
        rootMap.setOriginalWord(baseWord);
        rootMap.setDeductLevel(deductionLevel);
        rootMap.setFamilyList((List<String>) subNameRoots.get("familyNames"));
        rootMap.setLastList((List<String>) subNameRoots.get("lastNames"));
        rootMap.setFamilyNum((Integer) subNameRoots.get("familyNameSize"));
        rootMap.setLastNum((Integer) subNameRoots.get("lastNameSize"));
        //本次演绎需要的关系类型集合
        Set<String> rootKey = getRootKey(deductionTypes);

        for (String key : rootKey) {
            String newKey = key;

            //需要适配成图数据库的关系类型，调用图数据库操作服务
            if ("F".equalsIgnoreCase(key) || "M".equalsIgnoreCase(key)) {
                for (int i = 1; i <= rootMap.getFamilyNum(); i++) {
                    rootMap.putRoot(key + i, rootMap.getFamilyList().get(i - 1));
                }
            } else if ("F`".equalsIgnoreCase(key)) {
                for (int i = 1; i <= rootMap.getFamilyNum(); i++) {
                    newKey = key + i;
                    rootMap.getFamilyList().get(i - 1);
                }
            } else if ("M`".equalsIgnoreCase(key)) {
                for (int i = 1; i <= rootMap.getLastNum(); i++) {
                    newKey = key + i;
                    rootMap.getLastList().get(i - 1);
                }
            } else {
//                ，A-称谓词，D-负面词，R-关系词，P-前缀词，头衔词-T
//                ENR
            }

        }
        return new RootMap();
    }

    /**
     * 分割姓名
     *
     * @param baseWord 原始词
     */
    private Map<String, Object> subNameRoots(String baseWord) {
        Map<String, Object> result = new HashMap<>();
        String familyName = getFamilyName(baseWord);
        int familyNameSize = familyName.length();
        String lastName = baseWord.replace(familyName, "");
        int lastNameSize = lastName.length();
        char[] familyNames = familyName.toCharArray();
        char[] lastNames = lastName.toCharArray();
        String familyNameStr = StringUtils.join(familyNames, ',');
        String lastNameStr = StringUtils.join(lastNames, ',');
        result.put("familyNames", Arrays.asList(familyNameStr.split(",")));
        result.put("lastNames", Arrays.asList(lastNameStr.split(",")));
        result.put("familyNameSize", familyNameSize);
        result.put("lastNameSize", lastNameSize);
        return result;
    }

    /**
     * 从人名中提取姓
     *
     * @param baseWord 姓名原始词
     * @return java.lang.String 姓
     * @date 2018/9/13 0013 上午 8:45
     */
    private String getFamilyName(String baseWord) {
        String result = "";
        List<String> familyName = new ArrayList<>();
        /*
        把所有命中百家姓的姓放入到list
         */
        for (String family : familyNameList) {
            String f = StringUtils.strip(family).trim();
            if (baseWord.contains(f)) {
                familyName.add(f);
            }
        }
        /*
        如果只命中了，就默认返回命中的第一个姓，然后遍历命中的姓列表，如果有复姓即长度为2，
        则修改返回
         */
        if (familyName.size() > 0) {
            result = familyName.get(0);
            for (String s : familyName) {
                if (s.length() == 2) {
                    result = s;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 字根集合key去重
     *
     * @param deductionTypeList 演绎类型集合
     * @return java.util.Set<java.lang.String>
     * @date 2018/9/7 0007 上午 9:05
     */
    private Set<String> getRootKey(List<DeductionType> deductionTypeList) {
        Set<String> keySet = new HashSet<>();
        for (DeductionType deductionType : deductionTypeList) {
            String[] rootStr = deductionType.getRootList().split(",");
            Collections.addAll(keySet, rootStr);
        }
        return keySet;
    }
}


