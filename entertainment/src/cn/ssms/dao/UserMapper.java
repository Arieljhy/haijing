package cn.ssms.dao;

import cn.ssms.model.User;
import java.util.List;
import java.util.Map;

public interface UserMapper {
  User selectByUserCode(String paramString);
  
  User loginValidation(User paramUser);
  
  User getUserById(Integer paramInteger);
  
  Integer updateUserById(User paramUser);
  
  Integer addUser(User paramUser);
  
  List<User> selectUserByParams(Map<String, Object> paramMap);
  
  Integer countUserByParams(Map<String, Object> paramMap);
  
  User checkCertificateCode(String paramString);
  
  List<User> getUserByConfigId(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\UserMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */