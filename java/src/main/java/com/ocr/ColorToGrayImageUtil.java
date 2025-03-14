package com.ocr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 颜色反转Util
 */
public class ColorToGrayImageUtil {

    // 转换彩色图像为灰度图像
    public static BufferedImage convertToGray(BufferedImage colorImage) {
        int width = colorImage.getWidth();
        int height = colorImage.getHeight();

        // 创建新的灰度图像
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // 遍历每个像素并进行灰度转换
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // 获取原图像的RGB值
                int rgb = colorImage.getRGB(x, y);

                // 获取RGB各个通道的值
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // 使用灰度转换公式计算灰度值
                int gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);

                // 创建新的灰度像素值
                int grayRGB = (gray << 16) | (gray << 8) | gray;  // 将灰度值填充到RGB的每个通道

                // 设置灰度图像的对应像素值
                grayImage.setRGB(x, y, grayRGB);
            }
        }

        return grayImage;
    }

    // 保存灰度图像
    public static void saveImage(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "PNG", file);
    }

    public static void main(String[] args) {
        try {
            // 加载彩色图像
            File inputFile = new File("color_image.jpg");
            BufferedImage colorImage = ImageIO.read(inputFile);

            // 将彩色图像转换为灰度图像
            BufferedImage grayImage = convertToGray(colorImage);

            // 保存灰度图像
            saveImage(grayImage, new File("gray_image.png"));

            System.out.println("图像已成功转换为灰度图并保存为 gray_image.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
