//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.ssms.service;

import cn.ssms.dao.ConfigureMapper;
import cn.ssms.dao.FileMapper;
import cn.ssms.dao.ResourcesMapper;
import cn.ssms.dao.TResourceSyncMapper;
import cn.ssms.model.Configure;
import cn.ssms.model.File;
import cn.ssms.model.Resources;
import cn.ssms.model.TResourceSync;
import cn.ssms.util.PageHelper;
import cn.ssms.util.Result;
import cn.ssms.util.ValidateTools;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ConfigureMapper configureMapper;
    @Autowired
    private ResourcesMapper resourcesMapper;
    @Autowired
    private TResourceSyncMapper tResourceSyncMapper;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    @Autowired
    private TResourceSyncMapper resourceSyncMapper;
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String videoSync = ResourceServiceImpl.class.getSimpleName() + ".videoSync";
    private static final String audioSync = ResourceServiceImpl.class.getSimpleName() + ".audioSync";
    private static final String gameSync = ResourceServiceImpl.class.getSimpleName() + ".gameSync";
    private static final String bookSync = ResourceServiceImpl.class.getSimpleName() + ".bookSync";
    @Value("#{commonProperties['videoPath']}")
    private String videoPath;
    @Value("#{commonProperties['audioPath']}")
    private String audioPath;
    @Value("#{commonProperties['bookPath']}")
    private String bookPath;
    @Value("#{commonProperties['gamePath']}")
    private String gamePath;
    @Value("#{commonProperties['attachment']}")
    private String attachment;
    private static final String PREFIX_VIDEO = "video/";
    private static final String PREFIX_IMAGE = "image/";

    public ResourceServiceImpl() {
    }

    public String getResourceList(Map<String, Object> map) {
        try {
            map.put("state", 1);
            List<File> list = this.fileMapper.getResourceList(map);
            map.put("count", "1");
            List<File> total = this.fileMapper.getResourceList(map);
            Integer totalCount = ((File)total.get(0)).getCount();
            Gson gson = new Gson();
            String path = "getResourceList.html?page=";
            PageHelper result = new PageHelper(totalCount, Integer.valueOf(map.get("pageSize").toString()), Integer.valueOf(map.get("index").toString()), gson.toJson(list), path);
            return gson.toJson(result);
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public String addResource(Map<String, Object> map) {
        String image = (String)map.get("image");
        String icon = "";
        if (image != null && image != "") {
            String[] b = image.split(";base64,");
            String[] img = b[0].split("/");
            String header = "data:image/" + img[1] + ";base64,";
            image = image.substring(header.length());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String id = sdf.format(date);
            String filename = "picture/RES" + id + "." + img[1];
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] decodedBytes = decoder.decodeBuffer(image);
                String imgFilePath = (String)map.get("path") + filename;
                FileOutputStream out = new FileOutputStream(imgFilePath);
                out.write(decodedBytes);
                out.close();
            } catch (Exception var16) {
                var16.printStackTrace();
            }

            icon = filename;
        }

        map.put("image", icon);
        map.put("playCount", 0);
        map.put("state", 1);
        String resources1 = (String)map.get("resources");
        String s = StringEscapeUtils.unescapeHtml(resources1);
        List<File> files = JSON.parseArray(s + "", File.class);
        Iterator i$ = files.iterator();

        while(i$.hasNext()) {
            File file = (File)i$.next();
            map.put("name", file.getName());
            this.fileMapper.addResource(map);
            Integer fileId = Integer.parseInt(map.get("id") + "");
            map.remove("id");
            List<Resources> resources = file.getResources();
            Integer i = 1;
             i$ = resources.iterator();

            while(i$.hasNext()) {
                Resources resource = (Resources)i$.next();
                resource.setFileId(fileId);
                Integer var28 = i;
                i = i + 1;
                resource.setSets(var28);
                resource.setState(1);
                this.resourcesMapper.addResource(resource);
            }
        }

        Map<String, Object> result = new HashMap();
        result.put("flag", true);
        result.put("message", " 新增成功");
        return (new Gson()).toJson(result);
    }

    public String udpResource(Map<String, Object> map) {
        String image = (String)map.get("image");
        String icon = "";
        if (image != null && image != "") {
            String[] b = image.split(";base64,");
            String[] img = b[0].split("/");
            String header = "data:image/" + img[1] + ";base64,";
            image = image.substring(header.length());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String id = sdf.format(date);
            String filename = "picture/logo" + id + "." + img[1];
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] decodedBytes = decoder.decodeBuffer(image);
                String imgFilePath = (String)map.get("path") + filename;
                FileOutputStream out = new FileOutputStream(imgFilePath);
                out.write(decodedBytes);
                out.close();
            } catch (Exception var15) {
                var15.printStackTrace();
            }

            map.put("image", filename);
            this.fileMapper.updateResource(map);
            Map<String, Object> result = new HashMap();
            result.put("flag", true);
            result.put("message", " 修改成功");
            return (new Gson()).toJson(result);
        } else {
            Map<String, Object> result = new HashMap();
            result.put("flag", false);
            result.put("message", " 修改失败，请重新选择小于1MB的图片上传");
            return (new Gson()).toJson(result);
        }
    }

    public File getResourceById(Map<String, Object> params) {
        String idStr = (String)params.get("id");
        Integer id = Integer.valueOf(idStr);
        File file = this.fileMapper.getResourceById(id);
        return file;
    }

    public List<Configure> getConfigByType(Integer type) {
        return this.configureMapper.getConfigByType(type);
    }

    public List<Resources> getResources(Map<String, Object> params) {
        return this.resourcesMapper.getResources(params);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public String removeResource(Map<String, Object> params) {
        String[] ids = (params.get("ids") + "").split(",");
        Map<String, Object> map = new HashMap();
        map.put("state", 0);
        String[] arr$ = ids;
        int len$ = ids.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String id = arr$[i$];
            map.put("id", id);
            this.fileMapper.deleteByPrimaryKey(map);
            this.resourcesMapper.updateResource(map);
        }

        Map<String, Object> result = new HashMap();
        result.put("flag", true);
        result.put("message", " 删除成功");
        return (new Gson()).toJson(result);
    }

    @Transactional
    public Result syncVideoResource() {
        synchronized(videoSync) {
            List<Configure> category = this.getConfigByType(1);
            return this.syncVideo("视频", category);
        }
    }

    @Transactional
    public Result syncAudioResource() {
        synchronized(audioSync) {
            List<Configure> category = this.getConfigByType(7);
            return this.syncOther("音乐", category, 2);
        }
    }

    @Transactional
    public Result syncGameResource() {
        synchronized(gameSync) {
            List<Configure> category = this.getConfigByType(9);
            return this.syncOther("游戏", category, 4);
        }
    }

    @Transactional
    public Result syncBookResource() {
        synchronized(bookSync) {
            List<Configure> category = this.getConfigByType(6);
            return this.syncOther("书籍", category, 3);
        }
    }

    public List<File> getFileByConfigId(Map<String, Object> map) {
        return this.fileMapper.getFileByConfigId(map);
    }

    private Result syncVideo(String dir, List<Configure> category) {
        if (category.isEmpty()) {
            return Result.isFail().setMessage("不存在'" + dir + "'分类信息");
        } else {
            List<String> categoryName = new ArrayList();
            Iterator i$ = category.iterator();

            while(i$.hasNext()) {
                Configure c = (Configure)i$.next();
                log.debug(c.getName());
                categoryName.add(c.getName());
            }

            String videoRootPath = this.attachment + java.io.File.separator;
            java.io.File videoFile = new java.io.File(videoRootPath + dir);
            if (videoFile.exists() && videoFile.isDirectory()) {
                java.io.File[] videoTypeDirs = videoFile.listFiles(new FileFilter() {
                    public boolean accept(java.io.File pathname) {
                        return pathname.isDirectory();
                    }
                });
                if (ArrayUtils.isEmpty(videoTypeDirs)) {
                    return Result.isFail().setMessage(dir + "目录下分类文件夹为空");
                } else {
                    List<String> dirsName = new ArrayList();
                    java.io.File[] arr$ = videoTypeDirs;
                    int len$ = videoTypeDirs.length;

                    for(int i = 0; i < len$; ++i) {
                        java.io.File f = arr$[i];
                        log.debug(f.getName());
                        dirsName.add(f.getName());
                    }

                    dirsName.removeAll(categoryName);
                    log.info("dirsName:" + JSON.toJSONString(dirsName));
                    if (!dirsName.isEmpty()) {
                        return Result.isFail().setMessage("未存在" + JSON.toJSONString(dirsName) + "分类,请先添加");
                    } else {
                        List<Resources> resourcesList = new ArrayList();
                        List<TResourceSync> resourceSyncs = new ArrayList();
                        List<File> update = new ArrayList();
                        List<File> insert = new ArrayList();
                        Map<String, Object> deleteCIds = new HashMap(4);
                        List<File> deleteFiles = new ArrayList();
                        Map<Integer, List<String>> deleteFile = new HashMap();
                         i$ = category.iterator();

                        while(true) {
                            while(i$.hasNext()) {
                                Configure c = (Configure)i$.next();
                                String typeName = c.getName();
                                List<String> fileNames = new ArrayList();
                                deleteFile.put(c.getId(), fileNames);
                                java.io.File[] typeNameFiles = (new java.io.File(videoRootPath + dir + java.io.File.separator + typeName)).listFiles(new FileFilter() {
                                    public boolean accept(java.io.File pathname) {
                                        return pathname.isDirectory();
                                    }
                                });
                                java.io.File[] xlsFiles = (new java.io.File(videoRootPath + dir + java.io.File.separator + typeName)).listFiles(new FileFilter() {
                                    public boolean accept(java.io.File pathname) {
                                        String name = pathname.getName();
                                        return !pathname.isDirectory() && "详情模板.xls".equals(name);
                                    }
                                });
                                if (!ArrayUtils.isEmpty(typeNameFiles)) {
                                    Map<String, File> xlsData = this.readXlsFile(xlsFiles);
                                    boolean xls = false;
                                    if (xlsData != null && xlsData.size() > 0) {
                                        java.io.File xlsFile = xlsFiles[0];
                                        TResourceSync resourceSync = this.tResourceSyncMapper.selectOneBySyncPath(xlsFile.getPath().replace(videoRootPath, ""));
                                        if (resourceSync == null || (new Date(xlsFile.lastModified())).getTime() - resourceSync.getSyncTime().getTime() >= 1000L) {
                                            TResourceSync resourceSyncXls = new TResourceSync();
                                            resourceSyncXls.setCreateTime(new Date());
                                            resourceSyncXls.setSyncPath(xlsFile.getPath().replace(videoRootPath, ""));
                                            resourceSyncXls.setSyncTime(new Date(xlsFile.lastModified()));
                                            resourceSyncs.add(resourceSyncXls);
                                            xls = true;
                                        }
                                    }

                                     arr$ = typeNameFiles;
                                     len$ = typeNameFiles.length;

                                    for(int i = 0; i < len$; ++i) {
                                        java.io.File f = arr$[i];
                                        fileNames.add(f.getName());
                                        String cover = "";
                                        String description = "";
                                        String auth = "";
                                        String person = "1";
                                        TResourceSync resourceSync = this.tResourceSyncMapper.selectOneBySyncPath(f.getPath().replace(videoRootPath, ""));
                                        if (resourceSync != null && (new Date(f.lastModified())).getTime() - resourceSync.getSyncTime().getTime() < 1000L && !xls) {
                                            log.warn(String.format("不需要更新:%s,文件最后修改时间:%s,同步时间:%s", f.getName(), df.format(new Date(f.lastModified())), df.format(resourceSync.getSyncTime())));
                                        } else {
                                            File file = this.fileMapper.getFileByName(f.getName(), 1, String.valueOf(c.getId()));
                                            java.io.File[] videos = f.listFiles(new FileFilter() {
                                                public boolean accept(java.io.File pathname) {
                                                    return ResourceServiceImpl.isVedioFile(pathname);
                                                }
                                            });
                                            if (videos.length == 0) {
                                                if (file != null) {
                                                    deleteFiles.add(file);
                                                }
                                            } else {
                                                java.io.File[] coverFiles = f.listFiles(new FileFilter() {
                                                    public boolean accept(java.io.File pathname) {
                                                        return ResourceServiceImpl.isImageFile(pathname);
                                                    }
                                                });

                                                try {
                                                    if (xlsData != null && xlsData.size() > 0) {
                                                        File fileXls = (File)xlsData.get(f.getName());
                                                        if (fileXls != null) {
                                                            auth = fileXls.getAuthor();
                                                            description = fileXls.getIntroduction();
                                                        }
                                                    }
                                                } catch (Exception var42) {
                                                    log.error(c.getName() + "目录下的详情模板文件异常", var42);
                                                    return Result.isFail().setMessage(c.getName() + "目录下的详情模板文件异常");
                                                }

                                                if (description.length() > 500) {
                                                    return Result.isFail().setMessage("简介应该不大于500个字符，请检查" + c.getName() + "目录下的详情模板");
                                                }

                                                if (auth.length() > 10) {
                                                    return Result.isFail().setMessage("导演应该不大于10个字符，请检查" + c.getName() + "目录下的详情模板");
                                                }

                                                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                                                person = request.getSession().getAttribute("id").toString();
                                                String path = request.getSession().getServletContext().getRealPath("/");

                                               // int i;
                                                try {
                                                    if (coverFiles != null && coverFiles.length > 0) {
                                                        if (coverFiles.length > 1) {
                                                            Arrays.sort(coverFiles, new Comparator<java.io.File>() {
                                                                public int compare(java.io.File f1, java.io.File f2) {
                                                                    long diff = f1.lastModified() - f2.lastModified();
                                                                    if (diff > 0L) {
                                                                        return -1;
                                                                    } else {
                                                                        return diff == 0L ? 0 : 1;
                                                                    }
                                                                }

                                                                public boolean equals(Object obj) {
                                                                    return true;
                                                                }
                                                            });
                                                        }

                                                        java.io.File coverFile = coverFiles[0];

                                                        for(i = 0; i < coverFiles.length; ++i) {
                                                            String name = coverFiles[i].getName();
                                                            if (name.substring(0, name.lastIndexOf(".")).equals(f.getName())) {
                                                                coverFile = coverFiles[i];
                                                                break;
                                                            }
                                                        }

                                                        String substring = coverFile.getName().substring(coverFile.getName().lastIndexOf("."));
                                                        cover = "picture/video" + UUID.randomUUID().toString().replace("-", "") + substring;
                                                        Thumbnails.of(new java.io.File[]{coverFiles[0]}).size(150, 150).outputQuality(1.0F).toFile(path + cover);
                                                    }
                                                } catch (IOException var41) {
                                                    try {
                                                        FileUtils.copyFile(coverFiles[0], new java.io.File(path + cover));
                                                    } catch (IOException var40) {
                                                        log.error(c.getName() + "图片文件异常", var41);
                                                        return Result.isFail().setMessage(c.getName() + "图片文件异常");
                                                    }
                                                }

                                                if (file != null) {
                                                    file.setImage(cover);
                                                    file.setAuthor(auth);
                                                    file.setAddPerson(Integer.parseInt(person));
                                                    file.setIntroduction(description);
                                                    update.add(file);
                                                } else {
                                                    file = new File();
                                                    file.setPlayCount(0);
                                                    file.setName(f.getName());
                                                    file.setFileType(1);
                                                    file.setState(1);
                                                    file.setCategory(c.getId());
                                                    file.setAddDate(sdf.format(new Date()));
                                                    file.setImage(cover);
                                                    file.setAuthor(auth);
                                                    file.setAddPerson(Integer.parseInt(person));
                                                    file.setIntroduction(description);
                                                    insert.add(file);
                                                }

                                                if (null != videos && videos.length > 0) {
                                                    String[] urls = new String[videos.length];

                                                    for(i = 0; i < videos.length; ++i) {
                                                        if (isVedioFile(videos[i])) {
                                                            urls[i] = videos[i].getPath().replace(videoRootPath, "");
                                                        }
                                                    }

                                                    file.setUrls(urls);
                                                }

                                                resourceSync = new TResourceSync();
                                                resourceSync.setCreateTime(new Date());
                                                resourceSync.setSyncPath(f.getPath().replace(videoRootPath, ""));
                                                resourceSync.setSyncTime(new Date(f.lastModified()));
                                                resourceSyncs.add(resourceSync);
                                            }
                                        }
                                    }
                                } else {
                                    deleteCIds.put("configId", c.getId());
                                }
                            }

                            if (deleteCIds.size() > 0) {
                                List<File> fileByConfigId = this.fileMapper.getFileByConfigId(deleteCIds);
                                if (fileByConfigId.size() > 0) {
                                    for(int i = 0; i < fileByConfigId.size(); ++i) {
                                        this.fileMapper.deleteByFileId(((File)fileByConfigId.get(i)).getId());
                                        this.fileMapper.deleteByResourceByFileId(((File)fileByConfigId.get(i)).getId());
                                    }
                                }
                            }

                            List<File> list = new ArrayList();
                             i$ = deleteFile.keySet().iterator();

                            while(i$.hasNext()) {
                                Integer key = (Integer)i$.next();
                                List<String> strings = (List)deleteFile.get(key);
                                if (strings.size() > 0) {
                                    List<File> notExistFile = this.fileMapper.getNotExistFile(strings, key);
                                    list.addAll(notExistFile);
                                }
                            }

                            File file;
                            if (list.size() > 0) {
                                i$ = list.iterator();

                                while(i$.hasNext()) {
                                    file = (File)i$.next();
                                    this.fileMapper.deleteByFileId(file.getId());
                                    this.fileMapper.deleteByResourceByFileId(file.getId());
                                }
                            }

                            if (deleteFiles.size() > 0) {
                                i$ = deleteFiles.iterator();

                                while(i$.hasNext()) {
                                    file = (File)i$.next();
                                    this.fileMapper.deleteByFileId(file.getId());
                                }
                            }

                            int i;
                            Resources resources;
                            if (update.size() > 0) {
                                i$ = update.iterator();

                                label190:
                                while(true) {
                                    do {
                                        do {
                                            if (!i$.hasNext()) {
                                                break label190;
                                            }

                                            file = (File)i$.next();
                                            this.resourcesMapper.deleteByFileId(file.getId());
                                            this.fileMapper.update(file);
                                        } while(file.getUrls() == null);
                                    } while(file.getUrls().length <= 0);

                                    this.sort(file.getUrls());

                                    for(i = 0; i < file.getUrls().length; ++i) {
                                        resources = new Resources();
                                        resources.setState(1);
                                        resources.setFileId(file.getId());
                                        resources.setSets(i + 1);
                                        resources.setUrl(file.getUrls()[i]);
                                        resourcesList.add(resources);
                                    }
                                }
                            }

                            if (insert.size() > 0) {
                                i$ = insert.iterator();

                                label170:
                                while(true) {
                                    do {
                                        do {
                                            if (!i$.hasNext()) {
                                                break label170;
                                            }

                                            file = (File)i$.next();
                                            this.fileMapper.insert(file);
                                        } while(file.getUrls() == null);
                                    } while(file.getUrls().length <= 0);

                                    this.sort(file.getUrls());

                                    for(i = 0; i < file.getUrls().length; ++i) {
                                        resources = new Resources();
                                        resources.setState(1);
                                        resources.setFileId(file.getId());
                                        resources.setSets(i + 1);
                                        resources.setUrl(file.getUrls()[i]);
                                        resourcesList.add(resources);
                                    }
                                }
                            }

                            if (resourcesList != null && !resourcesList.isEmpty()) {
                                this.resourcesMapper.insertBatch(resourcesList);
                            }

                            if (resourceSyncs.size() > 0) {
                                i$ = resourceSyncs.iterator();

                                while(i$.hasNext()) {
                                    TResourceSync tResourceSync = (TResourceSync)i$.next();
                                    this.resourceSyncMapper.insert(tResourceSync);
                                }
                            }

                            return Result.isOk();
                        }
                    }
                }
            } else {
                return Result.isFail().setMessage("不存在'" + dir + "'目录");
            }
        }
    }

    private Map<String, File> readXlsFile(java.io.File[] xlsFiles) {
        if (xlsFiles != null && xlsFiles.length > 0) {
            java.io.File xlsFile = xlsFiles[0];
            Sheet sheet = null;
            Row row = null;
            Row rowHeader = null;
            Map<String, File> map = null;
            String cellData = null;
            Workbook wb = null;
            FileInputStream is = null;

            try {
                is = new FileInputStream(xlsFile);
                wb = new HSSFWorkbook(is);
                if (wb != null) {
                    map = new LinkedHashMap();
                    sheet = wb.getSheetAt(0);
                    int rownum = sheet.getPhysicalNumberOfRows();
                    rowHeader = sheet.getRow(0);
                    row = sheet.getRow(0);
                    int colnum = row.getPhysicalNumberOfCells();

                    for(int i = 1; i < rownum; ++i) {
                        row = sheet.getRow(i);
                        if (row == null) {
                            break;
                        }

                        File file = new File();
                        if (colnum > 2) {
                            Cell cell1 = row.getCell(1);
                            if (cell1 != null) {
                                cell1.setCellType(1);
                                file.setAuthor(cell1.getStringCellValue());
                            } else {
                                file.setAuthor("");
                            }

                            Cell cell2 = row.getCell(2);
                            if (cell2 != null) {
                                cell2.setCellType(1);
                                file.setIntroduction(cell2.getStringCellValue());
                            } else {
                                file.setIntroduction("");
                            }

                            map.put(row.getCell(0).getStringCellValue(), file);
                        }
                    }
                }
            } catch (FileNotFoundException var16) {
                var16.printStackTrace();
            } catch (IOException var17) {
                var17.printStackTrace();
            }

            return map;
        } else {
            return null;
        }
    }

    private static String getMimeType(java.io.File f) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(f.getPath());
        return type;
    }

    public static boolean isVedioFile(java.io.File f) {
        String mimeType = getMimeType(f);
        if (StringUtils.isNotBlank(mimeType) && mimeType.contains("video/")) {
            return true;
        } else {
            return ValidateTools.Video(f.getName());
        }
    }

    public static boolean isImageFile(java.io.File f) {
        String mimeType = getMimeType(f);
        if (StringUtils.isNotBlank(mimeType) && mimeType.contains("image/")) {
            return true;
        } else {
            return ValidateTools.Picture(f.getName());
        }
    }

    private Result syncOther(String dir, List<Configure> category, final Integer fileType) {
        if (category.isEmpty()) {
            return Result.isFail().setMessage("不存在'" + dir + "'分类信息");
        } else {
            List<String> categoryName = new ArrayList();
            Iterator i$ = category.iterator();

            while(i$.hasNext()) {
                Configure c = (Configure)i$.next();
                log.debug(c.getName());
                categoryName.add(c.getName());
            }

            String videoRootPath = this.attachment + java.io.File.separator;
            java.io.File videoFile = new java.io.File(videoRootPath + dir);
            if (videoFile.exists() && videoFile.isDirectory()) {
                java.io.File[] videoTypeDirs = videoFile.listFiles(new FileFilter() {
                    public boolean accept(java.io.File pathname) {
                        return pathname.isDirectory();
                    }
                });
                if (ArrayUtils.isEmpty(videoTypeDirs)) {
                    return Result.isFail().setMessage(dir + "目录下分类文件夹为空");
                } else {
                    List<String> dirsName = new ArrayList();
                    java.io.File[] arr$ = videoTypeDirs;
                    int len$ = videoTypeDirs.length;

                    for(int i = 0; i < len$; ++i) {
                        java.io.File f = arr$[i];
                        log.debug(f.getName());
                        dirsName.add(f.getName());
                    }

                    dirsName.removeAll(categoryName);
                    log.info("dirsName:" + JSON.toJSONString(dirsName));
                    if (!dirsName.isEmpty()) {
                        return Result.isFail().setMessage("未存在" + JSON.toJSONString(dirsName) + "分类,请先添加");
                    } else {
                        new NameFileComparator(IOCase.INSENSITIVE);
                        List<Resources> resourcesList = new ArrayList();
                        List<TResourceSync> resourceSyncXlsList = new ArrayList();
                        List<File> updateFile = new ArrayList();
                        List<File> insertFile = new ArrayList();
                        Map<Integer, List<String>> deleteFile = new HashMap();
                         i$ = category.iterator();

                        while(true) {
                            while(true) {
                                Configure c;
                                ArrayList fileNames;
                                java.io.File file;
                                java.io.File[] typeNameFiles;
                                do {
                                    if (!i$.hasNext()) {
                                        List<File> list = new ArrayList();
                                         i$ = deleteFile.keySet().iterator();

                                        while(i$.hasNext()) {
                                            Integer key = (Integer)i$.next();
                                            List<String> strings = (List)deleteFile.get(key);
                                            if (strings.size() > 0) {
                                                List<File> notExistFile = this.fileMapper.getNotExistFile(strings, key);
                                                list.addAll(notExistFile);
                                            }
                                        }

                                        File f;
                                        if (list.size() > 0) {
                                            i$ = list.iterator();

                                            while(i$.hasNext()) {
                                                f = (File)i$.next();
                                                this.fileMapper.deleteByFileId(f.getId());
                                            }
                                        }

                                        if (resourceSyncXlsList.size() > 0) {
                                            i$ = resourceSyncXlsList.iterator();

                                            while(i$.hasNext()) {
                                                TResourceSync tResourceSync = (TResourceSync)i$.next();
                                                this.resourceSyncMapper.insert(tResourceSync);
                                            }
                                        }

                                        Resources resource;
                                        if (updateFile.size() > 0) {
                                            i$ = updateFile.iterator();

                                            while(i$.hasNext()) {
                                                f = (File)i$.next();
                                                this.resourcesMapper.deleteByFileId(f.getId());
                                                this.fileMapper.update(f);
                                                if (f.getUrls() != null) {
                                                    resource = new Resources();
                                                    resource.setState(1);
                                                    resource.setFileId(f.getId());
                                                    resource.setSets(1);
                                                    resource.setUrl(f.getUrls()[0]);
                                                    resourcesList.add(resource);
                                                }
                                            }
                                        }

                                        if (insertFile.size() > 0) {
                                            i$ = insertFile.iterator();

                                            while(i$.hasNext()) {
                                                f = (File)i$.next();
                                                this.fileMapper.insert(f);
                                                resource = new Resources();
                                                resource.setState(1);
                                                resource.setFileId(f.getId());
                                                resource.setSets(1);
                                                resource.setUrl(f.getUrls()[0]);
                                                resourcesList.add(resource);
                                            }
                                        }

                                        if (!resourcesList.isEmpty() && resourcesList.size() > 0) {
                                            this.resourcesMapper.insertBatch(resourcesList);
                                        }

                                        return Result.isOk();
                                    }

                                    c = (Configure)i$.next();
                                    fileNames = new ArrayList();
                                    deleteFile.put(c.getId(), fileNames);
                                    String typeName = c.getName();
                                    file = new java.io.File(videoRootPath + dir + java.io.File.separator + typeName);
                                    typeNameFiles = file.listFiles(new FileFilter() {
                                        public boolean accept(java.io.File pathname) {
                                            System.out.println(pathname.getName());
                                            return pathname.isFile() && (ResourceServiceImpl.this.validateFile(pathname, fileType) || ResourceServiceImpl.isImageFile(pathname)) && !"详情模板.xls".equals(pathname.getName());
                                        }
                                    });
                                } while(ArrayUtils.isEmpty(typeNameFiles));

                                java.io.File[] xlsFiles = file.listFiles(new FileFilter() {
                                    public boolean accept(java.io.File pathname) {
                                        String name = pathname.getName();
                                        return "详情模板.xls".equals(name);
                                    }
                                });
                                Map<String, File> xlsData = this.readXlsFile(xlsFiles);
                                boolean xls = false;
                                if (xlsData != null && xlsData.size() > 0) {
                                    java.io.File xlsFile = xlsFiles[0];
                                    TResourceSync resourceSync = this.tResourceSyncMapper.selectOneBySyncPath(xlsFile.getPath().replace(videoRootPath, ""));
                                    if (resourceSync == null || (new Date(xlsFile.lastModified())).getTime() - resourceSync.getSyncTime().getTime() >= 1000L) {
                                        TResourceSync resourceSyncXls = new TResourceSync();
                                        resourceSyncXls.setCreateTime(new Date());
                                        resourceSyncXls.setSyncPath(xlsFile.getPath().replace(videoRootPath, ""));
                                        resourceSyncXls.setSyncTime(new Date(xlsFile.lastModified()));
                                        resourceSyncXlsList.add(resourceSyncXls);
                                        xls = true;
                                    }
                                }

                                TResourceSync resourceSync = this.tResourceSyncMapper.selectOneBySyncPath(file.getPath().replace(videoRootPath, ""));
                                if (resourceSync != null && (new Date(file.lastModified())).getTime() - resourceSync.getSyncTime().getTime() < 1000L && !xls) {
                                    log.warn(String.format("不需要更新:%s,文件最后修改时间:%s,同步时间:%s", file.getName(), df.format(new Date(file.lastModified())), df.format(resourceSync.getSyncTime())));
                                } else {
                                     arr$ = typeNameFiles;
                                     len$ = typeNameFiles.length;

                                    for(int i = 0; i < len$; ++i) {
                                        java.io.File f = arr$[i];
                                        final String fName = f.getName().substring(0, f.getName().lastIndexOf("."));
                                        fileNames.add(fName);
                                        String cover = "";
                                        String description = "";
                                        String auth = "";
                                        String person = "1";
                                        java.io.File[] coverFiles = file.listFiles(new FileFilter() {
                                            public boolean accept(java.io.File pathname) {
                                                return ResourceServiceImpl.isImageFile(pathname) && pathname.getName().substring(0, pathname.getName().lastIndexOf(".")).equals(fName);
                                            }
                                        });
                                        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                                        person = request.getSession().getAttribute("id").toString();

                                        try {
                                            if (coverFiles != null && coverFiles.length > 0) {
                                                String path = request.getSession().getServletContext().getRealPath("/");
                                                String substring = coverFiles[0].getName().substring(coverFiles[0].getName().lastIndexOf("."));
                                                cover = "picture/video" + UUID.randomUUID().toString().replace("-", "") + substring;

                                                try {
                                                    Thumbnails.of(new java.io.File[]{coverFiles[0]}).size(150, 150).outputQuality(1.0F).toFile(path + cover);
                                                } catch (IOException var39) {
                                                    FileUtils.copyFile(coverFiles[0], new java.io.File(path + cover));
                                                }
                                            }
                                        } catch (Exception var40) {
                                            log.error(c.getName() + "图片文件异常", var40);
                                            return Result.isFail().setMessage(c.getName() + "图片文件异常");
                                        }

                                        File fileExists;
                                        try {
                                            if (xlsData != null && xlsData.size() > 0) {
                                                fileExists = (File)xlsData.get(fName);
                                                if (fileExists != null) {
                                                    auth = fileExists.getAuthor();
                                                    description = fileExists.getIntroduction();
                                                }
                                            }
                                        } catch (Exception var41) {
                                            log.error(c.getName() + "目录下的详情模板文件异常", var41);
                                            return Result.isFail().setMessage(c.getName() + "目录下的详情模板文件异常");
                                        }

                                        if (description.length() > 500) {
                                            return Result.isFail().setMessage("简介应该不大于500个字符，请检查" + c.getName() + "目录下的详情模板");
                                        }

                                        if (auth.length() > 10 && fileType == 2) {
                                            return Result.isFail().setMessage("歌手名称应该不大于10个字符，请检查" + c.getName() + "目录下的详情模板");
                                        }

                                        if (auth.length() > 10 && fileType == 3) {
                                            return Result.isFail().setMessage("作者名称应该不大于10个字符，请检查" + c.getName() + "目录下的详情模板");
                                        }

                                        if (auth.length() > 10 && fileType == 4) {
                                            return Result.isFail().setMessage("公司名称应该不大于10个字符，请检查" + c.getName() + "目录下的详情模板");
                                        }

                                        fileExists = this.fileMapper.getFileByName(fName, fileType, String.valueOf(c.getId()));
                                        if (fileExists != null) {
                                            fileExists.setImage(cover);
                                            fileExists.setAuthor(auth);
                                            fileExists.setCompany(auth);
                                            fileExists.setType(c.getId());
                                            fileExists.setCategory(c.getId());
                                            fileExists.setAddPerson(Integer.parseInt(person));
                                            fileExists.setIntroduction(description);
                                            if (!isImageFile(f)) {
                                                fileExists.setUrls(new String[]{f.getPath().replace(videoRootPath, "")});
                                            }

                                            updateFile.add(fileExists);
                                        } else if (!isImageFile(f)) {
                                            fileExists = new File();
                                            fileExists.setPlayCount(0);
                                            fileExists.setName(fName);
                                            fileExists.setFileType(fileType);
                                            fileExists.setState(1);
                                            fileExists.setImage(cover);
                                            fileExists.setCategory(c.getId());
                                            fileExists.setType(c.getId());
                                            fileExists.setCompany(auth);
                                            fileExists.setAddDate(sdf.format(new Date()));
                                            fileExists.setAuthor(auth);
                                            fileExists.setAddPerson(Integer.parseInt(person));
                                            fileExists.setIntroduction(description);
                                            fileExists.setUrls(new String[]{f.getPath().replace(videoRootPath, "")});
                                            insertFile.add(fileExists);
                                        }
                                    }

                                    resourceSync = new TResourceSync();
                                    resourceSync.setCreateTime(new Date());
                                    resourceSync.setSyncPath(file.getPath().replace(videoRootPath, ""));
                                    resourceSync.setSyncTime(new Date(file.lastModified()));
                                    resourceSyncXlsList.add(resourceSync);
                                }
                            }
                        }
                    }
                }
            } else {
                return Result.isFail().setMessage("不存在'" + dir + "'目录");
            }
        }
    }

    private boolean validateFile(java.io.File pathname, Integer fileType) {
        if ("2".equals(String.valueOf(fileType))) {
            return ValidateTools.Audio(pathname.getName());
        } else if ("3".equals(String.valueOf(fileType))) {
            return ValidateTools.Doc(pathname.getName());
        } else {
            return "4".equals(String.valueOf(fileType)) ? ValidateTools.Game(pathname.getName()) : false;
        }
    }

    private void sort(String[] urls) {
        Arrays.sort(urls, new Comparator<String>() {
            public int compare(String o1, String o2) {
                try {
                    o1 = o1.substring(0, o1.lastIndexOf("."));
                    o2 = o2.substring(0, o2.lastIndexOf("."));
                    Pattern pattern1 = Pattern.compile("\\d+$");
                    Matcher matcher1 = pattern1.matcher(o1);
                    Matcher matcher2 = pattern1.matcher(o2);
                    int i1 = 0;
                    int i2 = 0;
                    if (matcher1.find()) {
                        i1 = Integer.parseInt(matcher1.group());
                    }

                    if (matcher2.find()) {
                        i2 = Integer.parseInt(matcher2.group());
                    }

                    return i1 - i2;
                } catch (Exception var8) {
                    return -1;
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            java.io.File file = new java.io.File("D:\\Documents\\Tencent Files\\591167027\\FileRecv\\厉害了我的国.jpg");
            Thumbnails.of(new java.io.File[]{file}).size(150, 150).outputQuality(1.0F).toFile("D:\\Documents\\Tencent Files\\591167027\\FileRecv\\厉害了我的国_1.jpg");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}
