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

# 项目演示地址
  - 项目演示地址：[coffeewx-web-ui](http://www.whjdz2012.cn/coffeewx-web-ui)

# 前后端项目地址
  - 前端地址：[coffeewx-web-ui](https://gitee.com/skysong/coffeewx-web-ui)
  - 后端地址：[coffeewx-admin-api](https://gitee.com/skysong/coffeewx-admin-api)


# 公众号url配置表达式
```sh
http://{域名}/api/wx/portal/{appid}
例子：http://coffee-ease.natapp1.cc/api/wx/portal/wxff7bf2c34c65e260
```

# coffeewx-web-ui 打包部署
```sh

1、修改打包对应环境的配置文件
例如：打包prod环境
修改/coffeewx-web-ui/config/prod中的BASE_API变量，修改为对应后台地址
2、打包部署
$ npm install
$ npm run dev
$ npm run build:prod
```

# coffeewx-admin-api 打包部署
```sh
1、修改/resources/application-prod.properties配置文件中对应配置，根据部署环境不同，切换配置文件
   mvn clean package -DskipTests -Pprod
2、Copy项目中/bin目录下三个脚本，和打包之后的coffeewx-admin-api-1.0.jar文件放在同一个目录
3、脚本授权
    chmod +x startup.sh
    chmod +x stop.sh
    chmod +x restart.sh
4、启动脚本
    ./startup.sh
    
```
# ngnix配置参考
```sh
    location / {
        root   /usr/local/coffeewx/coffeewx-web-ui;
        try_files $uri $uri/ @router;
        index  index.html index.htm;
    }
    
    location @router {
        rewrite ^.*$ /index.html last;
    }
    
    location ^~ /api {
        #root   interface;#资源目录
        #首页
        #index  index.html index.htm;
    
        #请求转向load_balance_server 定义的服务器列表
        proxy_pass  http://localhost:9999/api;
        access_log /usr/local/nginx/logs/coffeewx-admin-access.log main;
        error_log /usr/local/nginx/logs/coffeewx-admin-error.log warn;
    }

```

# SQL脚本
```sh
/src/test/resources/coffeewx-admin.sql
```

# CodeGenerator代码自动生成工具
```sh
注意事项：
1、执行完main方法，生成代码，genCodeByCustomModelName("sys_user","User")
2、修改UserMapper.java，添加findList方法，可参考其他模块。
3、修改UserMapper.xml，添加findList SQL语句，可参考其他模块。
4、运行项目
```

# 参考资料
  - [vue-element-admin](https://gitee.com/mirrors/vue-element-admin)
  - [spring-boot-api](https://github.com/lihengming/spring-boot-api-project-seed)
  - [weixin-java-tools](https://gitee.com/binary/weixin-java-tools)
  - [Hutool](https://gitee.com/loolly/hutool)