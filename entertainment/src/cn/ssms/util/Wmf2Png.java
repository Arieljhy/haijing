/*     */ package cn.ssms.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Scanner;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import net.arnx.wmf2svg.gdi.Gdi;
/*     */ import net.arnx.wmf2svg.gdi.svg.SvgGdi;
/*     */ import net.arnx.wmf2svg.gdi.wmf.WmfParser;
/*     */ import org.apache.batik.transcoder.TranscoderInput;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.image.PNGTranscoder;
/*     */ import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Wmf2Png
/*     */ {
/*     */   public static void main(String[] args) throws Exception {}
/*     */   
/*     */   public static String convert(String filePath) {
/*  46 */     String pngFile = "";
/*  47 */     File wmfFile = new File(filePath);
/*     */     try {
/*  49 */       if (!wmfFile.getName().contains(".wmf")) {
/*  50 */         throw new Exception("请确认输入的文件类型是wmf");
/*     */       }
/*     */       
/*  53 */       String svgFile = filePath.replace("wmf", "svg");
/*  54 */       wmfToSvg(filePath, svgFile);
/*     */       
/*  56 */       PreprocessSvgFile(svgFile);
/*     */       
/*  58 */       pngFile = filePath.replace("wmf", "png");
/*  59 */       svgToPng(svgFile, pngFile);
/*     */       
/*  61 */       File file = new File(svgFile);
/*  62 */       if (file.exists()) {
/*  63 */         file.delete();
/*     */       }
/*     */       
/*  66 */       if (wmfFile.exists()) {
/*  67 */         wmfFile.delete();
/*     */       }
/*     */     }
/*  70 */     catch (Exception e) {
/*     */       try {
/*  72 */         e.printStackTrace();
/*  73 */         wmfToJpg(filePath);
/*  74 */       } catch (Exception e1) {
/*  75 */         e1.printStackTrace();
/*     */       } 
/*     */     } 
/*  78 */     return wmfFile.getName().replace("wmf", "png");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void wmfToSvg(String src, String dest) throws Exception {
/*  88 */     boolean compatible = false;
/*     */     try {
/*  90 */       InputStream in = new FileInputStream(src);
/*  91 */       WmfParser parser = new WmfParser();
/*  92 */       SvgGdi gdi = new SvgGdi(compatible);
/*  93 */       parser.parse(in, (Gdi)gdi);
/*     */       
/*  95 */       Document doc = gdi.getDocument();
/*  96 */       OutputStream out = new FileOutputStream(dest);
/*  97 */       if (dest.endsWith(".svgz")) {
/*  98 */         out = new GZIPOutputStream(out);
/*     */       }
/*     */       
/* 101 */       output(doc, out);
/* 102 */     } catch (Exception e) {
/* 103 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void output(Document doc, OutputStream out) throws Exception {
/* 115 */     TransformerFactory factory = TransformerFactory.newInstance();
/* 116 */     Transformer transformer = factory.newTransformer();
/* 117 */     transformer.setOutputProperty("method", "xml");
/* 118 */     transformer.setOutputProperty("encoding", "UTF-8");
/* 119 */     transformer.setOutputProperty("indent", "yes");
/* 120 */     transformer.setOutputProperty("doctype-public", "-//W3C//DTD SVG 1.0//EN");
/* 121 */     transformer.setOutputProperty("doctype-system", "http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
/*     */     
/* 123 */     transformer.transform(new DOMSource(doc), new StreamResult(out));
/* 124 */     out.flush();
/* 125 */     out.close();
/* 126 */     out = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void PreprocessSvgFile(String svgFile) throws Exception {
/* 136 */     int defaultWeight = 50;
/* 137 */     FileInputStream inputs = new FileInputStream(svgFile);
/* 138 */     Scanner sc = new Scanner(inputs, "UTF-8");
/* 139 */     ByteArrayOutputStream os = new ByteArrayOutputStream();
/* 140 */     while (sc.hasNextLine()) {
/* 141 */       String ln = sc.nextLine();
/* 142 */       if (!ln.startsWith("<!DOCTYPE")) {
/* 143 */         os.write((ln + "\r\n").getBytes());
/*     */       }
/*     */     } 
/* 146 */     os.flush();
/*     */     
/* 148 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*     */     
/* 150 */     DocumentBuilder builder = factory.newDocumentBuilder();
/* 151 */     Document doc = null;
/*     */     try {
/* 153 */       doc = builder.parse(new ByteArrayInputStream(os.toByteArray()));
/* 154 */     } catch (Exception e) {
/* 155 */       inputs = new FileInputStream(svgFile);
/* 156 */       os = new ByteArrayOutputStream();
/* 157 */       int noOfByteRead = 0;
/* 158 */       while ((noOfByteRead = inputs.read()) != -1) {
/* 159 */         os.write(noOfByteRead);
/*     */       }
/* 161 */       os.flush();
/* 162 */       doc = builder.parse(new ByteArrayInputStream(os.toByteArray()));
/*     */     } finally {
/* 164 */       os.close();
/* 165 */       inputs.close();
/*     */     } 
/*     */     
/* 168 */     int height = Integer.parseInt(((Element)doc.getElementsByTagName("svg").item(0)).getAttribute("height"));
/* 169 */     int width = Integer.parseInt(((Element)doc.getElementsByTagName("svg").item(0)).getAttribute("width"));
/* 170 */     int newHeight = 0;
/* 171 */     int newWidth = 0;
/* 172 */     newHeight = height / 10;
/* 173 */     newWidth = width / 10;
/*     */     
/* 175 */     if (newWidth > defaultWeight) {
/* 176 */       newWidth = defaultWeight;
/* 177 */       newHeight = defaultWeight * height / width;
/*     */     } 
/*     */     
/* 180 */     ((Element)doc.getElementsByTagName("svg").item(0)).setAttribute("width", String.valueOf(newWidth));
/* 181 */     ((Element)doc.getElementsByTagName("svg").item(0)).setAttribute("height", String.valueOf(newHeight));
/* 182 */     OutputStream out = new FileOutputStream(svgFile);
/* 183 */     output(doc, out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void svgToPng(String svgPath, String pngFile) throws Exception {
/* 193 */     File svg = new File(svgPath);
/* 194 */     FileInputStream wmfStream = new FileInputStream(svg);
/* 195 */     ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
/* 196 */     int noOfByteRead = 0;
/* 197 */     while ((noOfByteRead = wmfStream.read()) != -1) {
/* 198 */       imageOut.write(noOfByteRead);
/*     */     }
/* 200 */     imageOut.flush();
/* 201 */     imageOut.close();
/* 202 */     wmfStream.close();
/*     */     
/* 204 */     ByteArrayOutputStream jpg = new ByteArrayOutputStream();
/* 205 */     FileOutputStream jpgOut = new FileOutputStream(pngFile);
/*     */     
/* 207 */     byte[] bytes = imageOut.toByteArray();
/* 208 */     PNGTranscoder t = new PNGTranscoder();
/* 209 */     TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(bytes));
/* 210 */     TranscoderOutput out = new TranscoderOutput(jpg);
/* 211 */     t.transcode(in, out);
/* 212 */     jpgOut.write(jpg.toByteArray());
/* 213 */     jpgOut.flush();
/* 214 */     jpgOut.close();
/* 215 */     imageOut = null;
/* 216 */     jpgOut = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String wmfToJpg(String wmfPath) throws Exception {
/* 227 */     File wmf = new File(wmfPath);
/* 228 */     FileInputStream wmfStream = new FileInputStream(wmf);
/* 229 */     ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
/* 230 */     int noOfByteRead = 0;
/* 231 */     while ((noOfByteRead = wmfStream.read()) != -1) {
/* 232 */       imageOut.write(noOfByteRead);
/*     */     }
/* 234 */     imageOut.flush();
/* 235 */     imageOut.close();
/* 236 */     wmfStream.close();
/*     */ 
/*     */     
/* 239 */     WMFTranscoder transcoder = new WMFTranscoder();
/* 240 */     TranscodingHints hints = new TranscodingHints();
/* 241 */     transcoder.setTranscodingHints(hints);
/* 242 */     TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(imageOut.toByteArray()));
/* 243 */     ByteArrayOutputStream svg = new ByteArrayOutputStream();
/* 244 */     TranscoderOutput output = new TranscoderOutput(svg);
/* 245 */     transcoder.transcode(input, output);
/*     */ 
/*     */     
/* 248 */     ByteArrayOutputStream jpg = new ByteArrayOutputStream();
/* 249 */     String jpgFile = StringUtils.replace(wmfPath, "wmf", "png");
/* 250 */     FileOutputStream jpgOut = new FileOutputStream(jpgFile);
/*     */     
/* 252 */     byte[] bytes = svg.toByteArray();
/* 253 */     PNGTranscoder t = new PNGTranscoder();
/* 254 */     TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(bytes));
/* 255 */     TranscoderOutput out = new TranscoderOutput(jpg);
/* 256 */     t.transcode(in, out);
/* 257 */     jpgOut.write(jpg.toByteArray());
/* 258 */     jpgOut.flush();
/* 259 */     jpgOut.close();
/* 260 */     return jpgFile;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\Wmf2Png.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */