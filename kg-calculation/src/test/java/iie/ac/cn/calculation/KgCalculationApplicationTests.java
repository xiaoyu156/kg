package iie.ac.cn.calculation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KgCalculationApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void setTest(){
        //HashSet 去重
        Set<String> stringSet=new HashSet<>();
        stringSet.add("a");
        stringSet.add("b");
        stringSet.add("a");

        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
