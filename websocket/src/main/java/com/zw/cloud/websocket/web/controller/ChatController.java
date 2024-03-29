package com.zw.cloud.websocket.web.controller;

import com.zw.cloud.websocket.server.endpoint.OneToManyWebSocket;
import com.zw.cloud.websocket.web.entity.chat.UserInfo;
import com.zw.cloud.websocket.web.entity.chat.UserVo;
import com.zw.cloud.websocket.web.service.api.chat.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.*;

@RestController
public class ChatController {

	@Autowired
	IUserInfoService userServices;
	

	@RequestMapping("/onlineUsers")
	public Set<String> onlineUsers(@RequestParam("currentUser") String currentUser) {
		Map<String, Session> map = OneToManyWebSocket.getClients();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		Set<String> nameset = new HashSet<String>();
		while (it.hasNext()) {
			String entry = it.next();
			if (!entry.equals(currentUser)) {
				nameset.add(entry);
			}
		}
		return nameset;
	}


	@RequestMapping("/getuid")
	public UserVo getuid(@RequestParam("username") String username) {
		return userServices.getUserByUsername(username);
	}

	@GetMapping(value="/currentUser/{uid}")
	public UserVo currentUser(@PathVariable("uid") Long uid){
		if (Objects.isNull(uid)) {
			return null;
		}
		UserInfo userInfo = userServices.getById(uid);
		if (Objects.isNull(userInfo)) {
			return null;
		}
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(userInfo,userVo);
		return userVo;
	}
}
