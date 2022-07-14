## blog

---

### 项目目录
 - blog-web: java开发的博客后端项目
 - blog_vue_web: vue开发的前端项目

### 目标 
 - 搭建自己的博客
 - 集成各类中间件
 - 提供一个入口为开发一些日常工作使用的插件

### 初期目标
 - 使用webpack+vue完成前端项目
 - 使用springboot+mybatis-plus 完成后端项目
 - 使用k8s+nginx+jenkins 部署项目

> 暂时处于开发过程中。 用实践自己的博客系统。

- 问题一：
  运行 npm install --save vue-markdown 后编译，
  出现 babel-runtime/core-js/get-iterator babel-runtime/core-js/object/keys not found
- 解决：运行 npm install --save babel-runtime，重新编译


- todo:
  - 功能设计:
    - 搜索内容接口
    - 动态获取目录
    - 观看量显示
    - 留言