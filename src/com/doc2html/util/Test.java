package com.doc2html.util;
import java.awt.Graphics2D;  
import java.awt.RenderingHints;  
import java.awt.geom.AffineTransform;  
import java.awt.image.BufferedImage;  
import java.awt.image.ColorModel;  
import java.awt.image.WritableRaster;  
import java.io.*;  
import javax.imageio.*;  
import javax.swing.*; 

public class Test extends JFrame{
    public static void main(String[] args) throws Exception {  
        //String filePath = "C:/testFile/1438243438705.html";
        new Test("http://www.baidu.cn", new File("c:/testFile/1111.jpg"));  
    }  
    
    public Test(String url, File file) throws Exception {  
        JEditorPane editorPane = new JEditorPane();  
        editorPane.setEditable(false);  
        editorPane.setPage(url);  
        JScrollPane jsp = new JScrollPane(editorPane);  
        getContentPane().add(jsp);  
        this.setLocation(0, 0);  
        Thread.sleep(3 * 1000);  
        setSize(1024, 768);  
        pack();  
        // BufferedImage image = new BufferedImage(editorPane.getWidth(),  
        // editorPane.getHeight(), BufferedImage.TYPE_INT_RGB);  
        BufferedImage image = new BufferedImage(editorPane.getWidth(), editorPane.getHeight(), BufferedImage.TYPE_INT_RGB);  
        Graphics2D graphics2D = image.createGraphics();  
        editorPane.paint(graphics2D);  
        BufferedImage image1 = resize(image, 1024, 768);  
        ImageIO.write(image1, "jpg", file);  
        dispose();  
    }  
    
    
    
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {  
        // targetW，targetH分别表示目标长和宽  
        int type = source.getType();  
        BufferedImage target = null;  
        double sx = (double) targetW / source.getWidth();  
        double sy = (double) targetH / source.getHeight();  
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放  
        // 则将下面的if else语句注释即可  
        if (sx > sy) {  
          sx = sy;  
          targetW = (int) (sx * source.getWidth());  
          // } else {  
          // sy = sx;  
          // targetH = (int) (sy * source.getHeight());  
        }  
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade  
          ColorModel cm = source.getColorModel();  
          WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);  
          boolean alphaPremultiplied = cm.isAlphaPremultiplied();  
          target = new BufferedImage(cm, raster, alphaPremultiplied, null);  
        } else  
          target = new BufferedImage(targetW, targetH, type);  
        Graphics2D g = target.createGraphics();  
        // smoother than exlax:  
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);  
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));  
        g.dispose();                            
        return target;  
    }  
}
