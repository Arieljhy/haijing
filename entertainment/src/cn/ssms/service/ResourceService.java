package cn.ssms.service;

import cn.ssms.model.Configure;
import cn.ssms.model.File;
import cn.ssms.model.Resources;
import cn.ssms.util.Result;
import java.util.List;
import java.util.Map;

public interface ResourceService {
  String getResourceList(Map<String, Object> paramMap);
  
  String addResource(Map<String, Object> paramMap);
  
  String udpResource(Map<String, Object> paramMap);
  
  File getResourceById(Map<String, Object> paramMap);
  
  List<Configure> getConfigByType(Integer paramInteger);
  
  List<Resources> getResources(Map<String, Object> paramMap);
  
  String removeResource(Map<String, Object> paramMap);
  
  Result syncVideoResource();
  
  Result syncAudioResource();
  
  Result syncGameResource();
  
  Result syncBookResource();
  
  List<File> getFileByConfigId(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\ResourceService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */