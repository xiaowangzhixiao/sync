package com.wangzhi.sync.manager;

import com.wangzhi.sync.dao.InfoDAO;
import com.wangzhi.sync.domain.InfoDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InfoManager {
    private static InfoDO infoDO = new InfoDO();
    private static InfoDAO infoDAO = new InfoDAO();
    private static Logger logger = LoggerFactory.getLogger(InfoManager.class);

    public static List<InfoDO> getInfos() {
        return infoDAO.findAll();
    }

    public static void deleteInfo(int id) {
        infoDAO.deleteByPrimaryKey(id);
    }

    public static void addInfo(String source,String target,int intervals,String userName,String password) {
        infoDO.setSource(source);
        infoDO.setTarget(target);
        infoDO.setIntervals(intervals);
        infoDO.setUserName(userName);
        infoDO.setPassword(password);
        logger.info("save info");
        infoDAO.save(infoDO);
    }

}
