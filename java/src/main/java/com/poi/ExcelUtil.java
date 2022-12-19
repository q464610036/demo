package com.poi;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈孟飞
 * @date 2022/7/8
 */
public class ExcelUtil {
    private static String textFile = "D://test/1.txt";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        List<String> list = new ArrayList<>();
        //读取文件
        BufferedReader bufferedReader = null;
        File file = new File(textFile);
        if(!file.exists()){
            System.out.println("文件不存在");
        } else {
            try {
                //设置文件的编码，否则会乱码
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            } catch (UnsupportedEncodingException | FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String lineText = null;
            try {
                while((lineText = bufferedReader.readLine()) != null){
                    if (lineText != null && !lineText.equals("")){
                        System.out.println("add：" + lineText);
                        list.add(lineText);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        createExcel( list);
    }

    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private static void createExcel(List<String> list) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        /* 开始逐行遍历 */
        int i = 0;
        int j = 0;
        boolean nextISName = false;
        for(String str : list){
            String[] strs = str.split("、");
            if (strs.length > 1 && isNumeric(strs[0])) {
                System.out.println("行数："+i);
                i++;
                Row row = sheet.createRow(i);
                j=0;
                row.createCell(j).setCellValue(strs[1]);
                nextISName = true;
                j++;
            } else {
                Row row = sheet.getRow(i);
                if (nextISName) {
                    row.createCell(j).setCellValue(str);
                    j++;
                } else {
                    String value = null;
                    if (row.getCell(j) != null && row.getCell(j).getStringCellValue() != null) {
                        value = row.getCell(j).getStringCellValue() + "\n"+str;
                    } else {
                        value = str;
                    }
                    row.createCell(j).setCellValue(value);
                }
                nextISName = false;
            }
            System.out.println("列数："+j);
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("D://test/1.xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
