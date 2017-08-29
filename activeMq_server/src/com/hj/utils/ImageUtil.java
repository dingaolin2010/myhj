package com.hj.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageUtil {

    /**
     * 时间:2015-7-29,作者: dys
     * 功能：{修改文件名称}
     * 逻辑描述：{这里写出方法的业务和逻辑}
     * 参数：{file原文件全路径名，toFile修改后的文件全路径名}
     * 返回结果：{返回结果说明}
     * 
     * 修改历史
     * 时间:2015-7-29,作者:{姓名}
     * 修改内容：
     * {这里描述下本次修改哪些内容}
     */
    public static void renameFile(String file, String toFile) {
        File toBeRenamed = new File(file);
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            System.out.println("File does not exist: " + file);
            return;
        }
        File newFile = new File(toFile);
        //修改文件名
        if (toBeRenamed.renameTo(newFile)) {
            System.out.println("File has been renamed.");
        } else {
            System.out.println("Error renmaing file");
        }
    }

    /**
      * 时间:2015-7-10,作者: XXX
      * 功能：{图片上传}
      * 逻辑描述：{这里写出方法的业务和逻辑}
      * 参数：{pic:file文件流,fileName:上传时的文件名,path:上传路径,realName:文件上传后的真实命名}
      * 返回结果：{返回结果说明}
      * 
      * 修改历史
      * 时间:2015-7-10,作者:{姓名}
      * 修改内容：
      * {这里描述下本次修改哪些内容}
      */
    public static String picupload(File pic, String fileName, String path, String realName) {
        String ret = null;
        String[] str = { ".jpg", ".jpeg", ".bmp", ".gif" };
        if (pic == null || pic.length() > 4194304) {
            ret = "文件过大";
        }
        for (String s : str) {
            if (fileName.endsWith(s)) {
                //realName=realName + fileName.substring(_picFileName.indexOf("."));
                File saveFile = new File(new File(path), realName);
                // 判断父目录是否存在  
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                try {
                    // 执行文件上传  
                    // FileUtils 类名 org.apache.commons.io.FileUtils;  
                    // 是commons-io包中的，commons-fileupload 必须依赖  
                    // commons-io包实现文件上次，实际上就是将一个文件转换成流文件进行读写  
                    FileUtils.copyFile(pic, saveFile);
                    ImageUtil.makeSmallImage(saveFile, saveFile.getPath(), 400);
                    ret = "上传成功";
                } catch (Exception e) {
                    ret = "上传失败";
                }
            }
        }
        return ret;
    }

    /**
      * 时间:2015-7-9,作者: dys
      * 功能：{删除图片}
      * 逻辑描述：{这里写出方法的业务和逻辑}
      * 参数：{path:图片在项目中的全路径名}
      * 返回结果：{返回结果说明}
      * 
      * 修改历史
      * 时间:2015-7-9,作者:{姓名}
      * 修改内容：
      * {这里描述下本次修改哪些内容}
      */
    public static boolean delpic(String path) {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
      * 时间:2015-7-10,作者: dys
      * 功能：{图片截取}
      * 逻辑描述：{这里写出方法的业务和逻辑}
      * 参数：{图片在项目中的全路径名}
      * 返回结果：{返回结果说明}
      * 
      * 修改历史
      * 时间:2015-7-10,作者:{姓名}
      * 修改内容：
      * {这里描述下本次修改哪些内容}
      */
    public static Boolean savepicCut(String inputPath, String outputPath, String pixString, String imageName, Integer x, Integer y, Integer width, Integer height) {
        String name = inputPath + imageName;
        try {
            FileInputStream is = null;
            ImageInputStream iis = null;
            try {
                // 读取图片文件  
                is = new FileInputStream(name);
                /* 
                 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。 
                 * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。 
                 */
                Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
                ImageReader reader = it.next();
                // 获取图片流  
                iis = ImageIO.createImageInputStream(is);
                /* 
                 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。 
                 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。 
                 */
                reader.setInput(iis, true);
                /* 
                 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O 
                 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的 
                 * getDefaultReadParam 方法中返回 ImageReadParam 的实例。 
                 */
                ImageReadParam param = reader.getDefaultReadParam();
                /* 
                 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象 
                 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。 
                 */
                Rectangle rect = new Rectangle(x, y, width, height);
                // 提供一个 BufferedImage，将其用作解码像素数据的目标。  
                param.setSourceRegion(rect);
                /* 
                 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的 
                 * BufferedImage 返回。 
                 */
                BufferedImage bi = reader.read(0, param);
                // 保存新图片  
                Boolean bool = ImageIO.write(bi, "jpg", new File(outputPath + pixString + imageName));
                return bool;
            } finally {
                if (is != null)
                    is.close();
                if (iis != null)
                    iis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 生成缩略图
     * @param srcImageFile 源图片文件的File实例      File file = new File("文件名");
     * @param dstImageFileName 待生成的缩略图片完整路径(生成的格式为：image/jpeg);
     * @param scalMaxSize 目标缩略图的最大宽度/高度，宽度与高度将按比例缩写;
     * @throws Exception
     */
    public static void makeSmallImage(File srcImageFile, String dstImageFileName, int scalMaxSize) throws Exception {
        FileOutputStream fileOutputStream = null;
        JPEGImageEncoder encoder = null;
        BufferedImage tagImage = null;
        Image srcImage = null;
        if (scalMaxSize < 10) {
            scalMaxSize = 300;
        }
        try {
            srcImage = ImageIO.read(srcImageFile);
            int srcWidth = srcImage.getWidth(null);//原图片宽度
            int srcHeight = srcImage.getHeight(null);//原图片高度
            //int dstMaxSize = scalMaxSize;//目标缩略图的最大宽度/高度，宽度与高度将按比例缩写
            int dstMaxSize = 225;//目标缩略图的最大宽度/高度，宽度与高度将按比例缩写
            int dstMaxHeight = 300;
            int dstWidth = srcWidth;//缩略图宽度
            int dstHeight = srcHeight;//缩略图高度
            float scale = 0;
            //计算缩略图的宽和高
            if (srcWidth > dstMaxSize) {
                dstWidth = dstMaxSize;
                scale = (float) srcWidth / (float) dstMaxSize;
                dstHeight = Math.round((float) srcHeight / scale);
                if (dstHeight < dstMaxHeight) {
                    dstHeight = dstMaxHeight;
                }
            }
            srcHeight = dstHeight;
            if (srcHeight > dstMaxHeight) {
                dstHeight = dstMaxHeight;
                scale = (float) srcHeight / (float) dstMaxHeight;
                dstWidth = Math.round((float) dstWidth / scale);
                if (dstWidth < 225) {
                    dstWidth = 225;
                }
            }
            //生成缩略图
            tagImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);

            Boolean bool = tagImage.getGraphics().drawImage(srcImage, 0, 0, dstWidth, dstHeight, null);
            fileOutputStream = new FileOutputStream(dstImageFileName);
            encoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
            encoder.encode(tagImage);
            fileOutputStream.close();
            fileOutputStream = null;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
                fileOutputStream = null;
            }
            encoder = null;
            tagImage = null;
            srcImage = null;
            System.gc();
        }
    }

    /**
     * 缩放图像（按高度和宽度缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param height 缩放后的高度
     * @param width 缩放后的宽度
     * @param bb 比例不对时是否需要补白：true为补白; false为不补白;
     */
    public final static void scale2(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * 旋转图片为指定角度 
     *  
     * @param bufferedimage 原图像 
     * @param degree 
     *            旋转角度 
     * @return 
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
        int w = bufferedimage.getWidth();// 得到图片宽度。  
        int h = bufferedimage.getHeight();// 得到图片高度。  
        int type = bufferedimage.getColorModel().getTransparency();// 得到图片透明度。  
        BufferedImage img;// 空的图片。
        Graphics2D graphics2d;// 空的画笔。
        graphics2d = (img = new BufferedImage(h, w, type)).createGraphics();
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);// 旋转，degree是整型，度数，比如垂直90度。
        graphics2d.drawImage(bufferedimage, 0, 0, null);// 从bufferedimagecopy图片至img，0,0是img的坐标。  
        graphics2d.dispose();
        return img;// 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。  
    }

    /**
     * 
     * @param bufferedimage 原图像 
     * @param degree 旋转角度 
     * @param withd
     * @param height
     * @param translateX
     * @param translateY
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree, boolean reversion, int translateX, int translateY) {
        int w = bufferedimage.getWidth();// 得到图片宽度。  
        int h = bufferedimage.getHeight();// 得到图片高度。  
        int type = bufferedimage.getColorModel().getTransparency();// 得到图片透明度。  
        BufferedImage img = null;
        if (reversion) {
            img = new BufferedImage(h, w, type);// 空的图片，且与原图width height互换 。
        } else {
            img = new BufferedImage(w, h, type);// 空的图片。
        }
        Graphics2D graphics2d = img.createGraphics();// 空的画笔。
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree));// 旋转，degree是整型，度数，比如垂直90度。
        graphics2d.translate(translateX, translateY);
        graphics2d.drawImage(bufferedimage, 0, 0, null);// 从bufferedimage copy图片至img，0,0是img的坐标。  
        graphics2d.dispose();
        return img;// 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。  
    }
}
