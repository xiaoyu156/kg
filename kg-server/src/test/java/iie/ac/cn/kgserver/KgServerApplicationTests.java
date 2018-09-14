package iie.ac.cn.kgserver;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KgServerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        File file=new File("C:\\Users\\Administrator\\Downloads\\Fish-v324-0712\\Fish-v324-0712\\kpdf\\EXCEL格式的百家姓列表.pdf");
        try {
            List<String> strings = FileUtils.readLines(file, "GBK");
            for (String s : strings) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
