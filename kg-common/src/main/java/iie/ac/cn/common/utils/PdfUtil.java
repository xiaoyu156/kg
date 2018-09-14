package iie.ac.cn.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: pdf 工具类
 * @Author: wangxiaoyu1994@iie.ac.cn
 * @CreateDate: 2018/9/12 0012 上午 9:50
 * @Version: 1.0
 */
@Slf4j
public class PdfUtil {
    public static List<String> readPdfByLine(String fileName) {
        PDDocument pdDocument;
        List<String> result = new ArrayList<>();
        try {
            //加载一个pdf对象
            pdDocument = PDDocument.load(new File(fileName));
            //创建一个文本剥离对象
            PDFTextStripper textStripper = new PDFTextStripper();
            //获取文本
            String content = textStripper.getText(pdDocument);
            //通过换行符分割成list
            result = Arrays.asList(StringUtils.strip(content).trim().split("\r"));
            System.out.println(result.size());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("pdf读取失败", e);
        }
        return result;
    }

}
