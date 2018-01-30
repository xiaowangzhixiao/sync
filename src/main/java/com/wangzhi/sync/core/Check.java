package com.wangzhi.sync.core;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Check extends TimerTask implements FileMonitor {

    public Map getFiles() {
        return files;
    }

    private Map files;
    private Map exist;
    private String source;
    private Logger logger = LoggerFactory.getLogger(Check.class);

    public String getSource() {
        return source;
    }



    @SuppressWarnings("unchecked")
    public Check(String sourcePath) {
        source = sourcePath;
        files = new HashMap<String,Long>();
        exist = new HashMap<String,Boolean>();
        Vector<String> allFile = new Vector<>();
        try {
            getFiles(allFile,sourcePath,"");
            for(String filename:allFile){
                String fp = sourcePath+"/"+filename;
                fp = fp.replaceAll("//", "/");
                File file = new File(fp);
                files.put(filename, file.lastModified());
                exist.put(filename,false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 递归获取目录下的所有文件 */
    public static void getFiles(Vector<String> ret, String fpath,String relativePath) throws IOException {
        File dir = new File(fpath);
        File[] files = dir.listFiles();

        if (files == null)
            return;
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(ret, file.getAbsolutePath(),relativePath+file.getName()+'/');
            } else {
                String fileName = file.getName();
                ret.addElement(relativePath + fileName);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run() {
        logger.info("start check" + source);
        Vector<String> allFile = new Vector<>();
        try {
            getFiles(allFile,source,"");

            for (String filename:allFile) {
                Long lastModified = (Long) files.get(filename);
                String fp = source+"/"+filename;
                fp = fp.replaceAll("//", "/");
                File file = new File(fp);
                if (lastModified == null) {
                    //文件增加
                    addAction(filename);

                    files.put(filename, file.lastModified());
                    exist.put(filename,true);
                } else {
                    if (file.lastModified() != lastModified) {
                        //文件更新
                        updateAction(filename);

                        files.put(filename, file.lastModified());
                        exist.put(filename,true);
                    } else {
                        //文件未动
                        exist.put(filename,true);
                    }

                }
            }

            Vector<String> deletedFiles = new Vector<>();
            Set<Map.Entry> set = exist.entrySet();
            for (Map.Entry entry : set) {
                if (!((Boolean) entry.getValue())) {
                    deletedFiles.add((String) entry.getKey());
                }
            }

            for (String filename :deletedFiles) {
                //文件删除
                deleteAction(filename);

                files.remove(filename);
                exist.remove(filename);
            }

            set = exist.entrySet();
            for (Map.Entry entry : set) {
                entry.setValue(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
