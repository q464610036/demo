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
}
