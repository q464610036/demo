package com.poi;

import com.util.StringUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;

public class WordUtils {

    private String tempLocalPath;
    private XWPFDocument xwpfDocument = null;
    private FileInputStream inputStream = null;
    private OutputStream outputStream = null;

    public void init(String path) throws Exception {
        this.tempLocalPath = path;
        inputStream = new FileInputStream(new File(this.tempLocalPath));
        xwpfDocument = new XWPFDocument(inputStream);
    }

    /**
     * 特殊表格赋值
     *
     * @param text       文本值
     * @param x          行
     * @param y          列
     * @param tableIndex 表格下标
     */
    public void changeTableProvince(String text, int x, int y, int tableIndex, int fontSize) {
        //获取表格对象集合
        List<XWPFTable> tables = xwpfDocument.getTables();
        XWPFTable table = tables.get(tableIndex);
        XWPFTableRow xwpfTable = table.getRow(x);
        XWPFTableCell cell = xwpfTable.getTableCells().get(y);
        XWPFParagraph xwpfParagraph = cell.getParagraphs().get(0);
        XWPFRun run = xwpfParagraph.createRun();
        run.setFontSize(fontSize);
        run.setFontFamily("宋体");
        run.setText(text);
    }

    /**
     * @param params     循环输入表格数据
     * @param x          模板行
     * @param tableIndex 表格下标
     * @return
     * @throws Exception
     */
    public boolean exportTable(List<Map<String, String>> params, int x, int tableIndex) throws Exception {
        List<XWPFTable> tables = xwpfDocument.getTables();
        if (tables.size() < tableIndex) {
            throw new Exception("tableIndex对应的表格不存在");
        }
        //获取表格对象集合
        XWPFTable table = tables.get(tableIndex);
        // 获取模板行
        XWPFTableRow temTableRow = table.getRow(x);
        List<XWPFTableCell> tableCells = temTableRow.getTableCells();
        String key = null;

        for (int i = 0; i < params.size(); i++) {
            // 插入的数据
            Map<String, String> map = params.get(i);
            // 创建新行
//            XWPFTableRow row = table.createRow();
            XWPFTableRow row = table.insertNewTableRow(x + i + 1);
            // 复制属性
            row.getCtRow().setTrPr(temTableRow.getCtRow().getTrPr());
            //复制列对象
            XWPFTableCell targetCell = null;
            System.out.println(tableCells.size());
            for (XWPFTableCell copyCell : tableCells) {
                targetCell = row.addNewTableCell();
                targetCell.getCTTc().setTcPr(copyCell.getCTTc().getTcPr());
                if (copyCell.getParagraphs() != null && copyCell.getParagraphs().size() > 0) {
                    targetCell.getParagraphs().get(0).getCTP().setPPr(copyCell.getParagraphs().get(0).getCTP().getPPr());
                    if (copyCell.getParagraphs().get(0).getRuns() != null
                            && copyCell.getParagraphs().get(0).getRuns().size() > 0) {
                        XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
                        cellR.setBold(copyCell.getParagraphs().get(0).getRuns().get(0).isBold());
                    }
                }
            }
            List<XWPFTableCell> cells = row.getTableCells();
            row.setHeight(temTableRow.getHeight());
            for (int j = 0; j < cells.size(); j++) {
                String text = "";
                XWPFTableCell cell = tableCells.get(j);
                String temKey = cell.getText();
                key = temKey.replace("$", "").replace("{", "").replace("}", "");
                if (map.containsKey(key)) {
                    text = map.get(key);
                }
                XWPFTableCell newCell = row.getTableCells().get(j);
                XWPFParagraph xwpfParagraph = newCell.getParagraphs().get(0);
                XWPFRun run = xwpfParagraph.createRun();
                XWPFRun tmpR = cell.getParagraphs().get(0).getRuns().get(0);
                if (!run.isBold()) {
                    run.setBold(tmpR.isBold());
                }
                run.setItalic(tmpR.isItalic());
                run.setUnderline(tmpR.getUnderline());
                run.setColor(tmpR.getColor());
                run.setTextPosition(tmpR.getTextPosition());
                if (tmpR.getFontSize() != -1) {
                    run.setFontSize(tmpR.getFontSize());
                }
                if (tmpR.getFontFamily() != null) {
                    run.setFontFamily(tmpR.getFontFamily());
                } else {
                    run.setFontFamily("宋体");
                }
                run.setText(text);
            }
        }
        table.removeRow(x);
        return true;
    }

