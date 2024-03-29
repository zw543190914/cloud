# springcloudAlibaba 案例
    springboot:2.6.7 spring-cloud:2021.0.3 springcloudAlibaba:2021.1
  1. 整合 springcloudAlibaba 组件。</br>
  2. 使用 nacos 作为 注册中心和配置中心，代替 Eureka和Config。参考 gateway 模块 </br>
  3. 使用 gateway 作为网关。 参考 gateway 模块 </br>
  4. 整合 dubbo 作为内部rpc调用，序列化使用 kryo,duboo整合sentinel熔断 。参考 dubbo相关模块 </br>
  5. 使用 feign 进行 http 远程调用,符合开发时接口调用规范。参考 sentinel + feign 相关模块 </br>
  6. 分布式事务 强一致性使用 Seata , 弱一致性使用 MQ 处理分布式事务。案例提供基于 AT模式和TCC模式事务处理，参考 seata 相关模块 </br>
  7. 熔断、降级、限流 使用 Sentinel 代替 Hystrix 更加灵活，代码侵入性更低。参考 sentinel 相关模块 </br>
  8. 分布式链路追踪 Zipkin ,参考 feign 相关模块。 </br>
  9. 分布式链路监控与追踪系统使用 Zipkin。参考 feign-provider </br>
  10. 分布式任务调度使用 xxl-job(客户端)，xxl-job-admin 为服务端。参考xxl-job，xxl-job-admin 模块 </br>
  11. sso单点登录使用 xxl-sso(客户端)，xxl-sso-server 为服务端。参考xxl-sso,xxl-sso-server 模块 </br>
  12. 分布式锁 zookeeper(ZkInterProcessMutex,LeaderLatch)。参考 feign-provider 模块。
  13. activiti工作流引擎使用整合。参考 activiti 模块。  </br>
  14. 多数据源整合, 参考 feign-consumer 模块。[mybatis-plus多数据源](https://www.yuque.com/zhengwei-tl3g2/zl9d8x/cp0yup) </br>
  15. influxDB 整合。</br>
  16. 整合 shiro 进行权限控制，缓存使用 ehcache(单机) 或者 redis(微服务) 。参考 shiro 模块 </br>
  17. elasticsearch 整合，工具类封装，数据来源为Jsoup获取网页数据。 参考 elasticsearch 模块。 </br>
  18. shop 商品模块，主要添加秒杀相关。
  19. mybatis-plus整合,sharding-jdbc分表,mybatis-plus generate 代码生成,事务传播机制&MVCC测试，参考 mybatis-plus 模块。</br>
  20. websocket 、 netty(netty-server)、 netty-client, 参考对应模块。</br>
  21. redis 分布式锁，幂等控制，参考 netty(netty-server) 模块。</br>
  22. MQ整合 rocketMq、rabbitMq、kafka. rocketMQ 使用策略模式进行消息消费</br>
  23. 统一日志处理以及TRACE_ID生成和传递 参考 logging 模块。    
  24. 长轮询实现 </br>
      poi excel 导出(百万数据使用 SXSSF),数据读取(百万数据量 使用事件模型进行读取) </br>
      小图片data url 数据库存储, 大文件 七牛云对象存储 </br>
      二维码生成和解析 </br>
      使用 百度AI / 阿里云AI 进行人脸识别 登录。(百度AI 被注释) </br>
      返回值统一封装,全局异常处理, Mybatis 代码生成插件 </br>
      Java简单爬虫 WebmagicUtils,StringTemplete 消息模板  </br>
      并发相关内容 锁，JUC工具类 线程池 </br>
      SPRINGBOOT单元测试 & MOCK静态方法 </br>
      参考 tools 模块 </br>
  25. 一些开发常用工具，比如 HttpUtils ，JwtUtils </br>
      整合使用IK分词器(自定义分词)或者 HanLP 分词进行文本相似度分析 </br>
      BigDecimalUtils 对数字计算和统计  </br>
      DateTimeUtils 日期工具  </br>
      使用 Pinyin4jUtils 进行 中文转拼音的相关处理， 参考 common 模块。 </br>
  26. IM即时通讯 后端代码 netty 模块，[uni-app 代码](https://gitee.com/moon-in-the-mirror/chat)   
  27. [常用插件](https://blog.csdn.net/weixin_41846320/article/details/82697818)
  
  父POM中配置了自己nexus私服，已注释，settings-nexus.xml 为自己私服对应配置文件，方便后期查找使用。</br> 
  
  后续更新在[gitee](https://gitee.com/moon-in-the-mirror/cloud)
