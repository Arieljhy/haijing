package cn.ssms.dao;

import cn.ssms.model.Admin;
import cn.ssms.model.AdminRole;
import java.util.List;
import java.util.Map;

public interface AdminMapper {
  Integer addAdmin(Admin paramAdmin);
  
  Admin findAdminByCode(String paramString);
  
  List<Admin> getAdminList(Map<String, Object> paramMap);
  
  Integer getAdminListTotal(Map<String, Object> paramMap);
  
  int insertAdminRole(AdminRole paramAdminRole);
  
  int updateByCode(Admin paramAdmin);
  
  int removeRoleListByAdminId(Long paramLong);
  
  int removeAuthorByRole(Long paramLong);
  
  int insertAuthor(Map<String, Object> paramMap);
  
  int addRole(Map<String, Object> paramMap);
  
  int removeAdminAuthorByRoleId(Long paramLong);
  
  void addAdminByMap(Map<String, Object> paramMap);
  
  void updateAdminById(Map<String, Object> paramMap);
  
  List<Admin> getPromotionUserList(Map<String, Object> paramMap);
  
  String getRegionNum(String paramString);
  
  Integer getAdminByPwd(Map<String, Object> paramMap);
  
  Integer getAdminByPayPwd(Map<String, Object> paramMap);
  
  List<Admin> findAdminByRoleId(Integer paramInteger);
  
  int checkRoleName(String paramString);
  
  Admin checkAdminRole(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\AdminMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */