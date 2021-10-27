package com.zw.cloud.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {
	
	public static final String QUEUE = "queue";

	public static final String TOPIC_QUEUE1 = "topic.queue1";
	public static final String TOPIC_QUEUE2 = "topic.queue2";
	public static final String TOPIC_EXCHANGE = "topicExchage";

	public static final String FANOUT_QUEUE1 = "fanout.queue1";
	public static final String FANOUT_QUEUE2 = "fanout.queue2";
	public static final String FANOUT_EXCHANGE = "fanoutExchage";

	public static final String DELAY_QUEUE = "delay.queue";
	public static final String DELAY_EXCHANGE = "delayExchange";

	public static final String HEADERS_EXCHANGE = "headersExchage";
	public static final String HEADER_QUEUE = "header.queue";


	/**
	 * Direct模式 交换机Exchange
	 * */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}
	
	/**
	 * Topic模式 交换机Exchange
	 * */
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}
	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}
	@Bean
	public TopicExchange topicExchage(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with("topic.a");
	}
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with("topic.#");
	}
	/**
	 * Fanout模式 交换机Exchange
	 * */
	@Bean
	public Queue fanoutQueue1(){
		return new Queue(FANOUT_QUEUE1);
	}
	@Bean
	public Queue fanoutQueue2(){
		return new Queue(FANOUT_QUEUE2);
	}

	@Bean
	public FanoutExchange fanoutExchage(){
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchage());
	}
	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchage());
	}


	/**
	 * 声明延时Exchange
	 */
	@Bean
	public DirectExchange delayExchange() {
		return new DirectExchange(DELAY_EXCHANGE, true, false);
	}
	@Bean
	public Queue delayQueue() {
		Map<String, Object> map = new HashMap<>(16);
		//直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活
		//map.put("x-message-ttl", 5 * 1000);
		map.put("x-dead-letter-exchange", "delay.data.receive_exchange");
		map.put("x-dead-letter-routing-key", "delay.data.receive_key");
		return new Queue(DELAY_QUEUE, true, false, false, map);
	}

	/**
	 * 绑定exchange
	 */
	@Bean
	public Binding  delayBinding() {
		return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delay.key");
	}

	/**
	 * 延时队列接收 队列
	 */
	@Bean
	public Queue delayDataReceiveQueue() {
		return new Queue("delay.data.receive_queue");
	}

	@Bean
	public DirectExchange delayDataReceiveExchange() {
		return new DirectExchange("delay.data.receive_exchange");
	}
	@Bean
	public Binding delayDataReceiveBinding() {
		return BindingBuilder.bind(delayDataReceiveQueue()).to(delayDataReceiveExchange()).with("delay.data.receive_key");
	}

	/**
	 * Header模式 交换机Exchange
	 * */
	@Bean
	public HeadersExchange headersExchage(){
		return new HeadersExchange(HEADERS_EXCHANGE);
	}
	@Bean
	public Queue headerQueue1() {
		return new Queue(HEADER_QUEUE, true);
	}
	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
	}
	
	
}
