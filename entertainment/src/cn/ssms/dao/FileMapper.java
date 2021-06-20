package cn.ssms.dao;

import cn.ssms.model.File;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface FileMapper {
  List<File> getResourceList(Map<String, Object> paramMap);
  
  void addResource(Map<String, Object> paramMap);
  
  void updateResource(Map<String, Object> paramMap);
  
  File getResourceById(Integer paramInteger);
  
  File getFileByName(@Param("name") String paramString1, @Param("fileType") Integer paramInteger, @Param("categoryId") String paramString2);
  
  Integer insert(File paramFile);
  
  Integer update(File paramFile);
  
  List<File> getFileByConfigId(Map<String, Object> paramMap);
  
  Integer deleteByPrimaryKey(Map<String, Object> paramMap);
  
  int deleteByCatId(int paramInt);
  
  List<File> getNotExistFile(@Param("fileNames") List<String> paramList, @Param("categoryId") int paramInt);
  
  int deleteByFileId(Integer paramInteger);
  
  int deleteByResourceByFileId(Integer paramInteger);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\FileMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */