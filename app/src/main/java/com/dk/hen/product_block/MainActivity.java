package com.dk.hen.product_block;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView block_list;
    ArrayList<block> blocks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        block_list = (ListView)findViewById(R.id.list_block);
        block b = new block("Orestoriga");
        blocks.add(b);
        blocks.add(b);
        blocks.add(b);


        block_adapter adapter = new block_adapter(this,R.layout.item_block,blocks);
        block_list.setAdapter(adapter);
    }
}
