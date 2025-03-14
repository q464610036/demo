package com.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        // 图片地址
        String imgPath = "D://work/test/temp2.png";
        File inputFile = new File(imgPath);
        File imgFile = new File("D://work/test/temp.png");

//        ImageColorInverterUtil.imageColorInverter(inputFile, imgFile);
        BufferedImage colorImage = ImageIO.read(inputFile);
        // 将彩色图像转换为灰度图像
        BufferedImage grayImage = ColorToGrayImageUtil.convertToGray(colorImage);
        // 保存灰度图像
        ColorToGrayImageUtil.saveImage(grayImage, imgFile);
        System.out.println("图像已成功转换为灰度图并保存为 gray_image.png");

        // 语言库位置
        String languageDataPath = "D://work/data/tesseract";
        Tesseract tesseract = new Tesseract();
        // 设置训练库位置
        tesseract.setDatapath(languageDataPath);
        // 中文：chi_sim
        // 英文：eng
        // 中英文：chi_sim+eng
        tesseract.setLanguage("chi_sim");
        String result = null;
        try {
            result = tesseract.doOCR(imgFile);
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}