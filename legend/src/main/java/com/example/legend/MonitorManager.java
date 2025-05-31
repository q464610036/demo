package com.example.legend;


import com.example.legend.common.util.FileUtil;
import com.example.legend.common.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class MonitorManager {

    /**
     * 生成截屏图片
     * @param path
     * @param historyPath
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws Exception
     */
    public static void createImage(String path, String historyPath, int x1, int y1, int x2, int y2) throws Exception {
        //重命名历史文件
        File file = new File(path);
        if (file.exists()) {
            FileUtil.copyFile(file, new File(historyPath));
            FileUtil.delFile(path);
        }
        //截屏
        ImageUtil.createScreenCapture(path, x1, y1,x2-x1, y2-y1);
    }
    /**
     * 对比文件
     * @throws Exception
     */
    public static Double contrast(String path, String historyPath) throws Exception {
        File file = new File(path);
        File historyFile = new File(historyPath);
        Double similarity = 0D;
        //对比文件
        if (file.exists() && historyFile.exists()) {
            // 读取图片
            BufferedImage image1 = ImageIO.read(file);
            BufferedImage image2 = ImageIO.read(historyFile);
            // 转换成灰度图
            BufferedImage grayImage1 = ImageUtil.toGray(image1);
            BufferedImage grayImage2 = ImageUtil.toGray(image2);
            // 计算相似度
            similarity = ImageUtil.getSimilarity(grayImage1, grayImage2);
        }
        return similarity;
    }
}
