/*     */ package cn.ssms.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.http.HttpServletRequest;
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
/*     */ public final class SystemUtils
/*     */ {
/*     */   public static String getIpAddr(HttpServletRequest request) {
/*  28 */     String ip = request.getHeader("X-Real-IP");
/*  29 */     if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
/*  30 */       return ip;
/*     */     }
/*  32 */     ip = request.getHeader("X-Forwarded-For");
/*  33 */     if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
/*     */       
/*  35 */       int index = ip.indexOf(',');
/*  36 */       if (index != -1) {
/*  37 */         return ip.substring(0, index);
/*     */       }
/*  39 */       return ip;
/*     */     } 
/*     */     
/*  42 */     return request.getRemoteAddr();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getRequestBrowserInfo(HttpServletRequest request) {
/*  52 */     String browserVersion = null;
/*  53 */     String header = request.getHeader("user-agent");
/*  54 */     if (header == null || header.equals("")) {
/*  55 */       return "";
/*     */     }
/*  57 */     if (header.indexOf("MSIE") > 0) {
/*  58 */       browserVersion = "IE";
/*  59 */     } else if (header.indexOf("Firefox") > 0) {
/*  60 */       browserVersion = "Firefox";
/*  61 */     } else if (header.indexOf("Chrome") > 0) {
/*  62 */       browserVersion = "Chrome";
/*  63 */     } else if (header.indexOf("Safari") > 0) {
/*  64 */       browserVersion = "Safari";
/*  65 */     } else if (header.indexOf("Camino") > 0) {
/*  66 */       browserVersion = "Camino";
/*  67 */     } else if (header.indexOf("Konqueror") > 0) {
/*  68 */       browserVersion = "Konqueror";
/*     */     } 
/*  70 */     return browserVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getRequestSystemInfo(HttpServletRequest request) {
/*  79 */     String systenInfo = null;
/*  80 */     String header = request.getHeader("user-agent");
/*  81 */     if (header == null || header.equals("")) {
/*  82 */       return "";
/*     */     }
/*     */     
/*  85 */     if (header.indexOf("NT 6.0") > 0) {
/*  86 */       systenInfo = "Windows Vista/Server 2008";
/*  87 */     } else if (header.indexOf("NT 5.2") > 0) {
/*  88 */       systenInfo = "Windows Server 2003";
/*  89 */     } else if (header.indexOf("NT 5.1") > 0) {
/*  90 */       systenInfo = "Windows XP";
/*  91 */     } else if (header.indexOf("NT 6.0") > 0) {
/*  92 */       systenInfo = "Windows Vista";
/*  93 */     } else if (header.indexOf("NT 6.1") > 0) {
/*  94 */       systenInfo = "Windows 7";
/*  95 */     } else if (header.indexOf("NT 6.2") > 0) {
/*  96 */       systenInfo = "Windows Slate";
/*  97 */     } else if (header.indexOf("NT 6.3") > 0) {
/*  98 */       systenInfo = "Windows 9";
/*  99 */     } else if (header.indexOf("NT 5") > 0) {
/* 100 */       systenInfo = "Windows 2000";
/* 101 */     } else if (header.indexOf("NT 4") > 0) {
/* 102 */       systenInfo = "Windows NT4";
/* 103 */     } else if (header.indexOf("Me") > 0) {
/* 104 */       systenInfo = "Windows Me";
/* 105 */     } else if (header.indexOf("98") > 0) {
/* 106 */       systenInfo = "Windows 98";
/* 107 */     } else if (header.indexOf("95") > 0) {
/* 108 */       systenInfo = "Windows 95";
/* 109 */     } else if (header.indexOf("Mac") > 0) {
/* 110 */       systenInfo = "Mac";
/* 111 */     } else if (header.indexOf("Unix") > 0) {
/* 112 */       systenInfo = "UNIX";
/* 113 */     } else if (header.indexOf("Linux") > 0) {
/* 114 */       systenInfo = "Linux";
/* 115 */     } else if (header.indexOf("SunOS") > 0) {
/* 116 */       systenInfo = "SunOS";
/* 117 */     } else if (header.indexOf("NT 10.0") > 0) {
/* 118 */       systenInfo = "Windows 10";
/*     */     } 
/* 120 */     return systenInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getHostName(String ip) {
/*     */     try {
/* 131 */       InetAddress inet = InetAddress.getByName(ip);
/* 132 */       return inet.getHostName();
/* 133 */     } catch (UnknownHostException e) {
/* 134 */       e.printStackTrace();
/*     */       
/* 136 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String callCmd(String[] cmd) {
/* 145 */     String result = "";
/* 146 */     String line = "";
/*     */     try {
/* 148 */       Process proc = Runtime.getRuntime().exec(cmd);
/* 149 */       InputStreamReader is = new InputStreamReader(proc.getInputStream());
/* 150 */       BufferedReader br = new BufferedReader(is);
/* 151 */       while ((line = br.readLine()) != null) {
/* 152 */         result = result + line;
/*     */       }
/* 154 */     } catch (Exception e) {
/* 155 */       e.printStackTrace();
/*     */     } 
/* 157 */     return result;
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
/*     */   private static String callCmd(String[] cmd, String[] another) {
/* 174 */     String result = "";
/* 175 */     String line = "";
/*     */     try {
/* 177 */       Runtime rt = Runtime.getRuntime();
/* 178 */       Process proc = rt.exec(cmd);
/* 179 */       proc.waitFor();
/* 180 */       proc = rt.exec(another);
/* 181 */       InputStreamReader is = new InputStreamReader(proc.getInputStream());
/* 182 */       BufferedReader br = new BufferedReader(is);
/* 183 */       while ((line = br.readLine()) != null) {
/* 184 */         result = result + line;
/*     */       }
/* 186 */     } catch (Exception e) {
/* 187 */       e.printStackTrace();
/*     */     } 
/* 189 */     return result;
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
/*     */   private static String filterMacAddress(String ip, String sourceString, String macSeparator) {
/* 210 */     String result = "";
/* 211 */     String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
/* 212 */     Pattern pattern = Pattern.compile(regExp);
/* 213 */     Matcher matcher = pattern.matcher(sourceString);
/* 214 */     while (matcher.find()) {
/* 215 */       result = matcher.group(1);
/* 216 */       if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
/*     */         break;
/*     */       }
/*     */     } 
/* 220 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMacInWindows(String ip) {
/* 231 */     String result = "";
/* 232 */     String[] cmd = { "cmd", "/c", "ping " + ip };
/* 233 */     String[] another = { "cmd", "/c", "arp -a" };
/* 234 */     String cmdResult = callCmd(cmd, another);
/* 235 */     result = filterMacAddress(ip, cmdResult, "-");
/* 236 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMacInLinux(String ip) {
/* 246 */     String result = "";
/* 247 */     String[] cmd = { "/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a" };
/* 248 */     String cmdResult = callCmd(cmd);
/* 249 */     result = filterMacAddress(ip, cmdResult, ":");
/* 250 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMacAddress(String ip) {
/* 259 */     String macAddress = "";
/* 260 */     macAddress = getMacInWindows(ip).trim();
/* 261 */     if (macAddress == null || "".equals(macAddress)) {
/* 262 */       macAddress = getMacInLinux(ip).trim();
/*     */     }
/* 264 */     return macAddress;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\SystemUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */