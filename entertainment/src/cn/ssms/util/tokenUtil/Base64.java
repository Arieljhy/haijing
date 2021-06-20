/*     */ package cn.ssms.util.tokenUtil;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.CharArrayWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import sun.misc.BASE64Decoder;
/*     */ import sun.misc.BASE64Encoder;
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
/*     */ public class Base64
/*     */ {
/*     */   public static char[] encode(byte[] data) {
/*  81 */     char[] out = new char[(data.length + 2) / 3 * 4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
/*  88 */       boolean quad = false;
/*  89 */       boolean trip = false;
/*     */       
/*  91 */       int val = 0xFF & data[i];
/*  92 */       val <<= 8;
/*  93 */       if (i + 1 < data.length) {
/*  94 */         val |= 0xFF & data[i + 1];
/*  95 */         trip = true;
/*     */       } 
/*  97 */       val <<= 8;
/*  98 */       if (i + 2 < data.length) {
/*  99 */         val |= 0xFF & data[i + 2];
/* 100 */         quad = true;
/*     */       } 
/* 102 */       out[index + 3] = alphabet[quad ? (val & 0x3F) : 64];
/* 103 */       val >>= 6;
/* 104 */       out[index + 2] = alphabet[trip ? (val & 0x3F) : 64];
/* 105 */       val >>= 6;
/* 106 */       out[index + 1] = alphabet[val & 0x3F];
/* 107 */       val >>= 6;
/* 108 */       out[index + 0] = alphabet[val & 0x3F];
/*     */     } 
/* 110 */     return out;
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
/*     */   public static byte[] decode(char[] data) {
/* 132 */     int tempLen = data.length;
/* 133 */     for (int ix = 0; ix < data.length; ix++) {
/* 134 */       if (data[ix] > 'ÿ' || codes[data[ix]] < 0) {
/* 135 */         tempLen--;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     int len = tempLen / 4 * 3;
/* 143 */     if (tempLen % 4 == 3)
/* 144 */       len += 2; 
/* 145 */     if (tempLen % 4 == 2) {
/* 146 */       len++;
/*     */     }
/* 148 */     byte[] out = new byte[len];
/*     */ 
/*     */     
/* 151 */     int shift = 0;
/* 152 */     int accum = 0;
/* 153 */     int index = 0;
/*     */ 
/*     */     
/* 156 */     for (int i = 0; i < data.length; i++) {
/* 157 */       int value = (data[i] > 'ÿ') ? -1 : codes[data[i]];
/*     */       
/* 159 */       if (value >= 0) {
/*     */         
/* 161 */         accum <<= 6;
/* 162 */         shift += 6;
/* 163 */         accum |= value;
/* 164 */         if (shift >= 8) {
/*     */           
/* 166 */           shift -= 8;
/* 167 */           out[index++] = (byte)(accum >> shift & 0xFF);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (index != out.length) {
/* 181 */       throw new Error("Miscalculated data length (wrote " + index + " instead of " + out.length + ")");
/*     */     }
/*     */ 
/*     */     
/* 185 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   private static byte[] codes = new byte[256];
/*     */   static {
/*     */     int i;
/* 201 */     for (i = 0; i < 256; i++)
/* 202 */       codes[i] = -1; 
/* 203 */     for (i = 65; i <= 90; i++)
/* 204 */       codes[i] = (byte)(i - 65); 
/* 205 */     for (i = 97; i <= 122; i++)
/* 206 */       codes[i] = (byte)(26 + i - 97); 
/* 207 */     for (i = 48; i <= 57; i++)
/* 208 */       codes[i] = (byte)(52 + i - 48); 
/* 209 */     codes[43] = 62;
/* 210 */     codes[47] = 63;
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
/*     */   public static void main(String[] args) {
/* 222 */     boolean decode = false;
/*     */     
/* 224 */     if (args.length == 0) {
/* 225 */       System.out.println("usage:  java Base64 [-d[ecode]] filename");
/* 226 */       System.exit(0);
/*     */     } 
/* 228 */     for (int i = 0; i < args.length; i++) {
/* 229 */       if ("-decode".equalsIgnoreCase(args[i])) {
/* 230 */         decode = true;
/* 231 */       } else if ("-d".equalsIgnoreCase(args[i])) {
/* 232 */         decode = true;
/*     */       } 
/*     */     } 
/* 235 */     String filename = args[args.length - 1];
/* 236 */     File file = new File(filename);
/* 237 */     if (!file.exists()) {
/* 238 */       System.out.println("Error:  file '" + filename + "' doesn't exist!");
/* 239 */       System.exit(0);
/*     */     } 
/*     */     
/* 242 */     if (decode) {
/* 243 */       char[] encoded = readChars(file);
/* 244 */       byte[] decoded = decode(encoded);
/* 245 */       writeBytes(file, decoded);
/*     */     } else {
/*     */       
/* 248 */       byte[] decoded = readBytes(file);
/* 249 */       char[] encoded = encode(decoded);
/* 250 */       writeChars(file, encoded);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static byte[] readBytes(File file) {
/* 255 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     try {
/* 257 */       InputStream fis = new FileInputStream(file);
/* 258 */       InputStream is = new BufferedInputStream(fis);
/* 259 */       int count = 0;
/* 260 */       byte[] buf = new byte[16384];
/* 261 */       while ((count = is.read(buf)) != -1) {
/* 262 */         if (count > 0)
/* 263 */           baos.write(buf, 0, count); 
/*     */       } 
/* 265 */       is.close();
/*     */     }
/* 267 */     catch (Exception e) {
/* 268 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 271 */     return baos.toByteArray();
/*     */   }
/*     */   
/*     */   private static char[] readChars(File file) {
/* 275 */     CharArrayWriter caw = new CharArrayWriter();
/*     */     try {
/* 277 */       Reader fr = new FileReader(file);
/* 278 */       Reader in = new BufferedReader(fr);
/* 279 */       int count = 0;
/* 280 */       char[] buf = new char[16384];
/* 281 */       while ((count = in.read(buf)) != -1) {
/* 282 */         if (count > 0)
/* 283 */           caw.write(buf, 0, count); 
/*     */       } 
/* 285 */       in.close();
/*     */     }
/* 287 */     catch (Exception e) {
/* 288 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 291 */     return caw.toCharArray();
/*     */   }
/*     */   
/*     */   private static void writeBytes(File file, byte[] data) {
/*     */     try {
/* 296 */       OutputStream fos = new FileOutputStream(file);
/* 297 */       OutputStream os = new BufferedOutputStream(fos);
/* 298 */       os.write(data);
/* 299 */       os.close();
/*     */     }
/* 301 */     catch (Exception e) {
/* 302 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeChars(File file, char[] data) {
/*     */     try {
/* 308 */       Writer fos = new FileWriter(file);
/* 309 */       Writer os = new BufferedWriter(fos);
/* 310 */       os.write(data);
/* 311 */       os.close();
/*     */     }
/* 313 */     catch (Exception e) {
/* 314 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBase64(String str) {
/* 323 */     byte[] b = null;
/* 324 */     String s = null;
/*     */     try {
/* 326 */       b = str.getBytes("utf-8");
/* 327 */     } catch (UnsupportedEncodingException e) {
/* 328 */       e.printStackTrace();
/*     */     } 
/* 330 */     if (b != null) {
/* 331 */       s = (new BASE64Encoder()).encode(b);
/*     */     }
/* 333 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFromBase64(String s) {
/* 338 */     byte[] b = null;
/* 339 */     String result = null;
/* 340 */     if (s != null) {
/* 341 */       BASE64Decoder decoder = new BASE64Decoder();
/*     */       try {
/* 343 */         b = decoder.decodeBuffer(s);
/* 344 */         result = new String(b, "utf-8");
/* 345 */       } catch (Exception e) {
/* 346 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 349 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\tokenUtil\Base64.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */