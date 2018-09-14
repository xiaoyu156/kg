package iie.ac.cn.kgserver.servcie;

import iie.ac.cn.kgserver.model.DeductionResult;

import java.util.List;

public interface IDeductionService {

    DeductionResult deductionPerson(String baseWord, List<String> typeIds, int level);

    Object deductionPlace(String baseWord);

    Object deductionOrganization(String baseWord);

}
