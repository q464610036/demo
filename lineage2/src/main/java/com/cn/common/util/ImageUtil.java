package com.cn.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static void createScreenCapture(String path, int x, int y, int width, int height) throws AWTException, IOException {
        // 创建一个Robot对象
        Robot robot = new Robot();
        // 获取屏幕的大小
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // 创建一个Rectangle对象，用于指定截屏的区域
        Rectangle rectangle = new Rectangle(x, y, width, height);
        // 捕获屏幕上的内容
        BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
        // 将捕获的内容保存到文件
        ImageIO.write(bufferedImage, "jpg", new File(path));
    }

    /**
     * 转换成灰度图
     * @param image
     * @return
     */
    public static BufferedImage toGray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }
        return grayImage;

    }

    /**
     * 计算相似度
     * @param image1
     * @param image2
     * @return
     */
    public static double getSimilarity(BufferedImage image1, BufferedImage image2) {
        int width = image1.getWidth();
        int height = image1.getHeight();
        int count = 0;
        double similarity = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb1 = image1.getRGB(i, j);
                int rgb2 = image2.getRGB(i, j);
                if (rgb1 == rgb2) {
                    count++;
                }
            }
        }
        similarity = (double) count / (width * height);
        return similarity;
    }
}
