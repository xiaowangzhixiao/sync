package com.wangzhi.sync.dao.base;


import com.free4lab.utils.sql.AbstractDAO;


public abstract class BaseDAO<T> extends AbstractDAO<T>{
    public String getPUName() {
        return "SYNC_PU";
    }
}
