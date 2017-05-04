package com.example.oh.week9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by OH on 2017-04-27.
 */

public class AddFruit extends LinearLayout implements View.OnClickListener{
    static int num = 0;
    AutoCompleteTextView eName;
    EditText ePrice;
    ImageView img;
    Button b_next;
    Button b_add;


    public AddFruit(Context context){
        this(context, null);
    }

    public AddFruit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_widget, this);
        eName = (AutoCompleteTextView)findViewById(R.id.f_name);
        ePrice = (EditText)findViewById(R.id.f_price);
        img = (ImageView)findViewById(R.id.image1);
        b_next = (Button)findViewById(R.id.b_next);
        b_add = (Button)findViewById(R.id.b_add);
        b_next.setOnClickListener(this);
        b_add.setOnClickListener(this);
        eName.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, MainActivity.fName));
    }

    public void setFruit(String name, String price, int image, int position){
        eName.setText(name);
        ePrice.setText(price);
        img.setImageResource(image);
        num = position;
        b_add.setText("M");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_next :
                num++;
                if (num > 7)
                    num = 0;
                img.setImageResource(MainActivity.fImage[num]);
                MainActivity.imageNum = MainActivity.fImage[num];
                break;
            case R.id.b_add :
                if (MainActivity.imageNum == 0)
                    MainActivity.imageNum = MainActivity.fImage[0];

                if (b_add.getText() == "M") {
                    b_add.setText("ADD");
                    onChangeListener.onChange(eName.getText().toString(), ePrice.getText().toString(), MainActivity.imageNum);
                }
                else
                    onAddListener.onAdd(eName.getText().toString(), ePrice.getText().toString(), MainActivity.imageNum);
                break;
        }
    }

    interface OnAddListener{
        void onAdd(String name, String price, int imgnum);
    }

    public OnAddListener onAddListener;

    public void setOnAddListener(OnAddListener onAddListener){
        this.onAddListener = onAddListener;
    }

    interface OnChangeListener{
        void onChange(String name, String price, int image);
    }
    public OnChangeListener onChangeListener;

    public void setOnChangeListner(OnChangeListener onChangeListener) { this.onChangeListener = onChangeListener;}

}
