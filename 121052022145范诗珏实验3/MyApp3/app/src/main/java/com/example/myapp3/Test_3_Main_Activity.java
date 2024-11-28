package com.example.myapp3;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Test_3_Main_Activity extends AppCompatActivity {

    private static final int TEXT_VIEW_ID = R.id.test_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_font_size_small) {
            updateFontSize(10);
            return true;
        } else if (item.getItemId() == R.id.menu_font_size_medium) {
            updateFontSize(16);
            return true;
        } else if (item.getItemId() == R.id.menu_font_size_large) {
            updateFontSize(20);
            return true;
        } else if (item.getItemId() == R.id.menu_normal_item) {
            Toast.makeText(this, "这是一段测试文本", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menu_font_color_red) {
            updateFontColor(Color.RED);
            return true;
        } else if (item.getItemId() == R.id.menu_font_color_black) {
            updateFontColor(Color.BLACK);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateFontSize(int size) {
        TextView textView = findViewById(TEXT_VIEW_ID);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    private void updateFontColor(int color) {
        TextView textView = findViewById(TEXT_VIEW_ID);
        textView.setTextColor(color);
    }
}