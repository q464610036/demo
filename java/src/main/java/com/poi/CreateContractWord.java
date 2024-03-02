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
    private static String wordTempFile = "D://test/contract/3.1三方合同空_code.docx";
    private static String excelFolder = "D://test/contract/excel/";
    private static String outFolder = "D://test/contract/out/";


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("是否有重复的幼儿园？Y:N");
        String notRepeat = br.readLine();
        List<Student> list = CreateContractWord.readExcel();
        WordUtils wordUtils = new WordUtils();
        FileUtil.delFolder(outFolder);
        for (Student student : list) {
            System.out.println("index:"+student.getIndex()
                    +",contractNO:"+student.getContractNO()
                    +",company:"+student.getCompany()
                    +",address:"+student.getAddress()
                    +",name:"+student.getName()
                    +",phoneNo:"+student.getPhoneNo()
                    +",dutyParagraph:"+student.getDutyParagraph());
            //生成word
            FileInputStream inputStream = new FileInputStream(new File(wordTempFile));
            XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
            Map<String, Text> textMap = new HashMap<>();
            if (StringUtil.isEmpty(student.getName())) {
                continue;
            }
            textMap.put("${company}", Text.builder().text(student.getCompany()).build());
            textMap.put("${address}", Text.builder().text(student.getAddress()).build());
            textMap.put("${name}", Text.builder().text(student.getName()).build());
            textMap.put("${phoneNO}", Text.builder().text(student.getPhoneNo()).build());
            if(student.getName().length() == 2){
                student.setName(student.getName()+"  ");
            }
            textMap.put("${name2}", Text.builder().text(student.getName()).underlinePatterns(UnderlinePatterns.SINGLE).build());
            textMap.put("${phoneNO2}", Text.builder().text(student.getPhoneNo()).underlinePatterns(UnderlinePatterns.SINGLE).build());
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

    private static List<Student> readExcel() throws Exception {
        //读取文件
        List<Student> list = new ArrayList<>();
        File folder = new File(excelFolder);
        if(!folder.exists()){
            System.out.println("文件不存在");
        } else {
            for (File file : folder.listFiles()) {
                list.addAll(ExcelUtils.readExcel(new FileInputStream(file), Student.class));
            }
        }
        return list;
    }


}
