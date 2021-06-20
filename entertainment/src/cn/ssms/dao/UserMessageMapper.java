package cn.ssms.dao;

import cn.ssms.model.UserMessage;
import java.util.List;
import java.util.Map;

public interface UserMessageMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(UserMessage paramUserMessage);
  
  int insertSelective(UserMessage paramUserMessage);
  
  UserMessage selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(UserMessage paramUserMessage);
  
  int updateByPrimaryKey(UserMessage paramUserMessage);
  
  List<UserMessage> getAllMessageByParams(Map<String, Object> paramMap);
  
  Integer countByParams(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\UserMessageMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */