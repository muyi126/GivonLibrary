# GivonLibrary
##  前言
1. 这个项目是为了学习mvp框架搭建的,先以app开发为library基础，完成之后会整理为mvp的library提供给其他项目做框架使用，网络请求为retrofit+okhttp3,线程处理使用rxjava，rxjava学习成本很高但是能解决项目后期的代码复杂度的和可阅读性问题，


## APP
## Library
1. 使用 [nucleus](https://github.com/konmik/nucleus) 简化 MVP 架构
2. 使用 [RxJava](https://github.com/ReactiveX/RxJava) 处理 API 请求返回数据
3. 使用 [SmartTabLayout](https://github.com/ogaclejapan/SmartTabLayout) 实
现带 Icon 的选项卡切换

##  项目依赖
1. [android.support.*](https://developer.android.com/tools/support-library/index.html) Android Support Library
2. [butterknife](https://github.com/JakeWharton/butterknife) View注入框架
3. [dagger]
4. [Gson]
5. [prefser]
6. [rxandroid]
7. [rxjava]
8. [fresco]
9. [okhttp3]
10. [icepick]
11. [rxbus]
12. [retrofit2]
13. [MultiStateView]
14. [smartadapters]
15. [materialrefeshlayout]
16. [nineoldandroids]

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
### /common/internal/di
	Dagger依赖注入的类
### /common/internal/di/component
### /common/internal/di/module
### /common/internal/di/qualifier
### /common/internal/di/scope
### /common/net
	retrofit
### /common/provider
	RxBus
### /common/qualifier
	Dagger自定义注解类
### /common/transformer
	retrofit转换类
### /module
	数据获取的类
### /module/EventObj
	RxBus事件的类
### /ui
	Ui类
### /ui/presenter
	presenter类
### /ui/View
	View类，activity，fragment、、
	
	








