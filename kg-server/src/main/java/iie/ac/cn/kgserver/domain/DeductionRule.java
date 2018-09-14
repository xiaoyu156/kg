package iie.ac.cn.kgserver.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description: 关键词演绎规则
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/5 0005 下午 17:01
 * @Version: 1.0
 */
@Entity
@Table(name = "t_deduction_rule", schema = "kg", catalog = "")
@Data
public class DeductionRule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "rule_desc")
    private String ruleDesc;

    @Column(name = "rule_content")
    private String ruleContent;

    @Column(name = "level")
    private Integer level;

    @Column(name = "harm_level")
    private Integer harmLevel;

    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private DeductionType deductionType;
}
