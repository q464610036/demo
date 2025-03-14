package com.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 灰度图Util
 */
public class ImageColorInverterUtil {

    public static File imageColorInverter(File inputFile, File outputFile) {
        try {
            // 输入图片路径（修改为你的图片路径）
            BufferedImage image = ImageIO.read(inputFile);

            // 获取图片尺寸
            int width = image.getWidth();
            int height = image.getHeight();

            // 创建新图片对象
            BufferedImage invertedImage = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_ARGB);

            // 颜色反转处理
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // 获取原始像素的ARGB值
                    int pixel = image.getRGB(x, y);

                    // 分解颜色通道（包含Alpha通道）
                    int alpha = (pixel >> 24) & 0xff;
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;

                    // 颜色反转计算（保持Alpha通道不变）
                    red = 255 - red;
                    green = 255 - green;
                    blue = 255 - blue;

                    // 组合新的像素值
                    int invertedPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;

                    // 设置新像素值
                    invertedImage.setRGB(x, y, invertedPixel);
                }
            }

            // 输出图片路径（修改为你希望的保存路径）
            ImageIO.write(invertedImage, "png", outputFile);
            System.out.println("颜色反转完成！输出文件已保存至: " + outputFile.getAbsolutePath());
            return outputFile;
        } catch (IOException e) {
            System.err.println("发生错误: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
