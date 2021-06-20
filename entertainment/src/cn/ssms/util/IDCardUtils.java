/*     */ package cn.ssms.util;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Hashtable;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class IDCardUtils
/*     */ {
/*     */   public static String IDCardValidate(String IDStr) throws ParseException {
/*  34 */     String errorInfo = "";
/*  35 */     String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
/*     */     
/*  37 */     String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
/*     */     
/*  39 */     String Ai = "";
/*     */     
/*  41 */     if (IDStr.length() != 15 && IDStr.length() != 18) {
/*  42 */       errorInfo = "身份证号码长度应该为15位或18位。";
/*  43 */       return errorInfo;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  48 */     if (IDStr.length() == 18) {
/*  49 */       Ai = IDStr.substring(0, 17);
/*  50 */     } else if (IDStr.length() == 15) {
/*  51 */       Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
/*     */     } 
/*  53 */     if (!isNumeric(Ai)) {
/*  54 */       errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
/*  55 */       return errorInfo;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  60 */     String strYear = Ai.substring(6, 10);
/*  61 */     String strMonth = Ai.substring(10, 12);
/*  62 */     String strDay = Ai.substring(12, 14);
/*  63 */     if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
/*  64 */       errorInfo = "身份证生日无效。";
/*  65 */       return errorInfo;
/*     */     } 
/*  67 */     GregorianCalendar gc = new GregorianCalendar();
/*  68 */     SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/*  70 */       if (gc.get(1) - Integer.parseInt(strYear) > 150 || gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime() < 0L) {
/*     */ 
/*     */         
/*  73 */         errorInfo = "身份证生日不在有效范围。";
/*  74 */         return errorInfo;
/*     */       } 
/*  76 */     } catch (NumberFormatException e) {
/*  77 */       e.printStackTrace();
/*  78 */     } catch (ParseException e) {
/*  79 */       e.printStackTrace();
/*     */     } 
/*  81 */     if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
/*  82 */       errorInfo = "身份证月份无效";
/*  83 */       return errorInfo;
/*     */     } 
/*  85 */     if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
/*  86 */       errorInfo = "身份证日期无效";
/*  87 */       return errorInfo;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  92 */     Hashtable h = GetAreaCode();
/*  93 */     if (h.get(Ai.substring(0, 2)) == null) {
/*  94 */       errorInfo = "身份证地区编码错误。";
/*  95 */       return errorInfo;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 100 */     int TotalmulAiWi = 0;
/* 101 */     for (int i = 0; i < 17; i++) {
/* 102 */       TotalmulAiWi += Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
/*     */     }
/*     */ 
/*     */     
/* 106 */     int modValue = TotalmulAiWi % 11;
/* 107 */     String strVerifyCode = ValCodeArr[modValue];
/* 108 */     Ai = Ai + strVerifyCode;
/*     */     
/* 110 */     if (IDStr.length() == 18) {
/* 111 */       if (!Ai.equals(IDStr)) {
/* 112 */         errorInfo = "身份证无效，不是合法的身份证号码";
/* 113 */         return errorInfo;
/*     */       } 
/*     */     } else {
/* 116 */       return "";
/*     */     } 
/*     */     
/* 119 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Hashtable GetAreaCode() {
/* 129 */     Hashtable<Object, Object> hashtable = new Hashtable<>();
/* 130 */     hashtable.put("11", "北京");
/* 131 */     hashtable.put("12", "天津");
/* 132 */     hashtable.put("13", "河北");
/* 133 */     hashtable.put("14", "山西");
/* 134 */     hashtable.put("15", "内蒙古");
/* 135 */     hashtable.put("21", "辽宁");
/* 136 */     hashtable.put("22", "吉林");
/* 137 */     hashtable.put("23", "黑龙江");
/* 138 */     hashtable.put("31", "上海");
/* 139 */     hashtable.put("32", "江苏");
/* 140 */     hashtable.put("33", "浙江");
/* 141 */     hashtable.put("34", "安徽");
/* 142 */     hashtable.put("35", "福建");
/* 143 */     hashtable.put("36", "江西");
/* 144 */     hashtable.put("37", "山东");
/* 145 */     hashtable.put("41", "河南");
/* 146 */     hashtable.put("42", "湖北");
/* 147 */     hashtable.put("43", "湖南");
/* 148 */     hashtable.put("44", "广东");
/* 149 */     hashtable.put("45", "广西");
/* 150 */     hashtable.put("46", "海南");
/* 151 */     hashtable.put("50", "重庆");
/* 152 */     hashtable.put("51", "四川");
/* 153 */     hashtable.put("52", "贵州");
/* 154 */     hashtable.put("53", "云南");
/* 155 */     hashtable.put("54", "西藏");
/* 156 */     hashtable.put("61", "陕西");
/* 157 */     hashtable.put("62", "甘肃");
/* 158 */     hashtable.put("63", "青海");
/* 159 */     hashtable.put("64", "宁夏");
/* 160 */     hashtable.put("65", "新疆");
/* 161 */     hashtable.put("71", "台湾");
/* 162 */     hashtable.put("81", "香港");
/* 163 */     hashtable.put("82", "澳门");
/* 164 */     hashtable.put("91", "国外");
/* 165 */     return hashtable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isNumeric(String str) {
/* 175 */     Pattern pattern = Pattern.compile("[0-9]*");
/* 176 */     Matcher isNum = pattern.matcher(str);
/* 177 */     if (isNum.matches()) {
/* 178 */       return true;
/*     */     }
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isDate(String strDate) {
/* 191 */     Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
/*     */     
/* 193 */     Matcher m = pattern.matcher(strDate);
/* 194 */     if (m.matches()) {
/* 195 */       return true;
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\IDCardUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */