package com.zw.cloud.websocket.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/webrtc")
@Controller
@Slf4j
public class PageController {

    /**
     * WebRTC + WebSocket
     */
    @RequestMapping("/{username}")
    //http://127.0.0.1:18092/webrtc/zw
    public ModelAndView socketChartPage(@PathVariable String username) {
        log.info("[PageController][socketChartPage]username is {}",username);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/oneToOneVideo.html");
        modelAndView.addObject("username",username);
        return modelAndView;
    }
}
