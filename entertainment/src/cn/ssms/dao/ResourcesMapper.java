package cn.ssms.dao;

import cn.ssms.model.Resources;
import java.util.List;
import java.util.Map;

public interface ResourcesMapper {
  void addResource(Resources paramResources);
  
  List<Resources> getResources(Map<String, Object> paramMap);
  
  void updateResource(Map<String, Object> paramMap);
  
  int deleteByFileId(Integer paramInteger);
  
  int insertBatch(List<Resources> paramList);
  
  int deleteByPrimaryKey(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\ResourcesMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */