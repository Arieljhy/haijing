package cn.ssms.dao;

import cn.ssms.model.Classfiy;
import java.util.List;
import java.util.Map;

public interface ClassfiyMapper {
  List<Classfiy> getClassfiyByType(Map<String, Object> paramMap);
  
  List<Classfiy> getClassfiyByTypeList(Map<String, Object> paramMap);
  
  Integer getClassfiyByTypeCount(Map<String, Object> paramMap);
  
  int addClassfiy(Classfiy paramClassfiy);
  
  int updClassfiy(Classfiy paramClassfiy);
  
  Classfiy getClassfiyById(Integer paramInteger);
  
  List<Classfiy> isReName(Map<String, Object> paramMap);
  
  List<Classfiy> isReSequene(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\ClassfiyMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */