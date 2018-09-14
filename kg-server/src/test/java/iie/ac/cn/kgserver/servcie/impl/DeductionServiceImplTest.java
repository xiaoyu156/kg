package iie.ac.cn.kgserver.servcie.impl;

import iie.ac.cn.kgserver.KgServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

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
    }
}