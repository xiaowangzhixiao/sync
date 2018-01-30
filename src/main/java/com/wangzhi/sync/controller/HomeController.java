package com.wangzhi.sync.controller;

import com.wangzhi.sync.domain.InfoDO;
import com.wangzhi.sync.manager.InfoManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class HomeController {
    @RequestMapping(value = "home")
    public static ModelAndView home() {
        Map param = new TreeMap();
        List<InfoDO> infoDOList = InfoManager.getInfos();
        param.put("infos",infoDOList);
        return new ModelAndView("home/home",param);
    }
}
