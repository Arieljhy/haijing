package cn.ssms.service;

import cn.ssms.model.Admin;
import cn.ssms.model.Menu;
import cn.ssms.util.DataAccessException;
import java.util.List;
import java.util.Map;

public interface AdminService {
  Admin findAdminByCode(String paramString);
  
  List<Menu> getMenuListUserCode(String paramString);
  
  String getAdminList(Map<String, Object> paramMap);
  
  String getRoleList(Map<String, Object> paramMap);
  
  String addAdmin(Map<String, Object> paramMap) throws DataAccessException;
  
  String updateByCode(Map<String, Object> paramMap) throws DataAccessException;
  
  String removeByCode(Map<String, Object> paramMap) throws DataAccessException;
  
  String updPwdbyCode(Map<String, Object> paramMap) throws DataAccessException;
  
  String getAdminMenuList(Map<String, Object> paramMap);
  
  String updRoleMenu(Map<String, Object> paramMap) throws DataAccessException;
  
  String getMenuList();
  
  String addRole(Map<String, Object> paramMap) throws DataAccessException;
  
  String removeRole(Map<String, Object> paramMap) throws DataAccessException;
  
  String stopOrUseRole(Map<String, Object> paramMap);
  
  String getMenuListByType(Map<String, Object> paramMap);
  
  String addMenu(Map<String, Object> paramMap);
  
  Map<String, Object> updatePassword(Map<String, Object> paramMap) throws DataAccessException;
  
  String updateMenu(Map<String, Object> paramMap) throws DataAccessException;
  
  List<Admin> findAdminByRoleId(String paramString);
  
  Map<String, Object> checkAdminRole(Map<String, Object> paramMap, String paramString);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\AdminService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */