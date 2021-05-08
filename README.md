# 轻量博客网站
    练手的小项目

## 目录
------
* 成果展示
* 技术栈
* 代码结构

### 一、成果展示
* 登录页面
![image.png](https://i.loli.net/2021/05/08/L2aEInv7JplsGuy.png)

* 注册页面
![image.png](https://i.loli.net/2021/05/08/Uf1l3nNpZ8S2sXg.png)
* 首页面
  ![image.png](https://i.loli.net/2021/05/08/pVXRu3sSo4zQW6x.png)
* 用户列表
  ![image.png](https://i.loli.net/2021/05/08/QGlLYhkeCjNaDyq.png)
    ![image.png](https://i.loli.net/2021/05/08/RiEZIrHhNak6te4.png)
* 博文列表
![image.png](https://i.loli.net/2021/05/08/QEOmukSJxzU89VA.png)
![image.png](https://i.loli.net/2021/05/08/ziQc3mYHaNk6sMw.png)
![image.png](https://i.loli.net/2021/05/08/SFTqDaroAlCPM9I.png)
* 个人中心
  ![image.png](https://i.loli.net/2021/05/08/PudKvRXS1Jp9hWb.png)
* 消息中心
  ![image.png](https://i.loli.net/2021/05/08/sLScqkQu2FZwUAz.png)
  ![image.png](https://i.loli.net/2021/05/08/bQsPNVMq2Z9GcWv.png)
* 写作页面 
  ![image.png](https://i.loli.net/2021/05/08/XKuZJMUpi1hqQa3.png)
  ![image.png](https://i.loli.net/2021/05/08/hcnZvgqeu5YMPjf.png)
  ![image.png](https://i.loli.net/2021/05/08/yA4Res5wTNJp6qU.png)
![image.png](https://i.loli.net/2021/05/08/fP2tdNb7guBnWlJ.png)
![image.png](https://i.loli.net/2021/05/08/nD1WVJapcethlrm.png)
### 二、技术栈
* 前端: vue + axious + element ui + vuex 
* 后端：springboot +shiro + Jpa 
* 关系型数据库：mysql 
### 三、代码结构
* springboot
  
|  文件夹   | 功能  |
|  ----  | ----  |
| config  | 配置，过滤端口，登录认证 |
| controller  | 接口 |
| entity  | 数据库表 |
| filter/manger  | 验证 |
| repository  | jpa接口 |
| utils  | 自定义工具类 |

* vue
  
|  vue   | 功能  |
|  ----  | ----  |
| List   | 用户列表 |
| Content  | 博文 |
| MessageList  | 消息中心 |
| Profile  | 个人中心 |
| Opro  | 其他用户介绍界面 |
| SortText  | 首页走马灯 |
| Text/TextMessage  | 文章卡片和内容页面 |
| Write  | 写文章 |
