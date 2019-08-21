# springcloud学习

## spring cloud config

4个原则：

1. 分离
2. 抽象
3. 集中
4. 稳定

注意：想要使用config加载本地配置文件，需要将*spring.profiles.active*设置为native。

spring cloud配置服务器始终提供最新版本的属性，通过其底层存储库，对属性进行的更改将是最新的。但spring boot(spring cloud client)应用
只会在启动时读取它们的属性。因此spring cloud配置服务器中进行的属性更改不会被spring boot应用自动获取到。spring boot actuator提供了
一个@RefreshScop注解，允许开发团队访问/refresh端点，这回强制spring boot应用重新读取应用程序配置。

## 服务发现 spring cloud eureka

 