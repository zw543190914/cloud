# springcloudAlibaba-demo
  1.整合 springcloudAlibaba 组件。</br>
  2.使用 nacos 作为 注册中心和配置中心，代替 Eureka和Config。参考 dubbo-provider 模块 </br>
  3.使用 zuul 作为网关，只是简单事例，工作中使用的是 nginx。 参考 zuul 模块 </br>
  4.使用 dubbo 作为内部rpc调用，序列化使用 kryo 。工作中将nexus作为入口，只负责请求进入(controller)，通过dubbo 进行 rpc 通信，
进行业务逻辑调用(service)，返回相应数据。api和dao作为单独项目，由service和nexus进行pom引入。
因为主要使用springcloud http 调用，所以只是简单实现dubbo整合。参考 dubbo相关模块 </br>
  5.使用 feign 进行 http 远程调用,符合开发时接口调用规范。参考 sentinel + feign 相关模块 </br>
  6.分布式事务 强一致性使用 Seata , 弱一致性使用 MQ 处理分布式事务。业务中应尽量避免分布式事务产生。
  目前仅为测试，api和dao 未单独分离。参考 seata 相关模块 </br>
  7.熔断、降级、限流 使用 Sentinel 代替 Hystrix 更加灵活，代码侵入性更低。参考 sentinel 相关模块 </br>
  8.分布式链路追踪 Zipkin ,参考 feign 相关模块。 </br>
  9.分布式链路监控与追踪系统使用 Zipkin。参考 feign-provider </br>
  10.分布式任务调度使用 xxl-job。参考xxl-job 模块 </br>
  11. activiti工作流引擎使用整合。参考 activiti 模块。  </br>
  12.一些开发常用工具，比如 二维码生成和解析，HttpUtils 参考 common 模块。 </br>
  13. Mybatis 代码生成插件 参考db 模块。 </br>
  14. 多数据源整合,时间相关转换,返回值统一封装,全局异常处理,poi,参考 feign-consumer 模块</br>
  15. 文本相似度分析，Pinyin4jUtils , 参考 common 模块。 </br>
  父POM中配置了自己nexus私服，已注释，settings-nexus.xml 为自己私服对应配置文件，方便后期查找使用。</br> 

