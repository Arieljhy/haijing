/*     */ package cn.ssms.util;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.poi.hwpf.HWPFDocument;
/*     */ import org.apache.poi.hwpf.HWPFDocumentCore;
/*     */ import org.apache.poi.hwpf.model.PicturesTable;
/*     */ import org.apache.poi.hwpf.usermodel.CharacterRun;
/*     */ import org.apache.poi.hwpf.usermodel.Paragraph;
/*     */ import org.apache.poi.hwpf.usermodel.Picture;
/*     */ import org.apache.poi.hwpf.usermodel.Range;
/*     */ import org.apache.poi.hwpf.usermodel.Table;
/*     */ import org.apache.poi.hwpf.usermodel.TableCell;
/*     */ import org.apache.poi.hwpf.usermodel.TableIterator;
/*     */ import org.apache.poi.hwpf.usermodel.TableRow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImportWord
/*     */ {
/*     */   private static final short ENTER_ASCII = 13;
/*  35 */   public static String title_answer = "";
/*  36 */   public static int taFlag = 0;
/*     */   
/*  38 */   public static int counter = 0;
/*  39 */   public static int beginPosi = 0;
/*  40 */   public static int endPosi = 0;
/*     */   public static int[] beginArray;
/*     */   public static int[] endArray;
/*     */   public static String[] htmlTextArray;
/*     */   public static boolean tblExist = false;
/*  45 */   public static String htmlTextTbl = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String wordImageFilePath() {
/*  53 */     return "C:/upload/wordImage/";
/*     */   }
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
/*     */   public static Map<String, List<String>> getWordAndStyle(InputStream inputStream) throws Exception {
/*  68 */     Map<String, List<String>> result = new HashMap<>();
/*     */     
/*  70 */     List<String> titles = new ArrayList<>();
/*     */     
/*  72 */     List<String> answers = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*  76 */     HWPFDocument doc = new HWPFDocument(inputStream);
/*     */     
/*  78 */     title_answer = "";
/*  79 */     taFlag = 0;
/*  80 */     beginPosi = 0;
/*  81 */     endPosi = 0;
/*  82 */     tblExist = false;
/*  83 */     htmlTextTbl = "";
/*     */     
/*  85 */     Range rangetbl = doc.getRange();
/*  86 */     TableIterator it = new TableIterator(rangetbl);
/*  87 */     int num = 100;
/*     */     
/*  89 */     beginArray = new int[num];
/*  90 */     endArray = new int[num];
/*  91 */     htmlTextArray = new String[num];
/*     */ 
/*     */ 
/*     */     
/*  95 */     int length = doc.characterLength();
/*     */     
/*  97 */     PicturesTable pTable = doc.getPicturesTable();
/*     */     
/*  99 */     if (it.hasNext())
/*     */     {
/* 101 */       readTable(it, rangetbl);
/*     */     }
/*     */     
/* 104 */     int cur = 0;
/*     */ 
/*     */ 
/*     */     
/* 108 */     for (int i = 0; i < length - 1; i++) {
/*     */       
/* 110 */       Range range = new Range(i, i + 1, (HWPFDocumentCore)doc);
/*     */       
/* 112 */       CharacterRun cr = range.getCharacterRun(0);
/*     */       
/* 114 */       if (tblExist)
/*     */       {
/* 116 */         if (i == beginArray[cur]) {
/*     */           
/* 118 */           title_answer += htmlTextArray[cur];
/* 119 */           i = endArray[cur] - 1;
/* 120 */           cur++;
/*     */           continue;
/*     */         } 
/*     */       }
/* 124 */       if (pTable.hasPicture(cr)) {
/*     */         
/* 126 */         readPicture(pTable, cr);
/*     */       }
/*     */       else {
/*     */         
/* 130 */         char c = cr.text().charAt(0);
/*     */         
/* 132 */         if (c == '【') {
/* 133 */           if (taFlag != 0) {
/* 134 */             title_answer = title_answer.replace("\023&nbsp; EMBED&nbsp; Equation.KSEE3&nbsp; &nbsp; \\*&nbsp; MERGEFORMAT&nbsp; \024", "");
/* 135 */             answers.add(title_answer);
/*     */           } 
/* 137 */           title_answer = "";
/* 138 */           taFlag = 1;
/* 139 */         } else if (c == '】') {
/* 140 */           titles.add(title_answer);
/* 141 */           title_answer = "";
/* 142 */           taFlag = 2;
/*     */         } else {
/* 144 */           if (c != '\r') {
/* 145 */             title_answer += c;
/*     */           }
/*     */ 
/*     */           
/* 149 */           if (c == '\r' && ("标题".equals(titles.get(titles.size() - 1)) || "内容".equals(titles.get(titles.size() - 1)) || "评分标准".equals(titles.get(titles.size() - 1)) || "内容".equals(titles.get(titles.size() - 1)))) {
/* 150 */             title_answer += "<br/>";
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*     */       continue;
/*     */     } 
/*     */     
/* 158 */     answers.add(title_answer);
/* 159 */     result.put("titles", titles);
/* 160 */     result.put("answers", answers);
/* 161 */     return result;
/*     */   }
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
/*     */   public static void readPicture(PicturesTable pTable, CharacterRun cr) throws Exception {
/* 175 */     Picture pic = pTable.extractPicture(cr, false);
/*     */     
/* 177 */     String afileName = pic.suggestFullFileName();
/*     */     
/* 179 */     String prefix = afileName.substring(afileName.lastIndexOf(".") + 1);
/* 180 */     System.out.println(prefix);
/*     */ 
/*     */     
/* 183 */     if (afileName.indexOf(".wmf") > 0) {
/* 184 */       int picSize = cr.getFontSize();
/* 185 */       int myHeight = 0;
/* 186 */       OutputStream out = new FileOutputStream(new File(wordImageFilePath() + afileName));
/* 187 */       out.write(pic.getContent());
/* 188 */       out.close();
/* 189 */       afileName = Wmf2Png.convert(wordImageFilePath() + afileName);
/* 190 */       File file = new File(wordImageFilePath() + afileName);
/* 191 */       BufferedImage image = ImageIO.read(file);
/* 192 */       int pheight = image.getHeight();
/* 193 */       int pwidth = image.getWidth();
/* 194 */       if (pwidth > 500) {
/* 195 */         title_answer += "<img style='width:" + pwidth + "px;height:" + myHeight + "px'" + " src=\"" + wordImageFilePath() + afileName + "\"/>";
/*     */       } else {
/*     */         
/* 198 */         myHeight = (int)(pheight / pwidth / picSize * 1.0D * 1.5D);
/* 199 */         title_answer += "<img style='vertical-align:middle;width:" + (picSize * 1.5D) + "px;height:" + myHeight + "px'" + " src=\"" + wordImageFilePath() + afileName + "\"/>";
/*     */       } 
/*     */     } else {
/*     */       
/* 203 */       OutputStream out = new FileOutputStream(new File(wordImageFilePath() + File.separator + afileName));
/* 204 */       pic.writeImageContent(out);
/* 205 */       title_answer += "<img src='" + wordImageFilePath() + afileName + "' mce_src='" + wordImageFilePath() + afileName + "' />";
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void readTable(TableIterator it, Range rangetbl) throws Exception {
/* 221 */     htmlTextTbl = "";
/*     */ 
/*     */     
/* 224 */     counter = -1;
/* 225 */     while (it.hasNext()) {
/*     */       
/* 227 */       tblExist = true;
/* 228 */       htmlTextTbl = "";
/* 229 */       Table tb = it.next();
/* 230 */       beginPosi = tb.getStartOffset();
/* 231 */       endPosi = tb.getEndOffset();
/*     */       
/* 233 */       counter++;
/*     */       
/* 235 */       beginArray[counter] = beginPosi;
/* 236 */       endArray[counter] = endPosi;
/*     */       
/* 238 */       htmlTextTbl += "<table border>";
/* 239 */       for (int i = 0; i < tb.numRows(); i++) {
/* 240 */         TableRow tr = tb.getRow(i);
/*     */         
/* 242 */         htmlTextTbl += "<tr>";
/*     */         
/* 244 */         for (int j = 0; j < tr.numCells(); j++) {
/* 245 */           TableCell td = tr.getCell(j);
/* 246 */           int cellWidth = td.getWidth();
/*     */ 
/*     */           
/* 249 */           for (int k = 0; k < td.numParagraphs(); k++) {
/* 250 */             Paragraph para = td.getParagraph(k);
/* 251 */             String s = para.text().toString().trim();
/* 252 */             if (s == "")
/*     */             {
/* 254 */               s = " ";
/*     */             }
/* 256 */             htmlTextTbl += "<td width=" + cellWidth + ">" + s + "</td>";
/*     */           } 
/*     */         } 
/*     */       } 
/* 260 */       htmlTextTbl += "</table>";
/* 261 */       htmlTextArray[counter] = htmlTextTbl;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String IsNullTag(String str) {
/* 267 */     if (str == null) {
/* 268 */       return "";
/*     */     }
/* 270 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\ImportWord.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */