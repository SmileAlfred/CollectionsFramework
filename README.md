# CollectionsFramework
Android常用基础框架、技术汇总；
# 一、软件框架的搭建
1. 实现多个fragment，
2. 通过对RadioGroup设置监听实现对不同fragmen的切换；
3. 这里默认选择首页的代码，应该写在监听处理之后；否则首次打开应用，主页没有数据；
4. 切换fragment；此处用到的不是replace；而是hide和show()；避免反复实例化fragment；
5. 解决横竖屏切换的问题，在功能清单文件配置：android:configChanges="orientation|keyboardHidden|screenSize；

# 二、OKHttp的使用
1. 原生okhttp3；
  1. 集哈地址： http://square.github.io/okhttp/
  2. 特点和用途：联网请求文本数据、大文件下载、大文件上传、请求图片；
  3. 使用了 OKHttp 的 get/post 请求文本
2. 张鸿洋封装好的OKHttp库；
  1. 集哈地址：https://github.com/hongyangAndroid/okhttp-utils
  2. 解决报错，导入其demo后，报错“PersistentCookieJar”相关信息：只需要在其 build.gradle文件种添加：
  ```
  allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
   }
  ```
  3. 这里要注意，实际使用过程中，选择一个框架使用，避免冲突，最终造成一个BUG改一天…………
  4. “一个BUG改一天之2”上传文件到本地TOMCAT服务器时，总是报错，没有权限、模拟器版本太高等等；最后在API22模拟器上面运行成功；新的API对服务器网址的校验十分严格；
  5. 本次封装，进行缓存时，只能缓存文本数据，对于图片没有缓存；
 3. 另外同样优秀的封装 OkHttpUtils；
  1. 集哈地址：https://github.com/jeasonlzy0216/OkHttpUtils
  2. 特点：文档详细，功能更具体，现在仍在持续更新！
  

  
