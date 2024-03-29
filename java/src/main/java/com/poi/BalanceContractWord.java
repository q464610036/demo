package com.poi;

import com.util.FileUtil;
import com.util.StringUtil;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 福州市华帜技工学校校企合作结算单
 * @author 陈孟飞
 * @date 2022/7/8
 */
public class BalanceContractWord {
    private static String wordTempFile = "D://test/balance contract/新建 DOCX 文档_code.docx";
    private static String excelFolder = "D://test/balance contract/excel/";
    private static String outFolder = "D://test/balance contract/out/";


    public static void main(String[] args) throws Exception {
        List<Student2> list = BalanceContractWord.readExcel();
        WordUtils wordUtils = new WordUtils();
        FileUtil.delFolder(outFolder);

        //当前word写入记录数
        int a = 0;
        Map<String, Text> textMap = new HashMap<>();
        for (int i=0; i<list.size(); i++) {
            String company = list.get(i).getCompany();
            String index = list.get(i).getIndex();
            System.out.println("index:"+index
                    +",company:"+company);
            //判断i为基数还是偶数，偶数需要创建word，写入word的even区域，奇数需要写入odd区域
            if (a%2 == 0) {
                if (StringUtil.isEmpty(company)) {
                    continue;
                }
                textMap.put("${even_index}", Text.builder().text(index).build());
                textMap.put("${even_company}", Text.builder().text(company).build());
                a++;
            } else {
                textMap.put("${odd_index}", Text.builder().text(index).build());
                textMap.put("${odd_company}", Text.builder().text(company).build());
                a++;
            }
            //map有两条记录或者已经是最后一套记录，写入word
            if (textMap.size() == 4 || i == list.size() - 1) {
                String outputFile = outFolder+"3.13华帜合作结算单_"+a+".docx";
                FileUtil.createFile(outputFile);
                FileOutputStream out = new FileOutputStream(outputFile);
                FileInputStream inputStream = new FileInputStream(new File(wordTempFile));
                XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
                wordUtils.replaceText(xwpfDocument, textMap);
                xwpfDocument.write(out);
                inputStream.close();
                out.close();
                textMap = new HashMap<>();
            }
        }

    }

    private static List<Student2> readExcel() throws Exception {
        //读取文件
        List<Student2> list = new ArrayList<>();
        File folder = new File(excelFolder);
        if(!folder.exists()){
            System.out.println("文件不存在");
        } else {
            for (File file : folder.listFiles()) {
                list.addAll(ExcelUtils.readExcel(new FileInputStream(file), Student2.class));
            }
        }
        return list;
    }


}
