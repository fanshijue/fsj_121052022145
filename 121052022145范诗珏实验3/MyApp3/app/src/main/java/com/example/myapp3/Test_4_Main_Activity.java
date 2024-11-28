package com.example.myapp3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_4_Main_Activity extends AppCompatActivity {

    private String[] names = new String[]{"One", "Two", "Three", "Four", "Five"};
    private int imageId = R.drawable.dog; // 使用单张图片资源
    private List<Map<String, Object>> listItems = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private ListView listView;
    private ActionMode actionMode;
    private ArrayList<Integer> selectedItems = new ArrayList<>();
    private int[] originalBackgroundColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_test);

        // 填充数据
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("name", names[i]);
            listItem.put("image", imageId);
            listItems.add(listItem);
        }

        // 创建SimpleAdapter
        simpleAdapter = new SimpleAdapter(this, listItems, R.layout.actionmode_test,
                new String[]{"name", "image"},
                new int[]{R.id.name, R.id.image});
        listView = findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);

        originalBackgroundColors = new int[listItems.size()];
        for (int i = 0; i < originalBackgroundColors.length; i++) {
            originalBackgroundColors[i] = Color.TRANSPARENT; // 假设原始背景是透明的
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode == null) {
                    actionMode = startActionMode(actionModeCallback);
                }

                toggleSelection(position); // 切换选中状态
                simpleAdapter.notifyDataSetChanged(); // 更新视图以反映新状态

                if (selectedItems.isEmpty()) {
                    actionMode.finish();
                } /* else {
                    actionMode.setTitle(String.valueOf(selectedItems.size())); // 更新标题显示选中项的数量
                }*/
            }
        });

    }

    private void toggleSelection(int position) {
        if (selectedItems.contains(position)) {
            selectedItems.remove(Integer.valueOf(position));
            View convertView = listView.getChildAt(position);
            if (convertView != null) {
                convertView.setBackgroundColor(originalBackgroundColors[position]);
            }
        } else {
            selectedItems.add(position);
            View convertView = listView.getChildAt(position);
            if (convertView != null) {
                convertView.setBackgroundColor(Color.parseColor("#87CEFA")); // 设置选中项的背景色
            }
        }
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.action_confirm_delete) {
                // 执行删除操作
                for (int i = selectedItems.size() - 1; i >= 0; i--) {
                    listItems.remove((int) selectedItems.get(i));
                }
                mode.finish();
                selectedItems.clear(); // 清除选中的项
                simpleAdapter.notifyDataSetChanged(); // 更新列表视图
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            for (Integer position : selectedItems) {
                View convertView = listView.getChildAt(position);
                if (convertView != null) {
                    convertView.setBackgroundColor(originalBackgroundColors[position]);
                }
            }
            selectedItems.clear(); // 清除所有选中的项
            simpleAdapter.notifyDataSetChanged(); // 更新列表视图
        }

    };
}