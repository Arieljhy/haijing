package cn.ssms.service;

import cn.ssms.model.Configure;
import cn.ssms.model.User;
import java.util.List;
import java.util.Map;

public interface UserService {
  User selectByUserCode(String paramString);
  
  User loginValidation(User paramUser);
  
  User getUserById(String paramString);
  
  String userList(Map<String, Object> paramMap) throws Exception;
  
  List<User> removeuser(String paramString);
  
  User addUser(Map<String, Object> paramMap);
  
  User updateUser(Map<String, Object> paramMap);
  
  List<Configure> getConfig(Integer paramInteger);
  
  User checkCertificateCode(String paramString);
  
  List<User> getUserByConfigId(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\UserService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */