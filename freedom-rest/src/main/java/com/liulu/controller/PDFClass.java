package com.liulu.controller;


import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * @Description:
 * @Copyright (c) by HomeFax.
 * @All right reserved.
 * @Create Date: 2018/11/13 18:26
 * @Create Author: nevermore
 * @File Name: PDFClass
 * @Last version: 3.8.0
 */
public class PDFClass {

    /**
     * 根据模板生成pdf
     * @param data Map(String,Object)
     * @return
     */
    public static boolean createPDF(String path,Map<String, Object> data) {
        PdfReader reader = null;
        AcroFields s = null;
        PdfStamper ps = null;
        ByteArrayOutputStream bos = null;
        try {
            reader = new PdfReader("C:\\Users\\VULCAN\\Desktop\\征信确认函2.pdf");
            bos = new ByteArrayOutputStream();
            ps = new PdfStamper(reader, bos);
            s = ps.getAcroFields();

            /**
             * 使用中文字体 使用 AcroFields填充值的不需要在程序中设置字体，在模板文件中设置字体为中文字体 Adobe 宋体 std L
             */
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            /**
             * 设置编码格式
             */
            s.addSubstitutionFont(bfChinese);


            // 遍历data 给pdf表单表格赋值
            for (String key : data.keySet()) {
                s.setField(key,data.get(key).toString());
            }

            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            ps.setFormFlattening(true);
            /**
             * 添加图片
             */
            String imgpath="C:\\Users\\VULCAN\\Desktop\\JTPC4~G{HQP}WSCPK345IQK.png";
            int pageNo = s.getFieldPositions("image").get(0).page;
            Rectangle signRect = s.getFieldPositions("image").get(0).position;
            float x = signRect.getLeft();
            float y = signRect.getBottom();
            // 读图片
            Image image = Image.getInstance(imgpath);
            // 获取操作的页面
            PdfContentByte under = ps.getOverContent(pageNo);
            // 根据域的大小缩放图片
            image.scaleToFit(signRect.getWidth(), signRect.getHeight());
            // 添加图片
            image.setAbsolutePosition(x, y);
            under.addImage(image);
            @SuppressWarnings("resource")
            FileOutputStream fos = new FileOutputStream("d:\\shouju_fb.pdf");
            fos.write(bos.toByteArray());
            return true;
        } catch (IOException | DocumentException e) {
            System.out.println("读取文件异常");
            e.printStackTrace();
            return false;
        }finally {
            try {
                bos.close();
                ps.close();
                reader.close();
            } catch (IOException | DocumentException e) {
                System.out.println("关闭流异常");
                e.printStackTrace();
            }
        }
    }

/*    public static void main(String[] args) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userName", "12312321");
        data.put("idNum", "小帅哥");
        data.put("year", "男");
        data.put("month", "21");
        data.put("day", "21");
        createPDF("C:\\Users\\VULCAN\\Desktop\\JTPC4~G{HQP}WSCPK345IQK.png",data);
    }*/

    /*public static void main(String[] args) throws Exception {
        // 模板文件路径
        String templatePath = "C:\\Users\\VULCAN\\Desktop\\征信确认函2.pdf";
        // 生成的文件路径
        String targetPath = "target.pdf";
        // 书签名
        String fieldName = "image";
        // 图片路径
        String imagePath = "C:\\Users\\VULCAN\\Desktop\\JTPC4~G{HQP}WSCPK345IQK.png";

        // 读取模板文件
        InputStream input = new FileInputStream(new File(templatePath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
        // 提取pdf中的表单
        AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userName", "刘璐");
        data.put("idNum", "340827199302235810");
        data.put("year", "2018");
        data.put("month", "11");
        data.put("day", "13");
        for (String key : data.keySet()) {
            form.setField(key,data.get(key).toString());
        }

        // 通过域名获取所在页和坐标，左下角为起点
        int pageNo = form.getFieldPositions(fieldName).get(0).page;
        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();

        // 读图片
        Image image = Image.getInstance(imagePath);
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(pageNo);
        // 根据域的大小缩放图片
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // 添加图片
        image.setAbsolutePosition(x, y);
        under.addImage(image);

        stamper.close();
        reader.close();
    }*/

    public static void main(String[] args) throws Exception{

        String filePath = "target.pdf";
        Document document = new Document();
        try {
            document.setFile(filePath);
        } catch (Exception ex) {
        }

        // save page caputres to file.

        float scale = 2f;

        float rotation = 0f;

        // Paint each pages content to an image and write the image to file

        for (int i = 0; i < document.getNumberOfPages(); i++) {

            BufferedImage image = (BufferedImage)document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                    Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;

            // capture the page image to file

            try {

                System.out.println("/t capturing page " + i);

                File file = new File("target1.png");

                ImageIO.write(rendImage, "png", file);

            } catch (IOException e) {

                e.printStackTrace();

            }

            image.flush();

        }

        // clean up resources

        document.dispose();

    }




}
