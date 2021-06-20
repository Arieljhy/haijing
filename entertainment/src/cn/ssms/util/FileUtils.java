/*     */ package cn.ssms.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class FileUtils
/*     */ {
/*     */   public static String getFileType(String fileName) {
/*  20 */     String str = fileName;
/*  21 */     str = str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase();
/*     */     
/*  23 */     return str;
/*     */   }
/*     */   
/*     */   public static String getFileNamePrefix(String fileName) {
/*  27 */     String str = fileName;
/*  28 */     str = str.substring(0, str.lastIndexOf("."));
/*  29 */     return str;
/*     */   }
/*     */   
/*     */   public static boolean isFileName(String str) {
/*  33 */     if (StringUtils.isNotEmpty(str) && str.length() >= 5 && (".".equals(String.valueOf(str.charAt(str.length() - 4))) || ".".equals(String.valueOf(str.charAt(str.length() - 5)))))
/*     */     {
/*     */ 
/*     */       
/*  37 */       return true;
/*     */     }
/*  39 */     return false;
/*     */   }
/*     */   
/*     */   public static File copy(File src, String path) throws IOException {
/*  43 */     FileInputStream fis = new FileInputStream(src);
/*  44 */     BufferedInputStream bis = new BufferedInputStream(fis);
/*  45 */     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
/*     */     
/*  47 */     int i = -1;
/*  48 */     while ((i = bis.read()) != -1) {
/*  49 */       bos.write(i);
/*     */     }
/*  51 */     fis.close();
/*  52 */     bis.close();
/*  53 */     bos.flush();
/*  54 */     bos.close();
/*  55 */     return new File(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public static File readToFile(String content, File to) {
/*  60 */     byte[] bytes = Base64.decodeBase64(content);
/*     */     try {
/*  62 */       org.apache.commons.io.FileUtils.writeByteArrayToFile(to, bytes);
/*     */     }
/*  64 */     catch (FileNotFoundException e) {
/*  65 */       e.printStackTrace();
/*  66 */     } catch (IOException e) {
/*  67 */       e.printStackTrace();
/*     */     } 
/*  69 */     return to;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String read(File src) {
/*  74 */     if (src == null || !src.isFile()) {
/*  75 */       return null;
/*     */     }
/*     */     
/*     */     try {
/*  79 */       FileInputStream fis = new FileInputStream(src);
/*  80 */       BufferedInputStream bis = new BufferedInputStream(fis);
/*  81 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  82 */       int i = -1;
/*  83 */       while ((i = bis.read()) != -1) {
/*  84 */         baos.write(i);
/*     */       }
/*  86 */       String v = Base64.encodeBase64String(baos.toByteArray());
/*  87 */       fis.close();
/*  88 */       bis.close();
/*  89 */       baos.close();
/*     */       
/*  91 */       return v;
/*  92 */     } catch (FileNotFoundException e) {
/*  93 */       e.printStackTrace();
/*  94 */     } catch (IOException e) {
/*  95 */       e.printStackTrace();
/*     */     } 
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static File download(String urlString, String toFile) throws IOException {
/* 102 */     URL url = new URL(urlString);
/* 103 */     InputStream is = url.openStream();
/* 104 */     BufferedInputStream bis = new BufferedInputStream(is);
/* 105 */     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toFile));
/*     */     
/* 107 */     int i = -1;
/* 108 */     while ((i = bis.read()) != -1) {
/* 109 */       bos.write(i);
/*     */     }
/* 111 */     is.close();
/* 112 */     bis.close();
/* 113 */     bos.flush();
/* 114 */     bos.close();
/*     */     
/* 116 */     File file = new File(toFile);
/* 117 */     if (file.exists()) {
/* 118 */       return file;
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fileToOutputStream(File file, OutputStream os) throws Exception {
/* 126 */     FileInputStream fis = new FileInputStream(file);
/* 127 */     ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 128 */     byte[] tmpbuf = new byte[1024];
/* 129 */     int count = 0;
/* 130 */     while ((count = fis.read(tmpbuf)) != -1) {
/* 131 */       bout.write(tmpbuf, 0, count);
/* 132 */       tmpbuf = new byte[1024];
/*     */     } 
/* 134 */     fis.close();
/* 135 */     byte[] orgData = bout.toByteArray();
/* 136 */     os.write(orgData);
/* 137 */     bout.close();
/* 138 */     os.flush();
/* 139 */     os.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean fileIsImage(String fileUrl) {
/* 144 */     String str = fileUrl;
/* 145 */     if (str.equals("BMP") || str.equals("PCX") || str.equals("TIFF") || str.equals("GIF") || str.equals("JPG") || str.equals("JPEG") || str.equals("TGA") || str.equals("EXIF") || str.equals("FPX") || str.equals("SVG") || str.equals("PSD") || str.equals("CDR") || str.equals("PCD") || str.equals("DXF") || str.equals("UFO") || str.equals("EPS") || str.equals("AI") || str.equals("PNG") || str.equals("HDRI") || str.equals("RAW") || str.equals("DWG"))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       return false;
/*     */     }
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean fileIsOffice(String fileUrl) {
/* 160 */     String str = fileUrl;
/* 161 */     if (str.equals("doc") || str.equals("docm") || str.equals("docx") || str.equals("dot") || str.equals("dotm") || str.equals("dotx") || str.equals("htm") || str.equals("html") || str.equals("txt") || str.equals("xls") || str.equals("xlsx") || str.equals("xml") || str.equals("pot") || str.equals("ppt") || str.equals("pptx"))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       return true;
/*     */     }
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   public static String getFileUrl(String jUrl) {
/* 173 */     String[] strs1 = jUrl.split("/");
/* 174 */     String prantPath = strs1[0].split("_")[0];
/* 175 */     String name = strs1[1].split("_")[1];
/* 176 */     String type = getFileNamePrefix(strs1[1].split("_")[2]);
/*     */     
/* 178 */     return prantPath + "/" + name + '.' + type;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\FileUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */