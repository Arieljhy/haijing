package cn.ssms.service;

import cn.ssms.model.MainTest;
import cn.ssms.model.TestTitle;
import cn.ssms.util.DataAccessException;
import java.util.List;
import java.util.Map;

public interface StudyService {
  String getContentList(Map<String, Object> paramMap);
  
  String addContent(Map<String, Object> paramMap);
  
  String updContent(Map<String, Object> paramMap);
  
  String removeContent(Map<String, Object> paramMap) throws DataAccessException;
  
  String findTestOne(Map<String, Object> paramMap);
  
  String findContentOne(Map<String, Object> paramMap);
  
  String getTestList(Map<String, Object> paramMap);
  
  String getTitleList(Map<String, Object> paramMap) throws DataAccessException;
  
  String getReplyList(Map<String, Object> paramMap);
  
  String getUserAnserList(Map<String, Object> paramMap);
  
  String getResultDetail(Map<String, Object> paramMap) throws DataAccessException;
  
  String removeTest(Map<String, Object> paramMap) throws DataAccessException;
  
  String removeTitle(Map<String, Object> paramMap) throws DataAccessException;
  
  String importWord(List<String> paramList1, List<String> paramList2, Integer paramInteger);
  
  void addWord(MainTest paramMainTest, List<TestTitle> paramList);
  
  void updateTitle(TestTitle paramTestTitle);
  
  TestTitle addTitle(TestTitle paramTestTitle);
  
  void updateWord(MainTest paramMainTest);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\StudyService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */