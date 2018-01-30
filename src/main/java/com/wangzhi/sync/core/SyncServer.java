package com.wangzhi.sync.core;

import com.wangzhi.sync.domain.InfoDO;
import com.wangzhi.sync.manager.InfoManager;
import com.wangzhi.sync.util.Mount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class SyncServer extends HttpServlet {

    private static List<InfoDO> infoDOList;
    private static List<Timer> timers;
    private static Logger logger = LoggerFactory.getLogger(SyncServer.class);

    private void start() {
        infoDOList = InfoManager.getInfos();
        logger.info("同步服务启动");
        for (InfoDO infoDO:infoDOList) {
            Mount.mount(infoDO.getSource(),infoDO.getUserName(),infoDO.getPassword());
            Timer timer = new Timer();
            String sourceDir = infoDO.getSource().substring(1);
            sourceDir = String.format("/mnt%s", sourceDir);
            timer.schedule(new Sync(sourceDir,infoDO.getTarget()),0,infoDO.getIntervals()*60000);
            timers.add(timer);
            logger.info("source:"+ infoDO.getSource() +" target:"+ infoDO.getTarget() + " interval:"+infoDO.getIntervals());
        }
    }

    private void stop() {
        logger.info("stop sync");
        for (Timer timer:timers) {
            timer.cancel();
        }
        for (InfoDO infoDO:infoDOList) {
            Mount.umount(infoDO.getSource());
        }
    }

    public void restart() {
        stop();
        timers.clear();
        start();
    }

    @Override
    public void init() throws ServletException {
        timers = new ArrayList<>();
        start();
        super.init();
    }

    @Override
    public void destroy() {
        stop();
        super.destroy();
    }
}
