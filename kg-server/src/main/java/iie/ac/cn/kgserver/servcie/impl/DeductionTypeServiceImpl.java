package iie.ac.cn.kgserver.servcie.impl;

import iie.ac.cn.kgserver.config.SystemConfig;
import iie.ac.cn.kgserver.domain.DeductionType;
import iie.ac.cn.kgserver.repository.DeductionRuleRepository;
import iie.ac.cn.kgserver.repository.DeductionTypeRepository;
import iie.ac.cn.kgserver.servcie.IDeductionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeductionTypeServiceImpl extends BaseService implements IDeductionTypeService {


    public DeductionTypeServiceImpl(DeductionRuleRepository deductionRuleRepository, DeductionTypeRepository deductionTypeRepository, SystemConfig systemConfig) {
        super(deductionRuleRepository, deductionTypeRepository, systemConfig);
    }

    /**
     * 新增类型
     *
     * @param deductionType 演绎类型
     * @return DeductionType
     * @date 2018/9/10 0010 下午 16:00
     */
    public DeductionType saveDeductionType(DeductionType deductionType) {
        return this.deductionTypeRepository.save(deductionType);
    }

    /**
     * 查询所有演绎类型
     *
     * @return java.util.List<DeductionType>
     * @date 2018/9/10 0010 下午 16:01
     */
    public List<DeductionType> findAll() {
        return this.deductionTypeRepository.findAll();
    }

    /**
     * 通过id查询演绎类型
     *
     * @param id 指代词演绎类型id
     * @return DeductionType
     * @date 2018/9/10 0010 下午 16:03
     */
    public DeductionType findById(String id) {
        return this.deductionTypeRepository.getOne(id);
    }

    /**
     * 通过id列表查询所有类型以及类型所包含的规则
     *
     * @param ids 演绎类型id集合
     * @return java.util.List<DeductionType>
     * @date 2018/9/10 0010 下午 16:09
     */
    public List<DeductionType> findAllByIds(List<String> ids) {
        return this.deductionTypeRepository.findAllByIdIn(ids);
    }

    /**
     * 删除演绎类型
     *
     * @param deductionType
     * @return boolean
     * @date 2018/9/10 0010 下午 16:22
     */
    public boolean deleteDeductionType(DeductionType deductionType) {
        boolean flag = false;
        try {
            this.deductionTypeRepository.delete(deductionType);
            flag = true;
        } catch (Exception e) {
            log.error("Delete deductionType error: ", e);
        }
        return flag;
    }

}
