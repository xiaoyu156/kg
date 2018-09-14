package iie.ac.cn.kgserver.servcie;

import iie.ac.cn.kgserver.domain.DeductionType;

import java.util.List;

public interface IDeductionTypeService {
    DeductionType saveDeductionType(DeductionType deductionType);

    /**
     * 查询所有演绎类型
     *
     * @return java.util.List<DeductionType>
     * @date 2018/9/10 0010 下午 16:01
     */
    List<DeductionType> findAll();

    /**
     * 通过id查询演绎类型
     *
     * @param id 指代词演绎类型id
     * @return DeductionType
     * @date 2018/9/10 0010 下午 16:03
     */
    DeductionType findById(String id);

    /**
     * 通过id列表查询所有类型以及类型所包含的规则
     *
     * @param ids 演绎类型id集合
     * @return java.util.List<DeductionType>
     * @throws
     * @date 2018/9/10 0010 下午 16:09
     */
    List<DeductionType> findAllByIds(List<String> ids);

    /**
     * 删除演绎类型
     *
     * @param deductionType
     * @return boolean
     * @date 2018/9/10 0010 下午 16:22
     */
    boolean deleteDeductionType(DeductionType deductionType);
}
