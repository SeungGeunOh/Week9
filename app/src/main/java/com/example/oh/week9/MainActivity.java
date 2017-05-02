package com.example.oh.week9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String[] fName ={"Abocado", "Banana", "Cherry", "Cranberry", "Grape", "Kiwi", "Orange", "Wetermelon"};
    final static String[] fPrice = {"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000"};
    final static int[] fImage ={R.drawable.abocado, R.drawable.banana, R.drawable.cherry,
                                 R.drawable.cranberry, R.drawable.grape, R.drawable.kiwi,
                                 R.drawable.orange, R.drawable.watermelon};
    public static boolean check = false;
    public static int imageNum = 0;

    ArrayList<String> cart = new ArrayList<>();
    ArrayList<FruitData> fruitDatas = new ArrayList<>();
    CustomAdapter adapter;
    GridView gridView;
    AddFruit addFruit;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView)findViewById(R.id.gridView);
        checkBox = (CheckBox)findViewById(R.id.checkBox);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), fruitDatas.get(position).fName, Toast.LENGTH_SHORT).show();
                addFruit.setFruit(fruitDatas.get(position).fName,
                        fruitDatas.get(position).fPrice,
                        fruitDatas.get(position).fImage,
                        position);
                imageNum = fruitDatas.get(position).fImage;
                cart.add(fruitDatas.get(position).fName);
            }
        });
        init();
    }

    public void init() {
        for (int i = 0; i < 8; i++)
            fruitDatas.add(new FruitData(fName[i], fPrice[i], fImage[i]));
        adapter = new CustomAdapter(this, fruitDatas);
        gridView.setAdapter(adapter);

        addFruit = (AddFruit)findViewById(R.id.addfruit);
        addFruit.setOnAddListener(new AddFruit.OnAddListener() {
            @Override
            public void onAdd(String name, String price, int imgnum) {
                imageNum = imgnum;
                fruitDatas.add(new FruitData(name, price, imgnum));
                gridView.setAdapter(adapter);
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkBox :
                if (checkBox.isChecked())
                    check = true;
                else
                    check = false;
                adapter.notifyDataSetChanged();
                break;
            case R.id.button :
                Toast.makeText(getApplicationContext(), "카트에 " + toString() + " 가 담겨있습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public String toString(){
        String msg = "";
        for (int i = 0; i < cart.size(); i++)
            msg += ", " + cart.get(i).toString();
        return msg.substring(1);
    }

}
