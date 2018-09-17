package iie.ac.cn.kgserver.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 字根和变异字根存储结构
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/6 0006 上午 10:59
 * @Version: 1.0
 */
@Data
@Slf4j
public class RootMap {
    /**
     * 原始词
     */
    private String originalWord;
    /**
     * 演绎级别
     */
    private Integer deductLevel;
    /**
     * 姓字根数
     */
    private Integer familyNum;

    /**
     * 名字根数
     */
    private Integer lastNum;

    /**
     * 变异字根存储
     */
    private Map<String, Object> roots=new HashMap<>();

    /**
     * 姓集合
     */
    private List<String> familyList;

    /**
     * 名集合
     */
    private List<String> lastList;

    public RootMap() {

    }

    public RootMap(String originalWord, Integer deductLevel, Map<String, Object> roots, List<String> familyList, List<String> lastList) {
        this.originalWord = originalWord;
        this.deductLevel = deductLevel;
        this.roots = roots;
        this.familyList = familyList;
        this.lastList = lastList;
        this.familyNum = familyList.size();
        this.lastNum = lastList.size();
    }

    /**
     * 通过key获取变异字根集合
     *
     * @param key 字根集合key
     * @return java.util.List<java.lang.String>
     * @date 2018/9/6 0006 下午 14:54
     */
    @SuppressWarnings("unchecked")
    public List<String> getRootByKey(String key) {
        return (List<String>) this.roots.get(key);
    }

    /**
     * 添加字根集合
     *
     * @param key   字根key
     * @param object 字根集合
     * @return boolean
     * @date 2018/9/6 0006 下午 14:57
     */
    public boolean putRoot(String key, Object object) {
        try {
            this.roots.put(key, object);
        } catch (Exception e) {
            log.error("添加root[" + key + "-->" + object + "]错误", e);
            return false;
        }
        return true;
    }

}
