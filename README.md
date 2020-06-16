# cloud
整合 springcloudAlibaba 组件
使用 nacos 作为 注册中心和配置中心，代替 Eureka和Config
使用 zuul 作为网关，只是简单事例，工作中使用的是 nginx
使用 feign 进行 http 远程调用，也可以选择 dubbo 进行 rpc 调用，整合 dubbo 调用事例 
分布式事务 强一致性使用 Seata 性能会较低, 弱一致性使用 MQ 处理分布式事务
熔断、降级、限流 使用 Sentinel 代替 Hystrix 更加灵活，代码侵入性更低
分布式锁 使用 Zookeeper 实现。
分布式任务调度使用 xxl-job
分布式链路监控与追踪系统使用 Zipkin
