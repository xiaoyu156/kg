package iie.ac.cn.kgserver.domain;

import iie.ac.cn.kgserver.model.DeductionWord;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 关键词演绎类型
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/5 0005 下午 17:01
 * @Version: 1.0
 */
@Entity
@Table(name = "t_deduction_type", schema = "kg", catalog = "")
@Data
public class DeductionType {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "root_list")
    private String rootList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private Set<DeductionRule> deductionRuleSet = new HashSet<>();

    @Transient
    private List<String> keywords = new ArrayList<>();
}
