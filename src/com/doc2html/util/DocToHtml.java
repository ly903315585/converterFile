package com.doc2html.util;

import java.io.File;
import java.net.ConnectException;
import java.util.Date;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 将Word文档转换成html字符串的工具类
 * @author MZULE
 * 
 */
public class DocToHtml {
    public static void main(String[] args) throws Exception {
        String docFile = "C:/testFile/商城模块详细设计.docx";
        String ipAddress = "192.168.13.164";
        String filePath = convert(docFile,ipAddress);
        System.out.println(filePath); 
    }

    /**
     * 将word文档转换成html文档
     * @param docFile 需要转换的word文档
     * @param filepath 转换之后html的存放路径
     * @return 转换之后的html文件
     */
    public static String convert(String docFile,String ipAddress) {
        String dirPath =  "C:/testFile";
        String filePath = dirPath + "/" + new Date().getTime() + ".html";
        File htmlFile = new File(filePath);// 创建保存html的文件
        OpenOfficeConnection con = new SocketOpenOfficeConnection(ipAddress,8100);// 创建Openoffice连接
        try {
            con.connect();// 连接
        } catch (ConnectException e) {
            System.out.println("获取OpenOffice连接失败...");
            e.printStackTrace();
        }
        
        DocumentConverter converter = new OpenOfficeDocumentConverter(con);// 创建转换器
        converter.convert(new File(docFile), htmlFile);// 转换文档问html
        con.disconnect();// 关闭openoffice连接
        return filePath;
    }
}
