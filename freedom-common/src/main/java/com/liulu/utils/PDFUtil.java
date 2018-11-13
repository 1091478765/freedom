package com.liulu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.StringUtils;
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
 * Created by 刘璐 on 2018/11/13.
 */
public class PDFUtil {

    /**
     * 根据pdf模板生成对应pdf文件
     * @param sourceUrl
     *              模板文件url
     * @param targetUrl
     *              生成路径，如果和模板文件一样，则覆盖模板文件
     * @param imglabel
     *              需要填充图片的标签名称，该方法只支持一张图片填充
     * @param imgUrl
     *              填充图片url地址
     * @param data
     * @return
     */
    public static String processPdf(String sourceUrl ,String targetUrl , String imglabel , String imgUrl,Map<String, Object> data ) {

        // 读取模板文件
        PdfStamper stamper = null;
        PdfReader reader = null;
        try {
            InputStream input = new FileInputStream(new File(sourceUrl));
            reader = new PdfReader(input);
            stamper = new PdfStamper(reader, new FileOutputStream(targetUrl));
            // 提取pdf中的表单
            AcroFields form = stamper.getAcroFields();
            form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
            if (data != null && data.size() >0){
                for (String key : data.keySet()) {
                    form.setField(key,data.get(key).toString());
                }
            }

            // 通过域名获取所在页和坐标，左下角为起点
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(imglabel)){
                int pageNo = form.getFieldPositions(imglabel).get(0).page;
                Rectangle signRect = form.getFieldPositions(imglabel).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                // 读图片
                Image image = Image.getInstance(imgUrl);
                // 获取操作的页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                // 根据域的大小缩放图片
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                // 添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                stamper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            reader.close();
        }
        return targetUrl;
    }

    /**
     *
     * @param sourceUrl
     *              模板文件位置
     * @param targetUrl
     *              生成文件位置
     * @param imgs
     *              填充图片
     * @param data
     *              文本数据
     * @return
     */
    public static String processPdfByImgs(String sourceUrl ,String targetUrl ,Map<String,String> imgs,Map<String, Object> data ) {

        // 读取模板文件
        PdfStamper stamper = null;
        PdfReader reader = null;
        try {
            InputStream input = new FileInputStream(new File(sourceUrl));
            reader = new PdfReader(input);
            stamper = new PdfStamper(reader, new FileOutputStream(targetUrl));
            // 提取pdf中的表单
            AcroFields form = stamper.getAcroFields();
            form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
            if (data != null && data.size() >0){
                for (String key : data.keySet()) {
                    form.setField(key,data.get(key).toString());
                }
            }

            // 通过域名获取所在页和坐标，左下角为起点
            if (imgs != null && imgs.size() >0){
                for (String key : imgs.keySet()) {
                    form.setField(key,imgs.get(key).toString());
                    String imglabel = key;
                    String imgUrl = imgs.get(key);
                    int pageNo = form.getFieldPositions(imglabel).get(0).page;
                    Rectangle signRect = form.getFieldPositions(imglabel).get(0).position;
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    // 读图片
                    Image image = Image.getInstance(imgUrl);
                    // 获取操作的页面
                    PdfContentByte under = stamper.getOverContent(pageNo);
                    // 根据域的大小缩放图片
                    image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                    // 添加图片
                    image.setAbsolutePosition(x, y);
                    under.addImage(image);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                stamper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            reader.close();
        }
        return targetUrl;
    }

    public static void main(String[] args) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userName", "刘璐");
        data.put("idNum", "340827199302235810");
        data.put("year", "2018");
        data.put("month", "11");
        //data.put("day", "13");
        /*processPdf("C:\\Users\\VULCAN\\Desktop\\征信确认函2.pdf"
                        ,"C:\\Users\\VULCAN\\Desktop\\征信确认函5.pdf"
                        , "image"
                ,"C:\\Users\\VULCAN\\Desktop\\JTPC4~G{HQP}WSCPK345IQK.png",data);*/

        Map<String,String> map = new HashMap<>();
        map.put("image","C:\\Users\\VULCAN\\Desktop\\JTPC4~G{HQP}WSCPK345IQK.png");
        map.put("day","C:\\Users\\VULCAN\\Desktop\\JTPC4~G{HQP}WSCPK345IQK.png");
        processPdfByImgs("C:\\Users\\VULCAN\\Desktop\\征信确认函2.pdf"
                ,"C:\\Users\\VULCAN\\Desktop\\征信确认函5.pdf"
                ,map ,data);

    }
}
