package com.google.leilaahamdyan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ResourceActivity extends AppCompatActivity {

    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        setTitle("منابع درسی");

        putData();

        ItemAdapter  adapter      = new ItemAdapter(this, items);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    void putData() {
        String title = "کتاب مفاهیم سیستم عامل نوشته سیلبرشاتس";
        String link  = "https://drive.google.com/file/d/1sJfsOLi6-ckOAEQE3gHfuZvBfecXNKt_/view?usp=sharing";
        items.add(new Item(title, link));

        title = "کتاب سیستم های عامل نوشته استالینگز";
        link  = "https://drive.google.com/file/d/1jDzivW2WxqLgIXckdZg-xQ_3nlMx84s-/view?usp=sharing";
        items.add(new Item(title, link));

        title = "کتاب سیستم های عامل مدرن نوشته تننباوم";
        link  = "https://drive.google.com/file/d/1QgJL1S34y1faOOSFcdn7a7tHNPE2ofia/view?usp=sharing";
        items.add(new Item(title, link));

        title = "درس نامه فارسی";
        link  = "https://drive.google.com/file/d/1VBiPteEtx89X92xVkWu4t8lAb1rENzMv/view?usp=sharing";
        items.add(new Item(title, link));
    }

}