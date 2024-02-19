package com.poi;

import com.util.FileUtil;
import com.util.StringUtil;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成三方协议，根据excel里面的人员名单自动生成word合同
 * @author 陈孟飞
 * @date 2022/7/8
 */
public class CreateContractWord {
    private static String wordTempFile = "D://test/【三方协议 2022政策】2022新稿职业学校学生岗位实习三方协议（20220825）_code.docx";
    private static String excelFile = "D://test/外部园所启动资料_code.xlsx";
    private static String outPut= "D://test/out/";


    public static void main(String[] args) throws Exception {
        List<Student> list = CreateContractWord.readExcel();
        WordUtils wordUtils = new WordUtils();
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
            String outputFile = outPut+student.getName()+"_【三方协议 2022政策】2022新稿职业学校学生岗位实习三方协议（20220825）.docx";
            FileUtil.createFile(outputFile);
            FileOutputStream out = new FileOutputStream(outputFile);
            xwpfDocument.write(out);
            out.close();
            inputStream.close();
        }
    }

    private static List<Student> readExcel() throws Exception {
        //读取文件
        File file = new File(excelFile);
        if(!file.exists()){
            System.out.println("文件不存在");
        } else {
            return ExcelUtils.readExcel(new FileInputStream(file), Student.class);
        }
        return null;
    }


}
