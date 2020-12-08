package com.zw.cloud.shop.controller;

import com.zw.cloud.shop.domain.MiaoshaUser;
import com.zw.cloud.shop.domain.OrderInfo;
import com.zw.cloud.shop.redis.service.RedisService;
import com.zw.cloud.shop.result.CodeMsg;
import com.zw.cloud.shop.result.Result;
import com.zw.cloud.shop.service.GoodsService;
import com.zw.cloud.shop.service.MiaoshaUserService;
import com.zw.cloud.shop.service.OrderService;
import com.zw.cloud.shop.vo.GoodsVo;
import com.zw.cloud.shop.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
									  @RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
