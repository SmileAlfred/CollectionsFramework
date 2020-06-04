# CollectionsFramework
Android常用基础框架、技术汇总；
# 一、软件框架的搭建
1. 实现多个fragment，
2. 通过对RadioGroup设置监听实现对不同fragmen的切换；
3. 这里默认选择首页的代码，应该写在监听处理之后；否则首次打开应用，主页没有数据；
4. 切换fragment；此处用到的不是replace；而是hide和show()；避免反复实例化fragment；
5. 解决横竖屏切换的问题，在功能清单文件配置：android:configChanges="orientation|keyboardHidden|screenSize；

# 二、
