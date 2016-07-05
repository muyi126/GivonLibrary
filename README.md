# GivonLibrary
##  前言
1. 这个项目是为了学习mvp框架搭建的


## APP
## Library
1. 使用 [nucleus](https://github.com/konmik/nucleus) 简化 MVP 架构
2. 使用 [RxJava](https://github.com/ReactiveX/RxJava) 处理 API 请求返回数据
3. 使用 [SmartTabLayout](https://github.com/ogaclejapan/SmartTabLayout) 实
现带 Icon 的选项卡切换

##  项目依赖
1. [android.support.*](https://developer.android.com/tools/support-library/index.html) Android Support Library
2. [butterknife](https://github.com/JakeWharton/butterknife) View注入框架

##  项目结构
### /api
	Api接口方法定义
### /api/entity
	Api接口返回数据模型
### /api/entity/element
	Api返回数据模型的单元模型
### /common
	App,Constant,Navigator
### /common/adapter
	适配器
### /common/base
	Activity基类，Adapter基类，Model基类，Presenter基类，Fragment基类
### /common/internal/di/component
### /common/internal/di/module
### /common/internal/di/qualifier
### /common/internal/di/scope
### /common/net
### /common/provider
### /common/qualifier
### /common/transformer



