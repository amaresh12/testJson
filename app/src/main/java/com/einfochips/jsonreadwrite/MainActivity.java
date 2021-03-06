package com.einfochips.jsonreadwrite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btCreate,btCreateTwo,btUpdate,btDelete,btAdd;
    TextView tvPrintOne, tvPrintTwo,printResult;
    JsonArray jsonArrayOne,jsonArrayTwo , jsonResultArray;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCreate = findViewById(R.id.btCreate);
        btCreateTwo = findViewById(R.id.btCreateTwo);
        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
        tvPrintOne = findViewById(R.id.printOne);
        tvPrintTwo = findViewById(R.id.printTwo);
        printResult = findViewById(R.id.printResult);
        btAdd = findViewById(R.id.btAdd);
        jsonArrayOne = new JsonArray();
        jsonArrayTwo = new JsonArray();
        jsonResultArray = new JsonArray();


        btCreate.setOnClickListener(this::onClick);
        btCreateTwo.setOnClickListener(this::onClick);
        btUpdate.setOnClickListener(this::onClick);
        btDelete.setOnClickListener(this::onClick);
        btAdd.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btCreate:
                createJsonOne();
                break;
            case R.id.btCreateTwo:
                createJsonTwo();
                break;
            case R.id.btAdd:
                addTwo();
                break;
            case R.id.btDelete:
                removeOneFromTwo();
                break;
        }
    }

    private void removeOneFromTwo() {
        Gson gson = new Gson();
        JsonArray jsonArrayListResult = jsonArrayTwo;
        for(int i = 0 ; i < jsonArrayTwo.size() ; i++){
            Log.e(TAG,"jsonArrayTwo ::"+i);
            for(int j = 0 ; j < jsonArrayOne.size() ; j++){
                if(jsonArrayTwo.get(i).equals(jsonArrayOne.get(j))){
                    Log.e(TAG,"jsonArrayOne ::"+j);
                    Log.e(TAG,"removed pos  ::"+i);
                    jsonArrayListResult.remove(i);
                }
            }
        }
        printResult.setText(jsonArrayListResult.toString());
    }

    private void addTwo() {
        jsonResultArray = new JsonArray();
        if (jsonArrayOne != null && jsonArrayTwo != null) {
            jsonResultArray.addAll(jsonArrayOne);
            jsonResultArray.addAll(jsonArrayTwo);

            // printResult.setText(jsonResultArray.toString());
            // remove duplicate
            List<Data> yourList = new Gson().fromJson(jsonResultArray, ArrayList.class);
            Set<Data> set = new HashSet<>(yourList);
            yourList.clear();
            yourList.addAll(set);
            JsonArray addJsonArray = new Gson().toJsonTree(yourList).getAsJsonArray();
            printResult.setText(addJsonArray.toString());
        }
        else{
            printResult.setText("One or more array is null");
        }
    }

    private void createJsonTwo() {
        List<Data> names = new ArrayList<>();
        names.add(new Data("Amaresh","9090"));
        names.add(new Data("Mukesh","1234"));
        names.add(new Data("Ronak","0000"));
        names.add(new Data("Vaisnavi","1111"));
        for(Data data : names) {
            JsonObject item = new JsonObject();
            item.addProperty("name",data.name);
            item.addProperty("phone",data.phone);
            jsonArrayTwo.add(item);
        }
        Log.e(TAG,""+jsonArrayTwo.toString());
        tvPrintTwo.setText(jsonArrayTwo.toString());
    }

    private void createJsonOne() {
        List<Data> names = new ArrayList<>();
        names.add(new Data("Amaresh","9090"));
        names.add(new Data("Mukesh","1234"));
        names.add(new Data("NewFriend","1000"));
        for(Data data : names) {
            JsonObject item = new JsonObject();
            item.addProperty("name",data.name);
            item.addProperty("phone",data.phone);
            jsonArrayOne.add(item);
        }
        Log.e(TAG,""+jsonArrayOne.toString());
        tvPrintOne.setText(jsonArrayOne.toString());
    }
}