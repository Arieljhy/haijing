package cn.ssms.dao;

import java.util.List;
import java.util.Map;

public interface CommentsMapper {
  List<Map<String, Object>> getComments(Map<String, Object> paramMap);
  
  int insertComments(Map<String, Object> paramMap);
  
  int deleteCommentsById(Map<String, Object> paramMap);
  
  List<Map<String, Object>> getCommentsClassify(Map<String, Object> paramMap);
  
  int getCommentsClassifyCount(Map<String, Object> paramMap);
  
  List<Map<String, Object>> selectByPrimaryKey(Map<String, Object> paramMap);
  
  Integer selectByPrimaryKeytotal(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\CommentsMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */