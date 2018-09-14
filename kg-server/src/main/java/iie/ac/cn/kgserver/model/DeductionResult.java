package iie.ac.cn.kgserver.model;

import iie.ac.cn.kgserver.domain.DeductionType;
import lombok.Data;

import java.util.List;

/**
 * @Description: 演绎结果
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/10 0010 上午 9:53
 * @Version: 1.0
 */
@Data
public class DeductionResult {
    private int keywordNum;
    private List<DeductionType> deductionTypes;
}
