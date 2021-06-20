/*     */ package cn.ssms.util;
/*     */ import java.io.*;
/*     */
/*     */
/*     */
/*     */
/*     */ import java.math.BigDecimal;
/*     */ import java.net.InetAddress;
/*     */
import java.net.UnknownHostException;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
/*     */
/*     */
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class TextUtil {
/*  17 */   static Random random = new Random();
/*     */   static {
/*  19 */     random.setSeed(System.currentTimeMillis());
/*     */   }
/*     */   
/*     */   public static float getFloatValue(float pram) {
/*  23 */     BigDecimal bd = new BigDecimal(pram);
/*  24 */     pram = bd.setScale(2, 4).floatValue();
/*  25 */     return pram;
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
/*     */   public static long getLong(Object pram) {
/*  37 */     if (pram != null) {
/*     */       
/*  39 */       BigDecimal bg = new BigDecimal(pram + "");
/*  40 */       return bg.longValue();
/*     */     } 
/*  42 */     return 0L;
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
/*     */   public static short getShort(Object pram) {
/*  55 */     if (pram != null) {
/*     */       
/*  57 */       BigDecimal bg = new BigDecimal(pram + "");
/*  58 */       return bg.shortValue();
/*     */     } 
/*  60 */     return 0;
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
/*     */   public static Long getOlong(Object pram) {
/*  73 */     if (pram != null) {
/*     */       
/*  75 */       BigDecimal bg = new BigDecimal(pram + "");
/*  76 */       return new Long(bg.longValue());
/*     */     } 
/*  78 */     return null;
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
/*     */   public static String getString(Object pram) {
/*  91 */     if (pram == null) {
/*  92 */       return "";
/*     */     }
/*  94 */     return pram + "";
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
/*     */   public static String getDateFormate(Object pram) {
/* 107 */     if (pram != null) {
/*     */       
/* 109 */       Timestamp ts = (Timestamp)pram;
/*     */       
/* 111 */       long date = ts.getTime();
/*     */       
/* 113 */       Date jdate = new Date(date);
/*     */       
/* 115 */       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*     */       
/* 117 */       String str = df.format(jdate);
/*     */       
/* 119 */       return str;
/*     */     } 
/*     */     
/* 122 */     return "";
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
/*     */   public static short getShort(String obj) {
/* 136 */     if (obj != null && !"".equals(obj))
/*     */     {
/* 138 */       return Short.parseShort(obj);
/*     */     }
/*     */     
/* 141 */     return 0;
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
/*     */   public static Short getOshort(String obj) {
/* 154 */     if (obj != null && !"".equals(obj) && !"undefined".equals(obj))
/*     */     {
/* 156 */       return new Short(Short.parseShort(obj));
/*     */     }
/*     */     
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Long getLong(String obj) {
/* 165 */     if (obj != null && !"".equals(obj))
/*     */     {
/* 167 */       return new Long(Long.parseLong(obj));
/*     */     }
/*     */     
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getInt(String obj) {
/* 176 */     if (obj != null && !"".equals(obj))
/*     */     {
/* 178 */       return Integer.parseInt(obj);
/*     */     }
/*     */     
/* 181 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getFloat(String obj) {
/* 187 */     if (obj != null && !"".equals(obj) && !"null".equals(obj))
/*     */     {
/* 189 */       return Float.parseFloat(obj);
/*     */     }
/*     */     
/* 192 */     return 0.0F;
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
/*     */   public static String getContentString(Blob blob) {
/*     */     try {
/* 206 */       BufferedInputStream bi = new BufferedInputStream(blob.getBinaryStream());
/*     */       
/* 208 */       byte[] data = new byte[(int)blob.length()];
/* 209 */       String outfile = "";
/* 210 */       bi.read(data);
/* 211 */       outfile = new String(data);
/* 212 */       bi.close();
/* 213 */       return outfile;
/* 214 */     } catch (Exception ex) {
/* 215 */       ex.printStackTrace();
/*     */       
/* 217 */       return null;
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
/*     */   public static BigDecimal getBigDecimal(String obj) {
/* 255 */     if (obj != null && !"".equals(obj))
/*     */     {
/* 257 */       return new BigDecimal(obj);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 263 */     return null;
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
/*     */   public static Integer getOInteger(String obj) {
/* 275 */     if (obj != null && !"".equals(obj))
/*     */     {
/* 277 */       return new Integer(obj);
/*     */     }
/* 279 */     return null;
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
/*     */   public static List split(String data, String propertyName) {
/* 291 */     List<String> resList = new ArrayList();
/* 292 */     data = data.toLowerCase();
/*     */ 
/*     */     
/* 295 */     Pattern pattern2 = Pattern.compile("<transition .*?/>");
/* 296 */     Matcher matcher2 = pattern2.matcher(data);
/* 297 */     while (matcher2.find()) {
/* 298 */       String data1 = matcher2.group();
/* 299 */       resList.add(getAttribute(data1, propertyName));
/*     */     } 
/* 301 */     return resList;
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
/*     */   public static List getNodeID(String data, String propertyName) {
/* 313 */     List<String> resList = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/* 317 */     Pattern pattern2 = Pattern.compile("<task .*?>");
/* 318 */     Matcher matcher2 = pattern2.matcher(data);
/* 319 */     while (matcher2.find()) {
/* 320 */       String data1 = matcher2.group();
/* 321 */       resList.add(getAttribute(data1, propertyName));
/*     */     } 
/* 323 */     return resList;
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
/*     */   public static List getNode(String data, String property, String propertyName) {
/* 336 */     List<String> resList = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/* 340 */     Pattern pattern2 = Pattern.compile("<" + property + " .*?>");
/* 341 */     Matcher matcher2 = pattern2.matcher(data);
/* 342 */     while (matcher2.find()) {
/* 343 */       String data1 = matcher2.group();
/* 344 */       resList.add(getAttribute(data1, propertyName));
/*     */     } 
/*     */     
/* 347 */     return resList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getAttribute(String elementString, String attributeName) {
/* 380 */     Pattern p = Pattern.compile("<[^>]+>");
/* 381 */     Matcher m = p.matcher(elementString);
/* 382 */     String tmp = m.find() ? m.group() : "";
/* 383 */     p = Pattern.compile("(" + attributeName + "+)\\s*=\\s*\"([^\"]+)\"");
/* 384 */     m = p.matcher(tmp);
/* 385 */     if (m.find()) {
/* 386 */       return m.group(2);
/*     */     }
/* 388 */     return "";
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
/*     */   public static int getInt(Object obj) {
/* 400 */     if (obj != null && !"null".equals(obj)) {
/* 401 */       String intValue = obj + "";
/* 402 */       if (!"".equals(intValue) && intValue != null) {
/* 403 */         return Integer.parseInt(intValue);
/*     */       }
/*     */     } 
/*     */     
/* 407 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replaceXmlAttribute(String xmlData, String fromAttribute, String toAttribute, String fromArg, String toArg) {
/* 414 */     if (xmlData != null && !"".equals(xmlData))
/*     */     {
/* 416 */       xmlData = xmlData.replaceAll("" + fromAttribute + "=\"" + fromArg + "\"", "" + toAttribute + "=\"" + toArg + "\"");
/*     */     }
/*     */     
/* 419 */     return xmlData;
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
/*     */   public static int getBD2Int(Object pram) {
/* 431 */     if (pram != null) {
/*     */       
/* 433 */       BigDecimal bg = new BigDecimal(pram + "");
/* 434 */       return bg.intValue();
/*     */     } 
/* 436 */     return 0;
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
/*     */   
/*     */   public static Map<String, Object> checkFrontNum(String arg, int maxSize) throws UnsupportedEncodingException {
/* 452 */     Map<String, Object> map = new HashMap<>();
/* 453 */     if (arg != null && !"".equals(arg)) {
/*     */       
/* 455 */       byte[] b = arg.getBytes("GBK");
/*     */       
/* 457 */       if (maxSize < b.length) {
/*     */         
/* 459 */         int overLength = b.length - maxSize;
/* 460 */         map.put("overFlag", "1");
/* 461 */         map.put("overLength", Integer.valueOf(overLength));
/* 462 */         return map;
/*     */       } 
/* 464 */       map.put("overFlag", "0");
/* 465 */       return map;
/*     */     } 
/* 467 */     map.put("overFlag", "0");
/* 468 */     return map;
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
/*     */   public static byte[] getBytesFromFile(File file) throws IOException {
/* 482 */     long length = file.length();
/* 483 */     InputStream is = null;
/* 484 */     is = new BufferedInputStream(new FileInputStream(file));
/* 485 */     if (length > 2147483647L) {
/* 486 */       throw new IOException("File is to large " + file.getName());
/*     */     }
/* 488 */     byte[] bytes = new byte[(int)length];
/* 489 */     int offset = 0;
/* 490 */     int numRead = 0;
/* 491 */     while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
/*     */     {
/* 493 */       offset += numRead;
/*     */     }
/* 495 */     if (offset < bytes.length) {
/* 496 */       throw new IOException("Could not completely read file " + file.getName());
/*     */     }
/*     */     
/* 499 */     is.close();
/* 500 */     return bytes;
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
/*     */   public static byte[] InputStreamToByte(InputStream is) throws IOException {
/* 514 */     ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
/*     */     int ch;
/* 516 */     while ((ch = is.read()) != -1) {
/* 517 */       bytestream.write(ch);
/*     */     }
/* 519 */     byte[] imgdata = bytestream.toByteArray();
/* 520 */     bytestream.close();
/* 521 */     return imgdata;
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
/*     */   public static Long getLongValue(Object o) {
/* 534 */     if (o == null || "".equals(o) || "0".equals(o + "")) {
/* 535 */       return null;
/*     */     }
/* 537 */     return Long.valueOf(o + "");
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
/*     */   
/*     */   public static long[] generate(long total, int count, long max, long min) {
/* 553 */     long[] result = new long[count];
/*     */     
/* 555 */     long average = total / count;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 566 */     for (i = 0; i < result.length; i++) {
/*     */ 
/*     */ 
/*     */       
/* 570 */       if (nextLong(min, max) > average) {
/*     */ 
/*     */         
/* 573 */         long temp = min + xRandom(min, average);
/* 574 */         result[i] = temp;
/* 575 */         total -= temp;
/*     */       }
/*     */       else {
/*     */         
/* 579 */         long temp = max - xRandom(average, max);
/* 580 */         result[i] = temp;
/* 581 */         total -= temp;
/*     */       } 
/*     */     } 
/*     */     
/* 585 */     while (total > 0L) {
/* 586 */       for (i = 0; i < result.length; i++) {
/* 587 */         if (total > 0L && result[i] < max) {
/* 588 */           result[i] = result[i] + 1L;
/* 589 */           total--;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 594 */     while (total < 0L) {
/* 595 */       for (i = 0; i < result.length; i++) {
/* 596 */         if (total < 0L && result[i] > min) {
/* 597 */           result[i] = result[i] - 1L;
/* 598 */           total++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 602 */     return result;
/*     */   }
/*     */   
/*     */   static long xRandom(long min, long max) {
/* 606 */     return sqrt(nextLong(sqr(max - min)));
/*     */   }
/*     */ 
/*     */   
/*     */   static long sqrt(long n) {
/* 611 */     return (long)Math.sqrt(n);
/*     */   }
/*     */ 
/*     */   
/*     */   static long sqr(long n) {
/* 616 */     return n * n;
/*     */   }
/*     */   
/*     */   static long nextLong(long n) {
/* 620 */     return random.nextInt((int)n);
/*     */   }
/*     */   
/*     */   static long nextLong(long min, long max) {
/* 624 */     return random.nextInt((int)(max - min + 1L)) + min;
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
/*     */   
/*     */   public static int[] randomArray(int min, int max, int n) {
/* 640 */     int len = max - min + 1;
/*     */     
/* 642 */     if (max < min || n > len) {
/* 643 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 647 */     int[] source = new int[len];
/* 648 */     for (int i = min; i < min + len; i++) {
/* 649 */       source[i - min] = i;
/*     */     }
/*     */     
/* 652 */     int[] result = new int[n];
/* 653 */     Random rd = new Random();
/* 654 */     int index = 0;
/* 655 */     for (int j = 0; j < result.length; j++) {
/*     */       
/* 657 */       index = Math.abs(rd.nextInt() % len--);
/*     */       
/* 659 */       result[j] = source[index];
/*     */       
/* 661 */       source[index] = source[len];
/*     */     } 
/* 663 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String generateName() {
/* 668 */     String[] name1 = { "赵", "钱", "孙", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "周", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "郑", "王", "冯", "陈", "诸", "卫", "蒋", "沈", "韩", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "张", "张", "张", "张", "张", "张", "张", "张", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卡", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "堪", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "粱", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "咎", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "魏", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景", "詹", "束", "龙", "司马", "上官", "欧阳", "夏侯", "诸葛", "东方", "尉迟", "公孙", "长孙", "慕容", "司徒", "西门" };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 712 */     String[] name2 = { "超", "媛", "念", "立", "思", "嘉", "雨", "伟", "权", "秋", "佩", "雅", "联", "如", "渠", "保", "室", "铜", "梧", "胤", "昱", "佳", "伊", "校", "诗", "节", "如", "阁", "耕", "宫", "古", "谷", "观", "桂", "贵", "国", "广", "冠", "汉", "翰", "航", "杭", "海", "豪", "浩", "皓", "和", "河", "贺", "恒", "弘", "虹", "宏", "红", "厚", "鹄", "虎", "华", "欢", "怀", "辉", "惠", "会", "奇", "吉", "骥", "嘉", "佳", "甲", "稼", "江", "坚", "剑", "锦", "经", "镜", "界", "竞", "介", "京", "建", "净", "精", "敬", "静", "靖", "津", "进", "菁", "景", "炯", "驹", "举", "炬", "君", "俊", "军", "骏", "郡", "峻", "恺", "楷", "康", "可", "克", "珂", "逵", "魁", "阔", "昆", "奎", "宽", "况", "乐", "雷", "岭", "廉", "霖", "麟", "灵", "利", "良", "联", "烈", "罗", "陵", "梁", "立", "礼", "力", "莉", "烁", "隆", "龙", "禄", "璐", "露", "律", "茂", "梦", "密", "铭", "明", "绵", "妙", "默", "木", "能", "年", "宁", "念", "怒", "庞", "佩", "培", "朋", "鹏", "屏", "平", "魄", "珀", "璞", "奇", "琦", "齐", "启", "谦", "乾", "茜", "倩", "芹", "琴", "青", "卿", "秋", "权", "求", "情", "渠", "全", "荃", "群", "泉", "然", "让", "仁", "任", "荣", "儒", "锐", "若", "瑞", "三", "瑟", "森", "韶", "绍", "尚", "商", "珊", "善", "生", "升", "声", "晟", "胜", "盛", "诗", "时", "石", "实", "凇", "慎", "设", "守", "随", "世", "寿", "仕", "余", "帅", "双", "朔", "硕", "水", "誓", "适", "书", "舒", "殊", "顺", "思", "嗣", "似", "松", "颂", "素", "岁", "棠", "泰", "腾", "添", "铁", "同", "桐", "童", "彤", "团", "涂", "图", "土", "万", "旺", "望", "王", "闻", "威", "薇", "嵬", "伟", "卫", "蔚", "文", "微", "巍", "玮", "为", "畏", "吾", "午", "西", "熙", "玺", "仙", "先", "孝", "湘", "祥", "行", "献", "享", "效", "兴", "夏", "宣", "协", "向", "校", "轩", "瑕", "衔", "筱", "羡", "相", "香", "贤", "翔", "杏", "新", "信", "幸", "心", "星", "绣", "秀", "欣", "鑫", "兴", "行", "雄", "许", "炫", "雪", "学", "旭", "璇", "勋", "萱", "迅", "亚", "雅", "扬", "耀", "彦", "延", "岩", "炎", "永", "砚", "演", "焱", "洋", "阳", "曜", "耀", "夜", "译", "逸", "伊", "义", "艺", "意", "异", "怡", "翼", "毅", "银", "瑛", "仪", "易", "寅", "印", "苡", "野", "业", "英", "璎", "盈", "颖", "影", "雍", "勇", "悠", "由", "游", "佑", "友", "瑜", "遇", "玉", "岳", "元", "宇", "愚", "钰", "裕", "郁", "于" };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 745 */     int len1 = name1.length - 1;
/* 746 */     int len2 = name2.length - 1;
/* 747 */     int random1 = (int)(Math.random() * len1);
/* 748 */     int random2 = (int)(Math.random() * len2);
/* 749 */     int random2_1 = (int)(Math.random() * len2);
/*     */     
/* 751 */     String name = "";
/* 752 */     if (random1 < 256) {
/* 753 */       int randomN = (int)(Math.random() * 2.0D);
/* 754 */       if (randomN == 1) {
/* 755 */         name = name1[random1] + name2[random2];
/*     */       } else {
/* 757 */         name = name1[random1] + name2[random2] + name2[random2_1];
/*     */       } 
/*     */     } else {
/* 760 */       name = name1[random1] + name2[random2] + name2[random2_1];
/*     */     } 
/*     */     
/* 763 */     return name;
/*     */   }
/*     */   
/*     */   public static String check(String str) {
/* 767 */     if (str == null)
/* 768 */       return ""; 
/* 769 */     return str.replaceAll(".*([';]+|(--)+).*", "");
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
/*     */   public static String lpad(int length, int number) {
/* 782 */     String f = "";
/* 783 */     if (number < 1000000) {
/* 784 */       f = "1%0" + length + "d";
/*     */     } else {
/* 786 */       f = "%0" + length + "d";
/*     */     } 
/* 788 */     return String.format(f, new Object[] { Integer.valueOf(number) });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getIpAddr(HttpServletRequest request) {
/* 793 */     String ipAddress = null;
/*     */     
/* 795 */     ipAddress = request.getHeader("x-forwarded-for");
/* 796 */     if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
/*     */     {
/* 798 */       ipAddress = request.getHeader("Proxy-Client-IP");
/*     */     }
/* 800 */     if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
/*     */     {
/* 802 */       ipAddress = request.getHeader("WL-Proxy-Client-IP");
/*     */     }
/* 804 */     if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
/*     */       
/* 806 */       ipAddress = request.getRemoteAddr();
/* 807 */       if (ipAddress.equals("127.0.0.1")) {
/*     */         
/* 809 */         InetAddress inet = null;
/*     */         try {
/* 811 */           inet = InetAddress.getLocalHost();
/* 812 */         } catch (UnknownHostException e) {
/* 813 */           e.printStackTrace();
/*     */         } 
/* 815 */         ipAddress = inet.getHostAddress();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 820 */     if (ipAddress != null && ipAddress.length() > 15)
/*     */     {
/* 822 */       if (ipAddress.indexOf(",") > 0) {
/* 823 */         ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
/*     */       }
/*     */     }
/* 826 */     return ipAddress;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\TextUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */