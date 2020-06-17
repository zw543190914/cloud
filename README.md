# cloud
整合 springcloudAlibaba 组件。</br>
使用 nacos 作为 注册中心和配置中心，代替 Eureka和Config。 </br>
使用 zuul 作为网关，只是简单事例，工作中使用的是 nginx。 </br>
使用 dubbo 作为内部rpc调用，因为只要使用springcloud http 调用，所以只是整合了dubbo，做一个案例。 </br>
使用 feign 进行 http 远程调用,符合开发时接口调用规范。 </br>
分布式事务 强一致性使用 Seata 性能会较低, 弱一致性使用 MQ 处理分布式事务。 </br>
熔断、降级、限流 使用 Sentinel 代替 Hystrix 更加灵活，代码侵入性更低。 </br>
分布式锁 使用 Zookeeper 实现。 </br>
分布式任务调度使用 xxl-job。 </br>
分布式链路监控与追踪系统使用 Zipkin。 </br>
