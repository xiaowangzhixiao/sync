package com.wangzhi.sync.core;

public interface FileMonitor {
    /** 文件删除 */
    void deleteAction(String filePath);
    /** 文件增加 */
    void addAction(String filePath);
    /** 文件更新 */
    void updateAction(String filePath);
}
