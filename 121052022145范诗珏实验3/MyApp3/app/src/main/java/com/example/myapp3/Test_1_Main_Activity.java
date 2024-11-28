package com.example.myapp3;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.*;

public class Test_1_Main_Activity extends AppCompatActivity {

    private  String[] names = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private int[] ImageIds = new int[]{
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };
    private int selectedItemPosition = -1;
    private View lastSelectedView = null;
    private Toast toast = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_test);

        List<Map<String, Object>>listItems = new ArrayList<>();

        // 填充数据
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("name", names[i]);
            listItem.put("image", ImageIds[i]);
            listItems.add(listItem);
        }

        // 创建SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.simple_item,
                new String[]{"name", "image"},
                new int[]{R.id.name, R.id.image});
        ListView list = findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);

        // 设置ListView的点击事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedItemPosition == position) {
                    return;
                }

                if (lastSelectedView != null) {
                    lastSelectedView.setBackgroundResource(R.drawable.bottomline);
                }

                // 更新选中项的位置
                selectedItemPosition = position;
                // 更新选中项的背景颜色
                view.setBackgroundColor(Color.parseColor("#660000"));
                lastSelectedView = view; // 更新记录

                // 显示 Toast 提示
                if (toast != null) {
                    toast.cancel(); // 取消之前的 Toast
                }
                String itemName = (String) listItems.get(position).get("name");
                toast = Toast.makeText(Test_1_Main_Activity.this, itemName, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}