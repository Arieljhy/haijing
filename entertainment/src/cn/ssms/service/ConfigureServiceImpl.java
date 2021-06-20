/*    */ package cn.ssms.service;
/*    */ 
/*    */ import cn.ssms.dao.ConfigureMapper;
/*    */ import cn.ssms.model.Configure;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service
/*    */ public class ConfigureServiceImpl
/*    */   implements ConfigureService {
/*    */   @Autowired
/*    */   private ConfigureMapper configureMapper;
/*    */   
/*    */   public List<Configure> getConfigList() {
/* 17 */     return this.configureMapper.getConfigList();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Configure> getConfigByType(Integer type) {
/* 22 */     return this.configureMapper.getConfigByType(type);
/*    */   }
/*    */ 
/*    */   
/*    */   public int addConfig(Map<String, Object> map) {
/* 27 */     return this.configureMapper.addConfig(map);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Configure> isReName(Map<String, Object> map) {
/* 32 */     return this.configureMapper.isReName(map);
/*    */   }
/*    */ 
/*    */   
/*    */   public int updateConfig(Map<String, Object> map) {
/* 37 */     return this.configureMapper.updateConfig(map);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\ConfigureServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */