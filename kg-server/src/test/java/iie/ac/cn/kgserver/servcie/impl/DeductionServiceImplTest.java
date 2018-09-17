package iie.ac.cn.kgserver.servcie.impl;

import iie.ac.cn.kgserver.KgServerApplicationTests;
import iie.ac.cn.kgserver.domain.DeductionRule;
import iie.ac.cn.kgserver.domain.DeductionType;
import iie.ac.cn.kgserver.model.RootMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

public class DeductionServiceImplTest extends KgServerApplicationTests {

    @Autowired
    DeductionServiceImpl deductionService;

    @Test
    public void deductionPerson() {
    }

    @Test
    public void deductionPlace() {
    }

    @Test
    public void deductionOrganization() {
    }

    @Test
    public void getFamilyName() {
        //System.out.println(deductionService.getFamilyName("欧阳娜娜"));
    }

    @Test
    public void subNameRoots() {
        // Map<String, Object> stringObjectMap = deductionService.subNameRoots("欧阳娜娜");
        //  System.out.println(stringObjectMap);
        System.out.println(LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                for (int k = 0; k <= 10; k++) {
                    for (int m = 0; m <= 10; m++) {
                        for (int n = 0; n <= 10; n++) {
                            System.out.println(n);
                        }
                    }
                }
            }
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println(end);
        System.out.println("dddd"+(end.getSecond()-now.getSecond()));


        List<String>[] aa=new List[2];

        List<String> bb=new ArrayList<>();
        bb.add("ddd");
        bb.add("aaa");

        aa[0]=new ArrayList<>();
        System.out.println(aa[0].isEmpty());
//        if (!aa[0].isEmpty()){
//            aa[0].addAll(bb);
//        }
        System.out.println(bb);
    }

    @Test
    public void deductionPerson1() {
    }

    @Test
    public void deductionPlace1() {
    }

    @Test
    public void deductionOrganization1() {
    }

    @Test
    public void deductionPerson2() {
    }

    @Test
    public void deductionPlace2() {
    }

    @Test
    public void deductionOrganization2() {

    }

    @Test
    public void deduction() {
        List<DeductionType> deductionTypes=new ArrayList<>();
        DeductionType deductionType=new DeductionType();
        deductionType.setRootList("F,F`,F``,L,L`,L``");
        deductionType.setName("变异");

        DeductionRule deductionRule=new DeductionRule();
        deductionRule.setLevel(1);
        deductionRule.setRuleContent("F,F`,F``&L,L`,L``");
        Set<DeductionRule> rules=new HashSet<>();
        rules.add(deductionRule);
        deductionType.setDeductionRuleSet(rules);

        deductionTypes.add(deductionType);


        RootMap rootMap=new RootMap();
        rootMap.setLastNum(2);
        rootMap.setFamilyNum(1);
        List<String> family=new ArrayList<>();
        family.add("王");
        List<String> last=new ArrayList<>();
        last.add("小");
        last.add("宇");
        rootMap.setLastList(last);
        rootMap.setFamilyList(family);

        List<String> fyin=new ArrayList<>();
        fyin.add("网");
        fyin.add("汪");
        List<String> fxin=new ArrayList<>();
        fxin.add("干");
        fxin.add("主");
        rootMap.putRoot("F1",family);
        rootMap.putRoot("F`1",fyin);
        rootMap.putRoot("F``1",fxin);

        List<String> m=new ArrayList<>();
        m.add(rootMap.getLastList().get(0));
        List<String> m1=new ArrayList<>();
        m1.add(rootMap.getLastList().get(1));
        rootMap.putRoot("L1",m);
        rootMap.putRoot("L2",m1);

        List<String> m1yin=new ArrayList<>();
        m1yin.add("笑");
        m1yin.add("晓");
        List<String> m2yin=new ArrayList<>();
        m2yin.add("与");
        m2yin.add("鱼");

        List<String> m1xin=new ArrayList<>();
        m1xin.add("本");
        m1xin.add("木");
        List<String> m2xin=new ArrayList<>();
        m2xin.add("字");
        m2xin.add("学");

        rootMap.putRoot("L`1",m1yin);
        rootMap.putRoot("L`2",m2yin);
        rootMap.putRoot("L``1",m1xin);
        rootMap.putRoot("L``2",m2xin);
        System.out.println("ss"+rootMap);
        rootMap.setOriginalWord("王小宇");
        System.out.println(deductionService.deduction(deductionTypes,rootMap));

    }

    @Test
    public void testMatch(){
        System.out.println("F`".contains("F"));
    }
}