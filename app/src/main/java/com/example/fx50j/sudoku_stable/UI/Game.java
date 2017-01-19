package com.example.fx50j.sudoku_stable.UI;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fx50j.sudoku_stable.Game.Game_View;
import com.example.fx50j.sudoku_stable.MyFragment;
import com.example.fx50j.sudoku_stable.R;

import java.util.ArrayList;
import java.util.List;

public class Game extends AppCompatActivity implements View.OnClickListener{

    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_10;

    TextView tv_mode;
    TextView tv_time;

    int game_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        game_mode = intent.getIntExtra("Game_Mode",0);
        Log.e("Game_1",game_mode + "");
        Game_View.MODE = intent.getIntExtra("MODE",0);

        Log.e("MODE",Game_View.MODE + "");
        initView();

    }

    private void initView() {

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = new MyFragment();

        manager.beginTransaction()
                .add(R.id.game_fragment,fragment)
                .commit();

        getSupportActionBar().hide();

        tv_mode = (TextView) findViewById(R.id.tv_mode);
        tv_time = (TextView) findViewById(R.id.tv_time);

        Log.e("game_2",game_mode + "");
        switch (game_mode){
            case 1:
                tv_mode.setText("难度：简单");
                break;
            case 2:
                tv_mode.setText("难度：困难");
                break;
            case 3:
                tv_mode.setText("难度：困难+");
                break;
            default:
                break;
        }

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_10 = (Button) findViewById(R.id.btn_10);


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_10.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                Game_View.init_num(1);
                check_answer();
                break;
            case R.id.btn_2:
                Game_View.init_num(2);
                check_answer();
                break;
            case R.id.btn_3:
                Game_View.init_num(3);
                check_answer();
                break;
            case R.id.btn_4:
                Game_View.init_num(4);
                check_answer();
                break;
            case R.id.btn_5:
                Game_View.init_num(5);
                check_answer();
                break;
            case R.id.btn_6:
                Game_View.init_num(6);
                check_answer();
                break;
            case R.id.btn_7:
                Game_View.init_num(7);
                check_answer();
                break;
            case R.id.btn_8:
                Game_View.init_num(8);
                check_answer();
                break;
            case R.id.btn_9:
                Game_View.init_num(9);
                check_answer();
                break;
            case R.id.btn_10:
                Game_View.clear_num();
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {

        Log.e("destroy","destroy");
        SQLiteDatabase db = Game_View.table.getWritableDatabase();
        db.delete("History","tag = ?",new String[]{"1"});

        ContentValues contentValues = new ContentValues();
        Log.e("sql",game_mode + "");
        contentValues.put("mode",game_mode);
        contentValues.put("tag",1);
        contentValues.put("source", strfromint(Game_View.source_1));
        contentValues.put("key",strfromint(Game_View.key));
        contentValues.put("answer",strfromint(Game_View.answer_1));
        db.insert("History",null,contentValues);
        contentValues.clear();
        db.close();

        super.onDestroy();
    }

    public String strfromint(int[][] num){
        String str = "";
        StringBuffer sb = new StringBuffer();

        for (int i = 0;i < num.length;i++){
            for (int j = 0;j < num[i].length;j++){
                sb.append(num[i][j]);
            }
        }
        str = String.valueOf(sb);
        return str;
    }

    //检查答案
    public void check_answer(){

        String key = strfromint(Game_View.key);
        String answer = strfromint(Game_View.answer_1);
        if (key.equals(answer)){
            Toast.makeText(Game.this,"游戏成功,...",Toast.LENGTH_SHORT).show();
            Log.e("game_over","完成游戏");


            Dialog dialog = new AlertDialog.Builder(Game.this)
                    .setTitle("完成游戏")
                    .setMessage("恭喜，很棒棒哦!!")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .create();
            dialog.show();

        }else {
            Log.e("answer",answer + "");
            Log.e("key",key + "");
            Log.e("game_over","游戏失败");
        }

    }
}
