package com.wangzhi.sync.controller;

import java.util.*;

import com.wangzhi.sync.core.SyncServer;
import com.wangzhi.sync.manager.InfoManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SyncController {

    @RequestMapping(value = "restart")
    @ResponseBody
    public static Map restart(SyncServer syncServer) {
        Map result = new HashMap();
        syncServer.restart();
        result.put("restart","success");
        return result;
    }

    @RequestMapping(value = "add")
    public static ModelAndView add(@RequestParam("source") String source,
                                   @RequestParam("userName") String userName,
                                   @RequestParam("password") String password,
                                   @RequestParam("target") String target,
                                   @RequestParam("intervals") int intervals) {
        InfoManager.addInfo(source,target,intervals,userName,password);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "delete")
    public static ModelAndView delete(@RequestParam("id") int id) {
        InfoManager.deleteInfo(id);
        return new ModelAndView("redirect:/home");
    }
}
