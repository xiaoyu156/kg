package iie.ac.cn.kgserver.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Description: jpa配置类
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/6 0006 上午 9:26
 * @Version: 1.0
 */
@EnableJpaRepositories(basePackages = "ac.iie.cn.calculation.repository")
@EntityScan(basePackages = "ac.iie.cn.calculation.model.domain")
public class JpaConfig {

}
