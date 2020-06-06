package com.example.collectionsframework.c3json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c3json.bean.DataInfo;
import com.example.collectionsframework.c3json.bean.FilmInfo;
import com.example.collectionsframework.c3json.bean.ShopInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
// 手动JSON解析页面

/**
 * （1）将json格式的字符串{}转换为Java对象
 * （2）将json格式的字符串[]转换为Java对象的List
 * （3）复杂json数据解析:既包括对象又包括数组
 * （4）特殊json数据解析
 */
public class NativeJsonPraseActivity extends Activity implements View.OnClickListener {
    private TextView tv_title;
    private Button bt_native_tojavaobject;
    private Button bt_native_tojavalist;
    private Button bt_native_complex;
    private Button bt_native_special;
    private TextView tv_native_orignal;
    private TextView tv_native_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_native_json_prase);

        // 初始化view
        initView();

        // 初始化监听
        initListener();
    }

    // 初始化button的点击事件
    private void initListener() {
        bt_native_tojavaobject.setOnClickListener(this);
        bt_native_tojavalist.setOnClickListener(this);
        bt_native_complex.setOnClickListener(this);
        bt_native_special.setOnClickListener(this);
    }

    private void initView() {
        // 修改头布局显示
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("手动JSON解析");

        // 获取button对象
        bt_native_tojavaobject = (Button) findViewById(R.id.bt_native_tojavaobject);
        bt_native_tojavalist = (Button) findViewById(R.id.bt_native_tojavalist);
        bt_native_complex = (Button) findViewById(R.id.bt_native_complex);
        bt_native_special = (Button) findViewById(R.id.bt_native_special);

        // 获取显示数据的textView对象
        tv_native_orignal = (TextView) findViewById(R.id.tv_native_orignal);
        tv_native_last = (TextView) findViewById(R.id.tv_native_last);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            // (1)将json格式的字符串{}转换为Java对象
            case R.id.bt_native_tojavaobject:
                jsonToJavaObjectByNative();
                break;

            // (2)将json格式的字符串[]转换为Java对象的List
            case R.id.bt_native_tojavalist:
                jsonToJavaListByNative();
                break;

            // (3)复杂json数据解析
            case R.id.bt_native_complex:
                jsonToJavaOfComplex();
                break;

            // (4)特殊json数据解析
            case R.id.bt_native_special:
                jsonToJavaOfSpecial();
                break;
        }
    }

    // (4)特殊json数据解析
    private void jsonToJavaOfSpecial() {

        // 1 获取或创建JSON数据
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"list\": {\n" +
                "        \"0\": {\n" +
                "            \"aid\": \"6008965\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 170,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:34\"\n" +
                "        },\n" +
                "        \"1\": {\n" +
                "            \"aid\": \"6008938\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 404,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:33\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        // 创建封装的Java对象
        FilmInfo filmInfo = new FilmInfo();

        // 2 解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            // 第一层解析
            int code = jsonObject.optInt("code");
            JSONObject list = jsonObject.optJSONObject("list");

            // 第一层封装
            filmInfo.setCode(code);
            List<FilmInfo.FilmBean> lists = new ArrayList<>();
            filmInfo.setList(lists);

            // 第二层解析
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonObject1 = list.optJSONObject(i + "");
                
                if(jsonObject1 != null) {
                    String aid = jsonObject1.optString("aid");

                    String author = jsonObject1.optString("author");

                    int coins = jsonObject1.optInt("coins");

                    String copyright = jsonObject1.optString("copyright");

                    String create = jsonObject1.optString("create");

                    // 第二层数据封装
                    FilmInfo.FilmBean filmBean = new FilmInfo.FilmBean();
                    filmBean.setAid(aid);
                    filmBean.setAuthor(author);
                    filmBean.setCoins(coins);
                    filmBean.setCopyright(copyright);
                    filmBean.setCreate(create);

                    lists.add(filmBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 3 显示JSON数据
        tv_native_orignal.setText(json);
        tv_native_last.setText(filmInfo.toString());
    }

    // (3)复杂json数据解析
    private void jsonToJavaOfComplex() {

        // 1 获取或创建JSON数据
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"count\": 5,\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"坚果\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 132,\n" +
                "                \"title\": \"炒货\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 166,\n" +
                "                \"title\": \"蜜饯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 195,\n" +
                "                \"title\": \"果脯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 196,\n" +
                "                \"title\": \"礼盒\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"rs_code\": \"1000\",\n" +
                "    \"rs_msg\": \"success\"\n" +
                "}";

        // 封装Java对象
        DataInfo dataInfo = new DataInfo();

        // 2 解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            // 第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            String rs_code = jsonObject.optString("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");

            // 第一层封装
            dataInfo.setRs_code(rs_code);
            dataInfo.setRs_msg(rs_msg);
            DataInfo.DataBean dataBean = new DataInfo.DataBean();
            dataInfo.setData(dataBean);

            // 第二层解析
            int count = data.optInt("count");
            JSONArray items = data.optJSONArray("items");

            // 第二层数据的封装
            dataBean.setCount(count);

            List<DataInfo.DataBean.ItemsBean> itemsBean = new ArrayList<>();
            dataBean.setItems(itemsBean);

            // 第三层解析
            for (int i = 0; i < items.length(); i++) {
                JSONObject jsonObject1 = items.optJSONObject(i);

                if (jsonObject1 != null) {
                    int id = jsonObject1.optInt("id");

                    String title = jsonObject1.optString("title");

                    // 第三层数据的封装
                    DataInfo.DataBean.ItemsBean bean = new DataInfo.DataBean.ItemsBean();
                    bean.setId(id);
                    bean.setTitle(title);

                    itemsBean.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 3 显示JSON数据
        tv_native_orignal.setText(json);
        tv_native_last.setText(dataInfo.toString());
    }


    // (2)将json格式的字符串[]转换为Java对象的List
    private void jsonToJavaListByNative() {

        // 1 获取或创建JSON数据
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

        List<ShopInfo> shops = new ArrayList<>();

        // 2 解析json
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject != null) {
                    int id = jsonObject.optInt("id");

                    String name = jsonObject.optString("name");

                    double price = jsonObject.optDouble("price");

                    String imagePath = jsonObject.optString("imagePath");

                    // 封装Java对象
                    ShopInfo shopInfo = new ShopInfo(id, name, price, imagePath);

                    shops.add(shopInfo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 3 显示JSON数据
        tv_native_orignal.setText(json);
        tv_native_last.setText(shops.toString());
    }


    // (1)将json格式的字符串{}转换为Java对象
    private void jsonToJavaObjectByNative() {

        // 1 获取或创建JSON数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}\n";

        ShopInfo shopInfo = null;

        // 2 解析json
        try {
            JSONObject jsonObject = new JSONObject(json);
//            int id = jsonObject.getInt("id");
            int id1 = jsonObject.optInt("id");

            String name = jsonObject.optString("name");

            double price = jsonObject.optDouble("price");

            String imagePath = jsonObject.optString("imagePath");

            // 封装Java对象
            shopInfo = new ShopInfo(id1, name, price, imagePath);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 3 显示JSON数据
        tv_native_orignal.setText(json);
        tv_native_last.setText(shopInfo.toString());
    }
}
