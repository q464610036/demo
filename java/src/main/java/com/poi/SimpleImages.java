package com.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author 陈孟飞
 * @date 2022/4/11
 */
public class SimpleImages {

    private static SimpleDateFormat dateFormat = null;
    private static String wordFolderStr = "word";
    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

    }

    public static void main(String[] args) throws Exception {
        String folderStr = "D://test";
//        String folderStr = "D://test/新建文件夹";
        //删除word文件夹
        File wordFolder = new File(folderStr+"/"+wordFolderStr);
        if (wordFolder.exists()) {
            wordFolder.delete();
        }
        List<File> files = new ArrayList<>();
        //获得文件夹下所有文件路径
        getChileFile(folderStr, files);
        //按名称分组
        Map<String, Map<String, List<String>>> map = getMap(files);
        for (String name: map.keySet()) {
            System.out.println("正在生成:"+name+".word ......");
            //写入每个人的word
            writeWord(map.get(name), name, folderStr);
        }
        System.out.println("执行完毕");
    }

    /**
     * 按名称创建一个word
     * @param dateMap
     * @param name
     * @param folderStr
     * @throws IOException
     * @throws InvalidFormatException
     */
    private static void writeWord(Map<String, List<String>> dateMap, String name, String folderStr) throws IOException, InvalidFormatException, ParseException {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p = doc.createParagraph();
        XWPFRun r = p.createRun();
        //日期重排序
        Date[] dates = new Date[dateMap.size()];
        int i = 0;
        for(String date : dateMap.keySet()){
            dates[i] = dateFormat.parse(date);
            i++;
        }
        Arrays.sort(dates);
        for(Date d : dates){
            String date = dateFormat.format(d);
            List<String> paths = dateMap.get(date);
            //写入日期
            r.setText(date);
            //换行
            r.addBreak();
            List<String> level1 = new ArrayList<>();
            List<String> level2 = new ArrayList<>();
            //path优先写入非同住人的
            for (String path : paths) {
                File file = new File(path);
                String[] strs = file.getName().split("-");
                String type = strs[4];
                if (type.substring(0, 3).equals("同住人")) {
                    level2.add(path);
                }else {
                    level1.add(path);
                }
            }
            for (String path : level1) {
                getXWPFRun(path, r, 132);
            }
            for (String path : level2) {
                getXWPFRun(path, r, 132);
            }
            //下一页
            r.addBreak(BreakType.PAGE);
        }
        File wordFolder = new File(folderStr+"/"+wordFolderStr);
        if (!wordFolder.exists() && !wordFolder.isDirectory()) {
            wordFolder.mkdirs();
        }
        File wordFile = new File(wordFolder.getPath() + "/" + name+".docx");
        if (!wordFile.exists()) {
            wordFile.delete();
        }
        wordFile.createNewFile();
        FileOutputStream out = new FileOutputStream(wordFile);
        doc.write(out);
        out.close();
        doc.close();
    }

    /**
     * path转为map
     * @param files
     * @return
     */
    private static Map<String, Map<String, List<String>>> getMap(List<File> files) throws Exception {
        Map<String, Map<String, List<String>>> map = new HashMap<>();
        String path = null;
        try{
            for (File file : files) {
                path = file.getPath();
                String fileName = file.getName();
                String[] fileNames = fileName.substring(11, fileName.length()).split("-");
                //日期
                String date = fileName.substring(0, 10);
                if (!isValidDate(date)) {
                    throw new Exception("路径无效：日期错误"+path);
                }
                //姓名
                String name = fileNames[0];
                name = name.replaceAll("\\d+","");
                //获取日期map
                Map<String, List<String>> dateMap = map.get(name);
                if (dateMap == null) {
                    dateMap = new HashMap<>();
                    map.put(name, dateMap);
                }
                List<String> pathList = dateMap.get(date);
                if (pathList == null) {
                    pathList = new ArrayList<>();
                    dateMap.put(date, pathList);
                }
                pathList.add(file.getPath());
            }
        }catch(Exception e){
            throw new Exception("路径无效："+path,e);
        }
        return map;
    }

    public static boolean isValidDate(String s) {
        try {
            dateFormat.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String formatDate(Date d) {
        return dateFormat.format(d);
    }


    /**
     * 文件夹遍历获得文件
     * @param folderStr
     * @param paths
     */
    private static void getChileFile(String folderStr, List<File> paths) throws Exception {
        File file = new File(folderStr);
        if (!file.exists()) {
            throw new Exception("文件夹不存在："+folderStr);
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                if(file1.isDirectory()){
                    if (file1.getName().equals("word")) {
                        return;
                    }
                    getChileFile(file1.getAbsolutePath(), paths);
                }else{
                    paths.add(file1);
                }
            }
        }else{
            paths.add(file);
        }
    }

    /**
     * 写入一张图片
     * @param imgFile
     * @param r
     * @param imgWidth
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    private static void getXWPFRun(String imgFile, XWPFRun r, int imgWidth) throws IOException, InvalidFormatException {
        int format = 0;
        if(imgFile.endsWith(".emf")) format = XWPFDocument.PICTURE_TYPE_EMF;
        else if(imgFile.endsWith(".wmf")) format = XWPFDocument.PICTURE_TYPE_WMF;
        else if(imgFile.endsWith(".pict")) format = XWPFDocument.PICTURE_TYPE_PICT;
        else if(imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg")) format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if(imgFile.endsWith(".png")) format = XWPFDocument.PICTURE_TYPE_PNG;
        else if(imgFile.endsWith(".dib")) format = XWPFDocument.PICTURE_TYPE_DIB;
        else if(imgFile.endsWith(".gif")) format = XWPFDocument.PICTURE_TYPE_GIF;
        else if(imgFile.endsWith(".tiff")) format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if(imgFile.endsWith(".eps")) format = XWPFDocument.PICTURE_TYPE_EPS;
        else if(imgFile.endsWith(".bmp")) format = XWPFDocument.PICTURE_TYPE_BMP;
        else if(imgFile.endsWith(".wpg")) format = XWPFDocument.PICTURE_TYPE_WPG;
        else {
            return;
        }
        BufferedImage img = ImageIO.read(new File(imgFile));
        //获取图片的长和宽
        int width = img.getWidth();
        int height = img.getHeight();
        DecimalFormat df=new DecimalFormat("0.00");
        double scale =Double.parseDouble(df.format((double) imgWidth/width)) ;
        int imgHeight = (int) (scale * height);
//        r.setText(imgFile);
//        r.addBreak();
        r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(imgWidth), Units.toEMU(imgHeight)); // 200x200 pixels
        r.setText(" ");
//        r.addBreak(BreakType.PAGE);
    }
}
