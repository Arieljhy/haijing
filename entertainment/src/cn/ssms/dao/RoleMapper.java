package cn.ssms.dao;

import cn.ssms.model.Role;
import java.util.List;
import java.util.Map;

public interface RoleMapper {
  Integer deleteRole(Integer paramInteger);
  
  Integer updateRole(Role paramRole);
  
  List<Role> getRoleList(Map<String, Object> paramMap);
  
  Integer getRoleListTotal(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\RoleMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */