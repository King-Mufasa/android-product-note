package com.dk.hen.product_block;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static com.dk.hen.product_block.Global.getToday;

public class block_adapter extends ArrayAdapter<block> implements View.OnClickListener {
    ArrayList<block> list_block = new ArrayList<block>();
    Context context;
    Integer is_garanti = 1;
    Integer is_return = 2;
    Integer is_share = 3;
    Integer is_comment = 4;
    int TAKE_PHOTO_CODE = 0;

    private DatePickerDialog picker;
    private Calendar calendar;
    private int year, month, day;
//    LinearLayout expanded_group;
//    LinearLayout main_block;
//    ImageButton hide;
//    ImageButton expand;

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

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_block, null);

        TextView name = view.findViewById(R.id.name);
        TextView id = view.findViewById(R.id.id);
        TextView last_four = view.findViewById(R.id.last_four);
        TextView created_date = view.findViewById(R.id.created_date);
        ImageView logo = view.findViewById(R.id.img_logo);
        TextView logo_text = view.findViewById(R.id.text_logo);

        ImageButton refresh = view.findViewById(R.id.btn_refresh);
        final ImageView hide = view.findViewById(R.id.btn_hide);
        final ImageView expand = view.findViewById(R.id.btn_expand);
        ImageView btn_take_photo = view.findViewById(R.id.take_photo);
        ImageView btn_garanti_date = view.findViewById(R.id.garanti_date);
        ImageView btn_share = view.findViewById(R.id.share);
        ImageView btn_return_date = view.findViewById(R.id.return_date);
        ImageView btn_comments = view.findViewById(R.id.comments);
        final  LinearLayout main_block = view.findViewById(R.id.main_block);
        final LinearLayout expanded_group = view.findViewById(R.id.expanded_btns);
        final ViewGroup.LayoutParams params = main_block.getLayoutParams();
        created_date.setText(getToday().split(" ")[1] + "/" +getToday().split(" ")[2] + "/" +getToday().split(" ")[5] );
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded_group.setVisibility(View.VISIBLE);
                hide.setVisibility(View.VISIBLE);
                expand.setVisibility(View.GONE);
                main_block.setLayoutParams(new RelativeLayout.LayoutParams(params.width,params.height+80));
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded_group.setVisibility(View.GONE);
                hide.setVisibility(View.INVISIBLE);
                expand.setVisibility(View.VISIBLE);
                main_block.setLayoutParams(new RelativeLayout.LayoutParams(params.width,params.height));
            }
        });

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        btn_garanti_date.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file = dir+getToday()+".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                }
                catch (IOException e)
                {
                }

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                ((Activity) context).startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
        btn_return_date.setOnClickListener(this);
        btn_comments.setOnClickListener(this);
        return view;
    }


    private void make_dialog(Integer flag) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.set_date_dialog);
        RadioButton val_1 = dialog.findViewById(R.id.val_1);
        RadioButton val_2 = dialog.findViewById(R.id.val_2);
        RadioButton val_3 = dialog.findViewById(R.id.val_3);
        EditText comment = dialog.findViewById(R.id.comments);
        RadioGroup radio_group = dialog.findViewById(R.id.radio_group);
        final RadioButton val_custom = dialog.findViewById(R.id.val_custom);
        Button btn_ok = dialog.findViewById(R.id.btn_ok);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);

        val_custom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    picker = new DatePickerDialog(context,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    val_custom.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month , day);

                    picker.show();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        switch (flag){
            case 1:
                break;
            case 4:
                comment.setVisibility(View.VISIBLE);
                radio_group.setVisibility(View.GONE);
                break;
            default:
                break;

        }

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.garanti_date:
                make_dialog(is_garanti);
                break;
            case R.id.share:
                Toast.makeText(MainActivity.getInstance(),"This is share button",Toast.LENGTH_LONG).show();
                break;
            case R.id.return_date:
                make_dialog(is_return);
                break;
            case R.id.comments:
                make_dialog(is_comment);
                break;
        }
    }
}
