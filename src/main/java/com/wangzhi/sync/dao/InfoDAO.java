package com.wangzhi.sync.dao;

import com.free4lab.utils.sql.IEntityManagerHelper;
import com.free4lab.utils.sql.entitymanager.NoCacheEntityManagerHelper;
import com.wangzhi.sync.dao.base.BaseDAO;
import com.wangzhi.sync.domain.InfoDO;

public class InfoDAO extends BaseDAO {
    public Class getEntityClass() {
        return InfoDO.class;
    }

    public IEntityManagerHelper getEntityManagerHelper() {
        return new NoCacheEntityManagerHelper();
    }
}
