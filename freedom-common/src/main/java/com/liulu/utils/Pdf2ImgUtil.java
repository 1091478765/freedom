package com.liulu.utils;

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
 * Created by 刘璐 on 2018/11/13.
 */
public class Pdf2ImgUtil {

    /**
     *
     * @param sourceUrl
     * @param targetUrl
     * @throws Exception
     */
    public static String pdf2Img(String sourceUrl, String targetUrl) throws Exception{
        Document document = new Document();
        try {
            document.setFile(sourceUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        float scale = 2f;
        float rotation = 0f;
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                    Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                System.out.println("/t capturing page " + i);
                File file = new File(targetUrl);
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
        return targetUrl;
    }

    public static void main(String[] args) throws Exception{
        pdf2Img("C:\\Users\\VULCAN\\Desktop\\征信确认函5.pdf","C:\\Users\\VULCAN\\Desktop\\征信确认函5.png");
    }
}
