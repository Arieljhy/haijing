/*    */ package cn.ssms.service;
/*    */ 
/*    */ import cn.ssms.dao.InforMapper;
/*    */ import cn.ssms.model.Infor;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class InforServiceImple
/*    */   implements InforService
/*    */ {
/*    */   @Autowired
/*    */   private InforMapper inforMapper;
/*    */   
/*    */   public List<Map<String, Object>> getInforList(int classifyId) {
/* 20 */     return this.inforMapper.getInforList(classifyId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, Object> getInforById(int id) {
/* 26 */     return this.inforMapper.getInforById(id);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Infor> getInforByClassFiyId(Map<String, Object> map) {
/* 32 */     return this.inforMapper.getInforByClassFiyId(map);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\InforServiceImple.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */