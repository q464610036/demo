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
 * 生成三方协议，根据excel里面的人员名单自动生成word合同
 * @author 陈孟飞
 * @date 2022/7/8
 */
public class CreateContractWord {
    private static String wordTempFile = "D://test/contract2/3.1校企协议空_code.docx";
    private static String excelFolder = "D://test/contract2/excel/";
    private static String outFolder = "D://test/contract2/out/";


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("是否有重复的幼儿园？Y:N");
        String notRepeat = br.readLine();
        List<Student2> list = CreateContractWord.readExcel();
        WordUtils wordUtils = new WordUtils();
        FileUtil.delFolder(outFolder);
        for (Student2 student : list) {
            System.out.println("index:"+student.getIndex()
                    +",company:"+student.getCompany()
                    +",credit:"+student.getCredit()
                    +",address:"+student.getAddress()
                    +",contractNO:"+student.getContractNO()
                    +",amount:"+student.getAmount()
                    +",capitalAmount:"+student.getCapitalAmount()
                    +",name:"+student.getName()
                    +",phoneNo:"+student.getPhoneNo());
            //生成word
            FileInputStream inputStream = new FileInputStream(new File(wordTempFile));
            XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
            Map<String, Text> textMap = new HashMap<>();
            if (StringUtil.isEmpty(student.getName())) {
                continue;
            }
            textMap.put("${company}", Text.builder().text(student.getCompany()).build());
            textMap.put("${credit}", Text.builder().text(student.getCredit()).build());
            textMap.put("${address}", Text.builder().text(student.getAddress()).build());
            textMap.put("${contractNO}", Text.builder().text(student.getContractNO()).build());
            textMap.put("${amount}", Text.builder().text(student.getAmount()).underlinePatterns(UnderlinePatterns.SINGLE).build());
            textMap.put("${capitalAmount}", Text.builder().text(student.getCapitalAmount()).underlinePatterns(UnderlinePatterns.SINGLE).build());
            textMap.put("${name}", Text.builder().text(student.getName()).build());
            textMap.put("${phoneNO}", Text.builder().text(student.getPhoneNo()).build());
            wordUtils.replaceText(xwpfDocument, textMap);
            String outputFile = outFolder;
            if (notRepeat.equalsIgnoreCase("Y")) {
                outputFile += student.getIndex()+"_"+student.getCompany()+".docx";
            } else {
                outputFile += student.getCompany()+".docx";
            }
            FileUtil.createFile(outputFile);
            FileOutputStream out = new FileOutputStream(outputFile);
            xwpfDocument.write(out);
            out.close();
            inputStream.close();
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
