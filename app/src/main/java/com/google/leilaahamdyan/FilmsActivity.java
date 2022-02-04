package com.google.leilaahamdyan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FilmsActivity extends AppCompatActivity {

    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        setTitle("فیلم های آموزشی");
        putData();

        ItemAdapter  adapter      = new ItemAdapter(this, items);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    void putData() {
        String title = "فصل اول";
        String link  = "https://drive.google.com/file/d/17v7Du7m8gpp0R2v-ph9QpZyDfsTEuwIg/view";
        items.add(new Item(title, link));

        title = "فصل دوم";
        link  = "https://drive.google.com/file/d/1MjH8zR5UthdCt34Frv5dJr9jhoTLhZsE/view";
        items.add(new Item(title, link));

        title = "فصل سوم";
        link  = "https://drive.google.com/file/d/1cSkgw-hh_WKf4ix7ELkjGm5OJ-grDxtI/view";
        items.add(new Item(title, link));

        title = "فصل چهارم قسمت اول";
        link  = "https://drive.google.com/file/d/1m8LLmNsbq4-57zeFs3_DWIKuR_1Z-RFY/view";
        items.add(new Item(title, link));

        title = "فصل چهارم قسمت دوم";
        link  = "https://drive.google.com/file/d/1oNBCvBVpx1pxHEvippp9llFfCnT-vout/view";
        items.add(new Item(title, link));

        title = "فصل پنجم";
        link  = "https://drive.google.com/file/d/1e3yrVElfFlQoSB-V-FKeT8IP5n8b1N-0/view";
        items.add(new Item(title, link));

        title = "فصل ششم";
        link  = "https://drive.google.com/file/d/1P6C_jDHrA8D7gMqUtSgL2XWzt7IdhCMh/view";
        items.add(new Item(title, link));

        title = "فصل هفتم قسمت اول";
        link  = "https://drive.google.com/file/d/1YOSngZAhbAnryF-fB1jd03rvz7spJNYe/view";
        items.add(new Item(title, link));

        title = "فصل هفتم قسمت دوم";
        link  = "https://drive.google.com/file/d/1WJZtjkys15dN81hKCmpXYsav8rorCs9C/view";
        items.add(new Item(title, link));

        title = "فصل هشتم";
        link  = "https://drive.google.com/file/d/18FlUa9jna5FGeXVTKu1ws_QK_AKQmr7g/view";
        items.add(new Item(title, link));
    }


}