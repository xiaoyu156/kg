package iie.ac.cn.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 笛卡儿积工具类
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/7 0007 下午 15:04
 * @Version: 1.0
 */
public class CartesianProduct {

    /**
     * 求几个列表的笛卡儿积，无论有几个列表，最后求得都是两个列表的笛卡儿积
     *
     * @param listList 二维数组
     * @return java.util.List<java.lang.String>
     * @date 2018/9/10 0010 上午 9:20
     */
    public static List<String> cartesionProduct(List<List<String>> listList) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < listList.size(); i++) {
            List<String> tempList = listList.get(i);
            if (i == 0) {
                result.addAll(tempList);
                continue;
            }
            result = selfCopy(result, tempList);
        }
        return result;
    }

    /**
     * 实现组合
     *
     * @param result 每一次笛卡儿积后的结果
     * @param temp 下一次要组合的集合
     * @return java.util.ArrayList<java.lang.String>
     * @date 2018/9/10 0010 上午 9:27
     */
    private static ArrayList<String> selfCopy(List<String> result, List<String> temp) {
        //初始容量
        int initialCapacity = 10000;
        ArrayList<String> last = new ArrayList<>(initialCapacity);
        for (String before : result) {
            for (String after : temp) {
                last.add(before + after);
            }
        }
        result.clear();
        return last;
    }
}
