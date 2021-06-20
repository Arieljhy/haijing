package cn.ssms.service;

import java.util.Map;

public interface CommentsService {
  int insertComments(Map<String, Object> paramMap);
  
  int deleteCommentsById(Map<String, Object> paramMap);
  
  String getCommentsClassify(Map<String, Object> paramMap);
  
  String getComments(Map<String, Object> paramMap);
  
  String deleteComments(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\CommentsService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */