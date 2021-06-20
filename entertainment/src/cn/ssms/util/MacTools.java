/*    */ package cn.ssms.util;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import java.net.InterfaceAddress;
/*    */ import java.net.NetworkInterface;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Enumeration;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MacTools {
/*    */   public static List<String> getMacList() throws Exception {
/* 12 */     Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
/* 13 */     StringBuilder sb = new StringBuilder();
/* 14 */     ArrayList<String> tmpMacList = new ArrayList<>();
/* 15 */     while (en.hasMoreElements()) {
/* 16 */       NetworkInterface iface = en.nextElement();
/* 17 */       List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
/* 18 */       for (InterfaceAddress addr : addrs) {
/* 19 */         InetAddress ip = addr.getAddress();
/* 20 */         System.out.println(ip.getHostAddress());
/* 21 */         NetworkInterface network = NetworkInterface.getByInetAddress(ip);
/* 22 */         if (network == null) {
/*    */           continue;
/*    */         }
/* 25 */         byte[] mac = network.getHardwareAddress();
/* 26 */         if (mac == null) {
/*    */           continue;
/*    */         }
/* 29 */         sb.delete(0, sb.length());
/* 30 */         for (int i = 0; i < mac.length; i++) {
/* 31 */           sb.append(String.format("%02X%s", new Object[] { Byte.valueOf(mac[i]), (i < mac.length - 1) ? "-" : "" }));
/*    */         } 
/* 33 */         tmpMacList.add(sb.toString());
/*    */       } 
/*    */     } 
/* 36 */     if (tmpMacList.size() <= 0) {
/* 37 */       return tmpMacList;
/*    */     }
/* 39 */     return tmpMacList;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 43 */     System.out.println("进行 multi net address 测试===》");
/* 44 */     List<String> macs = getMacList();
/* 45 */     System.out.println("本机的mac网卡的地址有：" + macs);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\MacTools.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */