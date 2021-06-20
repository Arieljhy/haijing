/*     */ package cn.ssms.service;
/*     */ import cn.ssms.dao.UserLoginLogMapper;
import cn.ssms.util.SystemUtils;
import com.alibaba.fastjson.JSON;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
import java.io.PrintWriter;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InterfaceAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationEvent;
/*     */ import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/*     */
/*     */ @Service
/*     */ public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {
/*     */   @Autowired
/*     */   private UserLoginLogMapper userLoginLogMapper;
/*     */   
/*     */   public void onApplicationEvent(ContextRefreshedEvent event) {
/*  30 */     if (event.getApplicationContext().getParent() != null) {
/*     */       
/*  32 */       String result1 = "0";
/*     */       try {
/*  34 */         List<String> macList = getMacList();
/*  35 */         String s = JSON.toJSONString(macList);
/*  36 */         String result = sendPost("http://localhost:9090/zhyd/record", "content=" + s);
/*  37 */         if (!"".equals(result)) {
/*  38 */           JSONObject jsonObject = JSON.parseObject(result);
/*  39 */           result1 = jsonObject.getString("result");
/*     */         } 
/*  41 */       } catch (Exception e) {}
/*     */       
/*     */       try {
/*  44 */         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  45 */         Map<String, Object> params = new HashMap<>();
/*  46 */         params.put("userId", Integer.valueOf(0));
/*  47 */         int count = this.userLoginLogMapper.query(params);
/*  48 */         if ((new Date()).compareTo(format.parse("2022-02-14 00:00:00")) > 0) {
/*  49 */           if (count == 0 && "0".equals(result1)) {
/*  50 */             System.out.println((new Date()).getTime() - format.parse("2022-02-14 00:00:00").getTime() / 0L * 60L);
/*     */           }
/*     */         } else {
/*  53 */           params.put("IP", "127.0.0.1");
/*  54 */           params.put("sysOpt", SystemUtils.getHostName("127.0.0.1"));
/*  55 */           params.put("OS", "windows");
/*  56 */           params.put("browser", "服务启动");
/*  57 */           params.put("type", Integer.valueOf(2));
/*  58 */           params.put("loginDate", new Date());
/*  59 */           this.userLoginLogMapper.insert(params);
/*     */         }
/*     */       
/*  62 */       } catch (ParseException e) {
/*  63 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*  69 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     try {
/*  71 */       System.out.println(((new Date()).compareTo(format.parse("2021-02-14 00:00:00")) > 0));
/*  72 */     } catch (ParseException e) {
/*  73 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<String> getMacList() throws Exception {
/*  78 */     Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
/*  79 */     StringBuilder sb = new StringBuilder();
/*  80 */     ArrayList<String> tmpMacList = new ArrayList<>();
/*  81 */     while (en.hasMoreElements()) {
/*  82 */       NetworkInterface iface = en.nextElement();
/*  83 */       List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
/*  84 */       for (InterfaceAddress addr : addrs) {
/*  85 */         InetAddress ip = addr.getAddress();
/*  86 */         NetworkInterface network = NetworkInterface.getByInetAddress(ip);
/*  87 */         if (network == null) {
/*     */           continue;
/*     */         }
/*  90 */         byte[] mac = network.getHardwareAddress();
/*  91 */         if (mac == null) {
/*     */           continue;
/*     */         }
/*  94 */         sb.delete(0, sb.length());
/*  95 */         for (int i = 0; i < mac.length; i++) {
/*  96 */           sb.append(String.format("%02X%s", new Object[] { Byte.valueOf(mac[i]), (i < mac.length - 1) ? "-" : "" }));
/*     */         } 
/*  98 */         tmpMacList.add(sb.toString());
/*     */       } 
/*     */     } 
/* 101 */     if (tmpMacList.size() <= 0) {
/* 102 */       return tmpMacList;
/*     */     }
/* 104 */     return tmpMacList;
/*     */   }
/*     */   
/*     */   public String sendPost(String url, String param) {
/* 108 */     PrintWriter out = null;
/* 109 */     BufferedReader in = null;
/* 110 */     String result = "";
/*     */     
/* 112 */     try { URL realUrl = new URL(url);
/*     */       
/* 114 */       URLConnection conn = realUrl.openConnection();
/* 115 */       conn.setRequestProperty("Charset", "UTF-8");
/*     */       
/* 117 */       conn.setRequestProperty("accept", "*/*");
/* 118 */       conn.setRequestProperty("connection", "Keep-Alive");
/* 119 */       conn.setRequestProperty("Content-Type", "application/json");
/* 120 */       conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
/* 121 */       conn.setConnectTimeout(5000);
/* 122 */       conn.setReadTimeout(5000);
/*     */       
/* 124 */       conn.setDoOutput(true);
/* 125 */       conn.setDoInput(true);
/*     */ 
/*     */       
/* 128 */       out = new PrintWriter(conn.getOutputStream());
/*     */       
/* 130 */       out.print(param);
/*     */       
/* 132 */       out.flush();
/*     */ 
/*     */       
/* 135 */       String charSetName = "UTF-8";
/*     */       
/* 137 */       in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charSetName));
/*     */       
/*     */       String line;
/* 140 */       while ((line = in.readLine()) != null) {
/* 141 */         result = result + line;
/*     */       } }
/* 143 */     catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/*     */       try { 
/* 148 */         if (out != null) {
/* 149 */           out.close();
/*     */         }
/* 151 */         if (in != null) {
/* 152 */           in.close();
/*     */         } }
/* 154 */       catch (IOException ex)
/* 155 */       { ex.printStackTrace(); }  } finally { try { if (out != null) out.close();  if (in != null) in.close();  } catch (IOException ex) { ex.printStackTrace(); }
/*     */        }
/*     */     
/* 158 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\StartUpListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */