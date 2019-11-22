package com.dk.hen.product_block;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class block_adapter extends ArrayAdapter<block> {
    ArrayList<block> list_block = new ArrayList<block>();
    Context context;
    public block_adapter(@NonNull Context context, int resource, @NonNull ArrayList<block> objects) {
        super(context, resource, objects);
        this.context = context;
        list_block = objects;
    }

    @Override
    public int getCount(){
        return super.getCount();
    }
    @Override

    public View getView(final int position, View convertView, ViewGroup parent){
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_block, null);

//        TextView name = view.findViewById(R.id.name);
//        TextView id = view.findViewById(R.id.id);
//        TextView last_four = view.findViewById(R.id.last_four);
//        TextView created_date = view.findViewById(R.id.created_date);
//        ImageView logo = view.findViewById(R.id.img_logo);
//        TextView logo_text = view.findViewById(R.id.text_logo);
//
//        ImageButton refresh = view.findViewById(R.id.btn_refresh);
//        ImageButton hide = view.findViewById(R.id.btn_hide);
//        ImageButton expand = view.findViewById(R.id.btn_expand);

//        ImageView btn_take_photo = view.findViewById(R.id.take_photo);
//        ImageView btn_garanti_date = view.findViewById(R.id.garanti_date);
//        ImageView btn_share = view.findViewById(R.id.share);
//        ImageView btn_return_date = view.findViewById(R.id.return_date);
//        ImageView btn_comments = view.findViewById(R.id.comments);
//
//        LinearLayout expanded_group = view.findViewById(R.id.expanded_btns);






        return view;
    }

}
