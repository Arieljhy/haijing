package cn.ssms.dao;

import cn.ssms.model.Menu;
import java.util.List;

public interface MenuMapper {
  int addMenu(Menu paramMenu);
  
  List<Menu> getMenuListUserCode(String paramString);
  
  List<Menu> getMenuByRoleId(Integer paramInteger);
  
  List<Menu> selectAllMenu();
  
  List<Menu> getMenuList();
  
  List<Menu> getSonMenuList(Integer paramInteger);
  
  Menu getMenuById(Integer paramInteger);
  
  int updateByPrimaryKeySelective(Menu paramMenu);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\MenuMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */