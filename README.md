# 简介
CoffeeWx是一款基于VUE、Spring Boot的前后端分离的微信公众号管理系统、支持多公众号。

# 技术
  - 开发语言：Java
  - 数据库：Mysql
  - 前端框架：Vue + Axios
  - 后端框架：Spring Boot
  - 缓存：Redis
  - 工具类集合：[Hutool](https://gitee.com/loolly/hutool)
  - 和微信端对接工具：[weixin-java-tools](https://gitee.com/binary/weixin-java-tools)
  - 内网穿透工具：[Natapp](https://natapp.cn)

# 项目地址
  - 前端项目地址：[coffeewx-web-ui](https://gitee.com/skysong/coffeewx-web-ui)
  - 后端项目地址：[coffeewx-admin-api](https://gitee.com/skysong/coffeewx-admin-api)

#公众号url配置表达式
```$xslt
http://{域名}/api/wx/portal/{appid}
例子：http://coffee-ease.natapp1.cc/api/wx/portal/wxff7bf2c34c65e260
```

# 功能
#### 系统管理
  - 账号管理
  - 欢迎语管理
  - 关键词管理
  - 粉丝管理
  - 菜单管理
#### 素材管理
  - 文本管理

# 功能截图

![账号管理](https://images.gitee.com/uploads/images/2019/0226/153816_0e2f97e9_1019464.png "1_看图王.png")

![欢迎语管理](https://images.gitee.com/uploads/images/2019/0226/153838_44bc1d12_1019464.png "2_看图王.png")

![关键字管理](https://images.gitee.com/uploads/images/2019/0226/153845_15ab4bc1_1019464.png "3_看图王.png")

![粉丝管理](https://images.gitee.com/uploads/images/2019/0226/153854_9418c515_1019464.png "4_看图王.png")

![菜单管理](https://images.gitee.com/uploads/images/2019/0226/153902_6bae1639_1019464.png "5_看图王.png")

![文本管理](https://images.gitee.com/uploads/images/2019/0226/153910_81ddfda9_1019464.png "6_看图王.png")

![用户管理](https://images.gitee.com/uploads/images/2019/0226/153916_2a0b732e_1019464.png "7_看图王.png")


# Development
```sh
$ yarn install
$ yarn dev
```

### Todos
 - 首页规划
 - 图文管理
 - 模板消息管理

# 参考资料
  - [vue-element-admin](https://gitee.com/mirrors/vue-element-admin)
  - [spring-boot-api](https://github.com/lihengming/spring-boot-api-project-seed)
  - [weixin-java-tools](https://gitee.com/binary/weixin-java-tools)
  - [Hutool](https://gitee.com/loolly/hutool)

# 备注
个人学习使用，切勿商用！