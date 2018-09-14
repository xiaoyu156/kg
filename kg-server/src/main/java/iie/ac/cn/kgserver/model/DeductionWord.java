package iie.ac.cn.kgserver.model;

import lombok.Data;


@Data
public class DeductionWord {
    private String word;
    private double harmScore;
    private Object deduction_route;
}