    /**
     * @param picture       2进制图片
     * @param signatureName 替换名称
     * @throws IOException
     */
    public void signaturePicture(String picture, String signatureName) throws IOException, InvalidFormatException {
        String path = "D:\\ssss.png";
        FileInputStream is = outOfPicture(picture, path);

        List<XWPFParagraph> paras = xwpfDocument.getParagraphs();
        for (XWPFParagraph para : paras) {
            //当前段落的属性
            String str = para.getText();
            List<XWPFRun> list = para.getRuns();
            for (XWPFRun run : list) {
                if (signatureName.equals(run.text())) {
                    run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, path, Units.toEMU(100), Units.toEMU(100));
                    run.setText(" ", 0);
                }

            }
        }
    }




    /**
     * 普通文本替换
     *
     * @param textMap
     * @return
     */
    public boolean exportText(Map textMap) {
        replaceText(xwpfDocument, textMap);
        return true;
    }

    /**
     * 替换非表格埋点值
     *
     * @param xwpfDocument
     * @param textMap      需要替换的文本入参
     */
    public void replaceText(XWPFDocument xwpfDocument, Map<String, Text> textMap) {
        Set<String> keySet = textMap.keySet();
        //文本
        Iterator<XWPFParagraph> itPara = xwpfDocument.getParagraphsIterator();
        while (itPara.hasNext()) {
            XWPFParagraph paragraph = itPara.next();
            List<XWPFRun> runs = paragraph.getRuns();
            for (int i = 0; i < runs.size(); i++) {
                String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
                if (oneparaString != null) {
                    for (String key : textMap.keySet()) {
                        oneparaString = oneparaString.replace(key, textMap.get(key).getText());
                    }
                    runs.get(i).setText(oneparaString, 0);
                }
            }
        }
        //表格
        // 替换表格中的指定文字
        Iterator<XWPFTable> itTable = xwpfDocument.getTablesIterator();
        while (itTable.hasNext()) {
            XWPFTable table = (XWPFTable) itTable.next();
            int rcount = table.getNumberOfRows();
            for (int i = 0; i < rcount; i++) {
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    for (String key : textMap.keySet()) {
                        String text = cell.getText();
                        if (!StringUtil.isEmpty(text) && text.contains(key)) {
                            //清楚表格内原来的内容
                            cell.removeParagraph(0);
                            text = text.replace(key, textMap.get(key).getText());
                            cell.setText(text);
                        }
                    }
                }
            }
        }
    }
//    public void replaceText(XWPFDocument xwpfDocument, Map<String, Text> textMap) {
//        List<XWPFParagraph> paras = xwpfDocument.getParagraphs();
//        Set<String> keySet = textMap.keySet();
//        for (XWPFParagraph para : paras) {
//            //当前段落的属性
//            String str = para.getText().trim();
//            List<XWPFRun> list = para.getRuns();
//            for (XWPFRun run : list) {
//                for (String key : keySet) {
//                    if (key.trim().equals(run.text().trim())) {
//                        if (textMap.get(key).getUnderlinePatterns() != null) {
//                            run.setUnderline(textMap.get(key).getUnderlinePatterns());
//                        }
//                        run.setText(textMap.get(key).getText(), 0);
//                    }
//                }
//            }
//        }
//    }

//    // 获取word文档中所有的文字
//    public List<String> getTexts() {
//        List<String> list = new ArrayList<>();
//        List<XWPFParagraph> paras = xwpfDocument.getParagraphs();
//        for (XWPFParagraph para : paras) {
//            if (str != null && !"".equals(str)) {
//                list.add(str);
//            }
//        }
//        return list;
//    }


    private FileInputStream outOfPicture(String picture, String path) throws IOException {
        // 将2进制图片输出
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(picture);
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.flush();
        fos.close();
        return new FileInputStream(path);
    }

    /**
     * 收尾方法
     *
     * @param outDocPath
     * @return
     * @throws IOException
     */
    public boolean generate(String outDocPath) throws IOException {
        outputStream = new FileOutputStream(outDocPath);
        xwpfDocument.write(outputStream);
        this.close(outputStream);
        this.close(inputStream);
        return true;
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

