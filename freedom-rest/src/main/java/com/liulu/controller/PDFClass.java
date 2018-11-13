package com.liulu.controller;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

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
            reader = new PdfReader("C:\\Users\\lenovo\\Desktop\\征信确认函2.pdf");
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
            String imgpath="C:\\Users\\lenovo\\Desktop\\front.png";
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

    public static void main(String[] args) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userName", "12312321");
        data.put("idNum", "小帅哥");
        data.put("year", "男");
        data.put("month", "21");
        data.put("day", "21");
        createPDF("C:\\Users\\lenovo\\Desktop\\front.png",data);
    }


}
