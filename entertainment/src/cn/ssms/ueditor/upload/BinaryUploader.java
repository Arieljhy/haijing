/*    */ package cn.ssms.ueditor.upload;
/*    */ 
/*    */ import com.baidu.ueditor.PathFormat;
/*    */ import com.baidu.ueditor.define.BaseState;
/*    */ import com.baidu.ueditor.define.FileType;
/*    */ import com.baidu.ueditor.define.State;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.commons.fileupload.FileItemFactory;
/*    */ import org.apache.commons.fileupload.FileItemIterator;
/*    */ import org.apache.commons.fileupload.FileItemStream;
/*    */ import org.apache.commons.fileupload.FileUploadException;
/*    */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*    */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BinaryUploader
/*    */ {
/*    */   public static final State save(HttpServletRequest request, Map<String, Object> conf) {
/* 27 */     FileItemStream fileStream = null;
/* 28 */     boolean isAjaxUpload = (request.getHeader("X_Requested_With") != null);
/*    */     
/* 30 */     if (!ServletFileUpload.isMultipartContent(request)) {
/* 31 */       return (State)new BaseState(false, 5);
/*    */     }
/*    */     
/* 34 */     ServletFileUpload upload = new ServletFileUpload((FileItemFactory)new DiskFileItemFactory());
/*    */ 
/*    */     
/* 37 */     if (isAjaxUpload) {
/* 38 */       upload.setHeaderEncoding("UTF-8");
/*    */     }
/*    */     
/*    */     try {
/* 42 */       FileItemIterator iterator = upload.getItemIterator(request);
/*    */       
/* 44 */       while (iterator.hasNext()) {
/* 45 */         fileStream = iterator.next();
/*    */         
/* 47 */         if (!fileStream.isFormField())
/*    */           break; 
/* 49 */         fileStream = null;
/*    */       } 
/*    */       
/* 52 */       if (fileStream == null) {
/* 53 */         return (State)new BaseState(false, 7);
/*    */       }
/*    */       
/* 56 */       String savePath = (String)conf.get("savePath");
/* 57 */       String originFileName = fileStream.getName();
/* 58 */       String suffix = FileType.getSuffixByFilename(originFileName);
/*    */       
/* 60 */       originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
/*    */       
/* 62 */       savePath = savePath + suffix;
/*    */       
/* 64 */       long maxSize = ((Long)conf.get("maxSize")).longValue();
/*    */       
/* 66 */       if (!validType(suffix, (String[])conf.get("allowFiles"))) {
/* 67 */         return (State)new BaseState(false, 8);
/*    */       }
/*    */       
/* 70 */       savePath = PathFormat.parse(savePath, originFileName);
/*    */       
/* 72 */       String physicalPath = (String)conf.get("rootPath") + savePath;
/*    */       
/* 74 */       InputStream is = fileStream.openStream();
/* 75 */       State storageState = StorageManager.saveFileByInputStream(is, physicalPath, maxSize);
/*    */       
/* 77 */       is.close();
/*    */       
/* 79 */       if (storageState.isSuccess()) {
/* 80 */         storageState.putInfo("url", PathFormat.format(savePath));
/* 81 */         storageState.putInfo("type", suffix);
/* 82 */         storageState.putInfo("original", originFileName + suffix);
/*    */       } 
/*    */       
/* 85 */       return storageState;
/* 86 */     } catch (FileUploadException e) {
/* 87 */       return (State)new BaseState(false, 6);
/* 88 */     } catch (IOException e) {
/*    */       
/* 90 */       return (State)new BaseState(false, 4);
/*    */     } 
/*    */   }
/*    */   private static boolean validType(String type, String[] allowTypes) {
/* 94 */     List<String> list = Arrays.asList(allowTypes);
/*    */     
/* 96 */     return list.contains(type);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\uedito\\upload\BinaryUploader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */