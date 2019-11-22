package com.dk.hen.product_block;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView block_list;
    ArrayList<block> blocks = new ArrayList<>();
    int TAKE_PHOTO_CODE = 0;
    public static MainActivity self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self = this;
        ImageView add_block = findViewById(R.id.btn_add_block);
        final EditText block_id = findViewById(R.id.edit_block_id);
        block_list = (ListView)findViewById(R.id.list_block);

        block_adapter adapter = new block_adapter(this,R.layout.item_block,blocks);
        block_list.setAdapter(adapter);

        add_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (block_id.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please input id", Toast.LENGTH_LONG).show();
                }
                else {
                    blocks.add(new block(block_id.getText().toString()));
                    refresh();
                    block_id.setText("");
                }
            }
        });
    }
    public void refresh(){
        block_adapter adapter = new block_adapter(this,R.layout.item_block,blocks);
        block_list.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
        }
    }

    public static MainActivity getInstance(){
        return self;
    }
}
