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
  
