/*     */ package cn.ssms.util;
/*     */ import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
/*     */ import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;
/*     */
/*     */
/*     */

/*     */
/*     */ public class ExcelStyleUtil implements IExcelExportStyler {
/*   9 */   private static final short STRING_FORMAT = (short) BuiltinFormats.getBuiltinFormat("TEXT");
/*     */ 
/*     */   
/*     */   private static final short FONT_SIZE_TEN = 10;
/*     */ 
/*     */   
/*     */   private static final short FONT_SIZE_ELEVEN = 11;
/*     */   
/*     */   private static final short FONT_SIZE_TWELVE = 12;
/*     */   
/*     */   private CellStyle headerStyle;
/*     */   
/*     */   private CellStyle titleStyle;
/*     */   
/*     */   private CellStyle styles;
/*     */ 
/*     */   
/*     */   public ExcelStyleUtil(Workbook workbook) {
/*  27 */     init(workbook);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init(Workbook workbook) {
/*  36 */     this.headerStyle = initHeaderStyle(workbook);
/*  37 */     this.titleStyle = initTitleStyle(workbook);
/*  38 */     this.styles = initStyles(workbook);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CellStyle getHeaderStyle(short color) {
/*  49 */     return this.headerStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CellStyle getTitleStyle(short color) {
/*  60 */     return this.titleStyle;
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
/*     */   public CellStyle getStyles(boolean parity, ExcelExportEntity entity) {
/*  72 */     return this.styles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
/*  83 */     return getStyles(true, entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CellStyle initHeaderStyle(Workbook workbook) {
/* 101 */     CellStyle style = getBaseCellStyle(workbook);
/* 102 */     style.setFont(getFont(workbook, (short)12, true));
/* 103 */     return style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CellStyle initTitleStyle(Workbook workbook) {
/* 113 */     CellStyle style = getBaseCellStyle(workbook);
/* 114 */     style.setFont(getFont(workbook, (short)11, false));
/*     */     
/* 116 */     style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
/* 117 */     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
/* 118 */     return style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CellStyle initStyles(Workbook workbook) {
/* 128 */     CellStyle style = getBaseCellStyle(workbook);
/* 129 */     style.setFont(getFont(workbook, (short)10, false));
/* 130 */     style.setDataFormat(STRING_FORMAT);
/* 131 */     return style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CellStyle getBaseCellStyle(Workbook workbook) {
/* 140 */     CellStyle style = workbook.createCellStyle();
/*     */     
/* 142 */     style.setBorderBottom(BorderStyle.THIN);
/*     */     
/* 144 */     style.setBorderLeft(BorderStyle.THIN);
/*     */     
/* 146 */     style.setBorderTop(BorderStyle.THIN);
/*     */     
/* 148 */     style.setBorderRight(BorderStyle.THIN);
/*     */     
/* 150 */     style.setAlignment(HorizontalAlignment.CENTER);
/*     */     
/* 152 */     style.setVerticalAlignment(VerticalAlignment.CENTER);
/*     */     
/* 154 */     style.setWrapText(true);
/* 155 */     return style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Font getFont(Workbook workbook, short size, boolean isBold) {
/* 166 */     Font font = workbook.createFont();
/*     */     
/* 168 */     font.setFontName("宋体");
/*     */     
/* 170 */     font.setBold(isBold);
/*     */     
/* 172 */     font.setFontHeightInPoints(size);
/* 173 */     return font;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\ExcelStyleUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */