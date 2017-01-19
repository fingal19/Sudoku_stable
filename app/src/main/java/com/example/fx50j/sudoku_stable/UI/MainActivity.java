package com.example.fx50j.sudoku_stable.UI;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fx50j.sudoku_stable.Game_num;
import com.example.fx50j.sudoku_stable.R;
import com.example.fx50j.sudoku_stable.SQLite.SQL_Table;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_new;
    Button btn_continue;
    Button btn_record;
    Button btn_about;

    SQLiteDatabase db;

    int game_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }


    private void init() {
        btn_new = (Button) findViewById(R.id.new_game);
        btn_continue = (Button) findViewById(R.id.continue_game);
        btn_about = (Button) findViewById(R.id.about_game);
        btn_record = (Button) findViewById(R.id.record_game);

        SQL_Table tables = new SQL_Table(this,"tables.db",null,1);
        db = tables.getWritableDatabase();
        Cursor cursor = db.query("History",null,null,null,null,null,null);

        Log.e("count",cursor.getCount() + "");
        if (cursor.getCount() == 0){
            btn_continue.setEnabled(false);
        }else {
            btn_continue.setEnabled(true);
        }
        cursor.close();


        btn_continue.setOnClickListener(this);
        btn_new.setOnClickListener(this);
        btn_record.setOnClickListener(this);
        btn_about.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_game:
                Cursor cursor = db.query("History",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        game_mode = cursor.getInt(cursor.getColumnIndex("mode"));
                        Log.e("main_mode",game_mode + "");
                    }while (cursor.moveToNext());
                }
                cursor.close();

                Intent intent1 = new Intent(MainActivity.this,Game.class);
                Log.e("intent_mode",game_mode + "");
                intent1.putExtra("MODE",0);
                intent1.putExtra("Game_Mode",game_mode);
                startActivity(intent1);
                break;
            case R.id.new_game:
                Intent intent2 = new Intent(MainActivity.this,New_Game.class);
                startActivity(intent2);
                break;
            case R.id.record_game:
                Toast.makeText(this,"不做纪录了，自己手动记吧。",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_game:
                Intent intent_4 = new Intent(MainActivity.this,About.class);
                startActivity(intent_4);
                break;
            default:
                break;
        }
    }
}
