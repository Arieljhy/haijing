package cn.ssms.dao;

import cn.ssms.model.MainContent;
import cn.ssms.model.MainTest;
import java.util.List;
import java.util.Map;

public interface MainTestMapper {
  List<MainTest> getTestList(Map<String, Object> paramMap);
  
  int getTestListTotal(Map<String, Object> paramMap);
  
  List<MainContent> getContentList(Map<String, Object> paramMap);
  
  int getContentListTotal(Map<String, Object> paramMap);
  
  int addTest(MainTest paramMainTest);
  
  int addContent(MainContent paramMainContent);
  
  int addTestList(List<MainTest> paramList);
  
  int updContentById(MainContent paramMainContent);
  
  MainTest findTestOne(Integer paramInteger);
  
  MainContent findContentOne(Integer paramInteger);
  
  int updTestById(MainTest paramMainTest);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\MainTestMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */