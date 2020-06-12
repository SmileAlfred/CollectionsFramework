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
2. 封装好的OKHttp库；
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
  
# 三、Json解析的使用
1. Android 原生技术
   1. 将 json 格式的字符串 转换为 Java 对象；
   2. 将 json 格式的字符串 转换为 Java 对象的 List；
   3. 复杂 json 数据解析（json数据中既包含对象又包含集合）；
   4. 特殊 json 数据解析（对于json数据中title是0的，无法用工具解析bean类，因为：*要求json对象中的key的名称与java对象对应的类中的属性名要相同*；需要手写）；
   5. 注：原生的解析没有 Java对象→json数据 的方法；
2. GSON 框架技术
   1. 最新地址：https://mvnrepository.com/artifact/com.google.code.gson/gson
   2. 将 json 格式的字符串 转换为 Java 对象；
   3. 将 json 格式的字符串 转换为 Java 对象的 List；
   4. 将Java对象转换为json字符串{}；
   5. 将Java对象的List转换为json字符串[]；
3. FastJson 框架技术
   1. 最新地址：https://github.com/alibaba/fastjson/wiki/Quick-Start-CN
   2. 将 json 格式的字符串 转换为 Java 对象；
   3. 将 json 格式的字符串 转换为 Java 对象的 List；
   4. 将Java对象转换为json字符串{}；
   5. 将Java对象的List转换为json字符串[]；
   
# 四、xUitls3-注解&联网模块
1. 功能：
   1. 注解：在 Activity 或 Fragment 中初始化布局文件、点击事件、替换Fragment ；
   2. 联网模块：支持get/post请求、超过2G大文件上传下载；
   3. 加载图片，并支持圆角、方形剪裁；
   4. 数据库模块；
   
2. 注解模块;
   1. 通过导入module进行依赖，而非jar包；最新地址：https://github.com/wyouflf/xUtils3；
   2. 这里需要配置相应的 application（拷贝使用即可）；
   3. 代码显示
   ```
   @ContentView(R.layout.activity_xutils3) 
   public class XUtils3Activity extends Activity {
      @ViewInject(R.id.tv_title) 
      private TextView tv_title;
      @Override 
      protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); 
         x.view().inject(this);
	}
	@Event(value = {R.id.btn_annotation,R.id.btn_net,R.id.btn_image,R.id.btn_database}) 
	private void getEnvent(View view){
   ```
   4. Fragment 中使用注解初始化布局
   ```
   //在 onCreateView() 方法中写
   return x.view().inject(this, inflater, container);
   ```

3. 联网模块
   1. Get / POST 请求文本；
   2. 文件下载&断点续传；```params.setAutoResume(true);```
   
# 五、Afinal框架
1. 作为xUtils3的前身，他已经在2015就停止维护了；
2. 导入jar包afinal_0.5.1_bin.jar使用；
3. 同样的加载图片、请求json数据、文件上传下载；
  
# 六、Volley
1. 通过jar依赖使用；并没有找到最新的jar；
2. 非常适合进行数据量不大，但通信频繁的网络操作；对于大数据量的网络操作，比如下载文件等，Volley的表现不好；
3. 实现网络请求（Get / Post / Json）和图片加载；

# 七、EventBus
1. 通过jar包依赖；最新地址：https://github.com/greenrobot/EventBus；
2. 问题：对使用情景不了解，暂时做不到灵活使用；
3. 步骤：
   1. 注册广播：``EventBus.getDefault().register(EventBusActivity.this);```
   2. 解注册：```EventBus.getDefault().unregister(EventBusActivity.this);```
   3. 创建发送消息类；
   4. 发送消息：```EventBus.getDefault().post(new MessageEvent("主线程发送过来的数据"));```
   5. 接收消息;
   ```
   @Subscribe(threadMode = ThreadMode.MAIN)
   public void MesssageEventBus(MessageEvent event) {
    ```
4. 注意：粘性事件的顺序：3 - 4 - 5 - 1 - 2

# 八、ButterKnife
1. 最新地址：https://github.com/JakeWharton/butterknife
2. 越用越顺手，不过第一次使用时，配置比较麻烦；
3. 功能：
   1. 省略findViewById()；在activity、fragment、holder中使用；
   2. 省略setOnClickListener()；
   3. ListView的点击@OnItemClick, CheckBox的@OnCheckedChanged也可以实现省略操作；
   4. 一次指定多个id,为多个View绑定一个事件处理方法；

# 九、ImageLoader
1. 特点：多线程下载 来自网络、文件等的图片；
2. 最新地址：https://github.com/nostra13/Android-Universal-Image-Loader ；要么使用jar包，要么使用 ```implementation 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'```;
3. 使用前要配置 Application;
4. 实现：在ListView 、 GridView 、 ViewPager 中的显示；

# 十、Picasso
1. 下载地址：https://github.com/square/picasso
2. 使用方法：    
   ```
   	implementation 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1'
   }
   repositories {
       jcenter()
   }
   ```
3. 基本用法；```Picasso.get.load(imageUrl).into(imageView);```
4. 图片裁剪；``` .resizeDimen(width,height)  ```
5. 存在36种变换；包括，黑白化、肖像化、模糊化，马赛克化等等；

# 十一、RecyclerView
1. 注意：如何设置适配器；
2. 注意：如何设置其item的点击事件？
3. 注意：如何设置其添加、删除动画？
4. 详细使用见博客：https://blog.csdn.net/liusaisaiV1/article/details/106038665

# 十二、Glide（重！有BUG）
1. 可加载图片、动图、本地等资源；地址：https://github.com/bumptech/glide
2. 变化比较大，最新的API值得总结和学习；
3. 对于Glide的变换；出现BUG，一定要看最新的文档，因为很多方法都废弃了；

# 十三、Fresco图片加载模块<重>
1. 有更快的图片下载速度，可以加载和显示gif图，是很好的图片框架；
2. 最新地址：https://github.com/facebook/fresco
3. 注意添加依赖后还要在 Application 中配置；
4. 依赖：
   ```
   implementation 'com.facebook.fresco:fresco:2.2.0'
   implementation 'com.facebook.fresco:animated-gif:2.2.0'
   implementation 'com.facebook.fresco:animated-webp:2.1.0'
   implementation 'com.facebook.fresco:webpsupport:2.2.0'
   ```
5. 在 Application 中初始化Fresco：``` Fresco.initialize(this);```

# 十四、Android-PullToRefresh模块
1. 下载地址：https://github.com/chrisbanes/Android-PullToRefresh ；通过导入module添加依赖方式；
2. 实现ListView下拉刷新；
3. 实现GridView下拉刷新；
4. 实现Fragment下拉刷新；
5. ViewPager中嵌套多个ListView下拉刷新；
6. 用PullToRefreshViewPager实现ViewPager刷新；
7. WebView实现下拉刷新。

# 十五、UniversalVideoView 模块
1. 对系统播放器做了封装，对横竖屏切换以及控制栏做了优化；但并不是万能播放器；
2. 地址：https://github.com/linsea/UniversalVideoView
3. 在API22出现播放不了；23可以

# 十六、节操（饺子）播放器（重）
1. 依赖方式：导入module，随后依赖库；已经导入并优化、净化老版本的；
2. 实现小窗口播放；
3. 实现在ListView中播放视频；
4. 实现在ViewPager的ListView中播放视频；
5. 实现分类型的ListView中播放视频；
6. 实现RecyclerView中播放视频。
7. 可以自定义播放器UI；
8. 可以在WebView中播放视频；
9. 导入了最新的“饺子播放器”，并净化了视频链接；没有进行集成；
10. 实现的功能：小窗口播放、倍速、分享按钮等牛操作；

# 十七、Banner（重）
1. 依赖方式：```implementation 'com.youth.banner:banner:1.4.10'``` ；这是继承的老版本；
2. 已经导入新版本的module；但并未继承；
3. 实现多种动画切换效果；
4. 实现在 RecyclerView 中嵌套 Banner；在 ConstrainLayout 中嵌套 Banner；在 ViewPager+fragment+RecyclerView中嵌套 Banner;以及仿照淘宝的Banner;

















