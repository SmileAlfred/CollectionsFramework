package com.example.collectionsframework.c3json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.collectionsframework.R;
import com.example.collectionsframework.c3json.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

// FastJson解析页面
public class FastJsonActivity extends Activity implements View.OnClickListener {

    private TextView tv_title;
    private Button bt_fastjson_tojavaobject;
    private Button bt_fastjson_tojavalist;
    private Button bt_fastjson_javatojsonobject;
    private Button bt_fastjson_javatojsonarray;

    private TextView tv_fastjson_orignal;
    private TextView tv_fastjson_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fast_json);

        // 初始化view
        initView();

        // 初始化监听
        initListener();
    }

    private void initListener() {
        bt_fastjson_tojavaobject.setOnClickListener(this);
        bt_fastjson_tojavalist.setOnClickListener(this);
        bt_fastjson_javatojsonobject.setOnClickListener(this);
        bt_fastjson_javatojsonarray.setOnClickListener(this);
    }

    private void initView() {

        // 获取标题对象
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("FastJson解析");

        // 获取4个button对象
        bt_fastjson_tojavaobject = (Button)findViewById(R.id.bt_fastjson_tojavaobject);
        bt_fastjson_tojavalist = (Button)findViewById(R.id.bt_fastjson_tojavalist);
        bt_fastjson_javatojsonobject = (Button)findViewById(R.id.bt_fastjson_javatojsonobject);
        bt_fastjson_javatojsonarray = (Button)findViewById(R.id.bt_fastjson_javatojsonarray);

        // 获取显示数据的textView对象
        tv_fastjson_orignal = (TextView)findViewById(R.id.tv_fastjson_orignal);
        tv_fastjson_last = (TextView)findViewById(R.id.tv_fastjson_last);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            // （1）将json格式的字符串{}转换为Java对象
            case R.id.bt_fastjson_tojavaobject:
                jsonToJavaObjectByFastJson();
                break;

            // （2）将json格式的字符串[]转换为Java对象的List
            case R.id.bt_fastjson_tojavalist:
                jsonToJavaListByFastJson();
                break;

            // （3）将Java对象转换为json字符串{}
            case R.id.bt_fastjson_javatojsonobject:
                javaToJsonObjectByFastJson();
                break;

            // （4）将Java对象的List转换为json字符串[]
            case R.id.bt_fastjson_javatojsonarray:
                javaToJsonArrayByFastJson();
                break;
        }
    }

    // （4）将Java对象的List转换为json字符串[]
    private void javaToJsonArrayByFastJson() {

        // 1 创建一个Java集合
        List<ShopInfo> shops = new ArrayList<>();

        ShopInfo baoyu = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");
        ShopInfo longxia = new ShopInfo(2, "龙虾", 251.0, "longxia");

        shops.add(baoyu);
        shops.add(longxia);

        // 2 生成JSON数据
        String json = JSON.toJSONString(shops);

        // 3 显示JSON数据
        tv_fastjson_orignal.setText(shops.toString());
        tv_fastjson_last.setText(json);

    }

    // （3）将Java对象转换为json字符串{}
    private void javaToJsonObjectByFastJson() {

        // 1 创建一个Java对象
        ShopInfo shopInfo = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");

        // 2 生成JSON数据
        String json = JSON.toJSONString(shopInfo);

        // 3 显示数据
        tv_fastjson_orignal.setText(shopInfo.toString());
        tv_fastjson_last.setText(json);
    }


    // （2）将json格式的字符串[]转换为Java对象的List
    private void jsonToJavaListByFastJson() {

        // 1 获取或创建json数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";

       // 2 解析JSON数据
        List<ShopInfo> shopInfos = JSON.parseArray(json, ShopInfo.class);

        // 3 显示数据
        tv_fastjson_orignal.setText(json);
        tv_fastjson_last.setText(shopInfos.toString());
    }

    // （1）将json格式的字符串{}转换为Java对象
    private void jsonToJavaObjectByFastJson() {

        // 1 获取或创建json数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}\n";

        // 2 解析JSON数据
        ShopInfo shopInfo = JSON.parseObject(json, ShopInfo.class);

        // 3 显示数据
        tv_fastjson_orignal.setText(json);
        tv_fastjson_last.setText(shopInfo.toString());
    }
}
