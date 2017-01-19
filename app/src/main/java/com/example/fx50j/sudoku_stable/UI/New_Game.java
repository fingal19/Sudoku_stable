package com.example.fx50j.sudoku_stable.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fx50j.sudoku_stable.R;

public class New_Game extends AppCompatActivity implements View.OnClickListener{

    Button btn_easy;
    Button btn_hard;
    Button btn_master;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__game);

        init();

    }

    private void init() {
        btn_easy = (Button) findViewById(R.id.easy);
        btn_hard = (Button) findViewById(R.id.hard);
        btn_master = (Button) findViewById(R.id.master);

        btn_easy.setOnClickListener(this);
        btn_hard.setOnClickListener(this);
        btn_master.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.easy:
                Intent intent1 = new Intent(New_Game.this,Game.class);
                intent1.putExtra("MODE",1);
                intent1.putExtra("Game_Mode",1);
                startActivity(intent1);
                break;
            case R.id.hard:
                Intent intent2 = new Intent(New_Game.this,Game.class);
                intent2.putExtra("MODE",2);
                intent2.putExtra("Game_Mode",2);
                startActivity(intent2);
                break;
            case R.id.master:
                Intent intent3 = new Intent(New_Game.this,Game.class);
                intent3.putExtra("MODE",3);
                intent3.putExtra("Game_Mode",3);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
