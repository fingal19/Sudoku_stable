package com.example.fx50j.sudoku_stable.Game;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.fx50j.sudoku_stable.Game_num;
import com.example.fx50j.sudoku_stable.SQLite.SQL_Table;
import com.example.fx50j.sudoku_stable.UI.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by FX50J on 2017/1/16.
 */

public class Game_View extends View {

    public static List<Game_num> mlist = new ArrayList<Game_num>();
    public static int MODE = 0;
 //   public static int Game_Mode;
    public static SQL_Table table;
    public static int x = 0;
    public static int y = 0;

    //二维数组表示
    public static int source_1[][] = new int[9][9];
    public static int answer_1[][] = new int[9][9];
    public static int key[][] = new int[9][9];

    public Game_View(Context context) {
        super(context);
        /**
         * 每次只执行一次，invalidate方法只执行onDraw（）；
         */
        Log.e("game_view","00000000");
        table = new SQL_Table(context,"tables.db",null,1);
        SQLiteDatabase db = table.getWritableDatabase();


        x = 0;
        y = 0;

        mlist = new ArrayList<Game_num>();


        source_1 = new int[9][9];
        answer_1 = new int[9][9];
        key = new int[9][9];


        switch (MODE){
            case 0:
                Cursor cursor_history = db.query("History",null,null,null,null,null,null);
                if (cursor_history.moveToFirst()){
                    do {
                        String source = cursor_history.getString(cursor_history.getColumnIndex("source"));
                        String key = cursor_history.getString(cursor_history.getColumnIndex("key"));
                        String answer = cursor_history.getString(cursor_history.getColumnIndex("answer"));
                        int mode = cursor_history.getInt(cursor_history.getColumnIndex("mode"));

                        Game_num gameNum = new Game_num(source,key,answer,mode);
                        mlist.add(gameNum);
                    }while (cursor_history.moveToNext());
                }

                int ran_history = new Random().nextInt(mlist.size());

                MODE = mlist.get(ran_history).getMode();
 //               Game_Mode = mlist.get(ran_history).getMode();
                source_1 = fromstr(mlist.get(ran_history).getSource(),source_1);
                answer_1 = fromstr(mlist.get(ran_history).getAnswer(),answer_1);

                key = fromstr(mlist.get(ran_history).getKey(),key);
                Log.e("ran",ran_history + "");
                break;
            case 1:
                Cursor cursor_easy = db.query("Easy",null,null,null,null,null,null);
                if (cursor_easy.moveToFirst()){
                    do {
                        String source = cursor_easy.getString(cursor_easy.getColumnIndex("source"));
                        String key = cursor_easy.getString(cursor_easy.getColumnIndex("key"));

                        Game_num gameNum = new Game_num(source,key);
                        mlist.add(gameNum);
                    }while (cursor_easy.moveToNext());
                }

                int ran_easy = new Random().nextInt(mlist.size());
                source_1 = fromstr(mlist.get(ran_easy).getSource(),source_1);

                key = fromstr(mlist.get(ran_easy).getKey(),key);
                Log.e("ran",ran_easy + "");

                break;
            case 2:
                Cursor cursor_hard = db.query("Hard",null,null,null,null,null,null);
                if (cursor_hard.moveToFirst()){
                    do {
                        String source = cursor_hard.getString(cursor_hard.getColumnIndex("source"));
                        String key = cursor_hard.getString(cursor_hard.getColumnIndex("key"));

                        Game_num gameNum = new Game_num(source,key);
                        mlist.add(gameNum);
                    }while (cursor_hard.moveToNext());
                }

                int ran_hard = new Random().nextInt(mlist.size());
                source_1 = fromstr(mlist.get(ran_hard).getSource(),source_1);

                key = fromstr(mlist.get(ran_hard).getKey(),key);
                Log.e("ran",ran_hard + "");

                break;
            case 3:
                Cursor cursor_master = db.query("Master",null,null,null,null,null,null);
                if (cursor_master.moveToFirst()){
                    do {
                        String source = cursor_master.getString(cursor_master.getColumnIndex("source"));
                        String key = cursor_master.getString(cursor_master.getColumnIndex("key"));

                        Game_num gameNum = new Game_num(source,key);
                        mlist.add(gameNum);
                    }while (cursor_master.moveToNext());
                }

                int ran_master = new Random().nextInt(mlist.size());
                source_1 = fromstr(mlist.get(ran_master).getSource(),source_1);

                key = fromstr(mlist.get(ran_master).getKey(),key);
                Log.e("ran",ran_master + "");

                break;
            default:
                break;
        }

        db.close();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        Paint paint1 = new Paint();
        paint1.setARGB(255,0,0,0);
        Paint paint2 = new Paint();
        paint2.setARGB(255,150,150,150);

        Paint back_paint = new Paint();
        back_paint.setARGB(150,192,192,192);

        int height = getHeight()/9;
        int width = getWidth()/9;


        canvas.drawRect(0,0,width*3,height*3,back_paint);
        canvas.drawRect(width*3,height*3,width*6,height*6,back_paint);
        canvas.drawRect(width*6,0,width*9,height*3,back_paint);
        canvas.drawRect(0,height*6,width*3,height*9,back_paint);
        canvas.drawRect(width*6,height*6,width*9,height*9,back_paint);


        for(int i = 0;i < 10;i++){
            canvas.drawLine(0,height*i,getWidth(),height*i,paint2);

            if (i%3 == 0){
                canvas.drawLine(0,height*i+2,getWidth(),height*i+2,paint1);
            }
        }
        for(int j = 0;j < 10;j++){
            canvas.drawLine(width*j,0,width*j,getHeight(),paint2);

            if (j%3 == 0){
                canvas.drawLine(width*j+2,0,width*j+2,getHeight(),paint1);
            }
        }


        Paint paint3 = new Paint();
        paint3.setColor(Color.BLUE);
        paint3.setStrokeWidth(5);


        canvas.drawLine(width*x,height*y ,width*(x+1),height*y ,paint3);
        canvas.drawLine(width*x,height*y ,width*x,height*(y +1),paint3);
        canvas.drawLine(width*(x+1),height*y ,width*(x +1),height*(y +1),paint3);
        canvas.drawLine(width*x,height*(y +1),width*(x +1),height*(y +1),paint3);

        Paint num_paint = new Paint();
        num_paint.setColor(Color.BLACK);
        num_paint.setStyle(Paint.Style.STROKE);
        num_paint.setTextSize(height*0.75f);
        num_paint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics metrics = num_paint.getFontMetrics();
        float w = width/2;
        float h = height/2 - (metrics.ascent+metrics.descent)/2;

        //初始数据
        for (int i = 0;i < source_1.length;i++){
            for (int j = 0;j < source_1[i].length;j++){
                if (source_1[i][j] != 0){
                    canvas.drawText(String.valueOf(source_1[i][j]),j*width + w,i*height + h,num_paint);
                }
            }
        }

        //填写的数据
        Paint d_paint = new Paint();
        d_paint.setColor(Color.RED);
        d_paint.setStyle(Paint.Style.STROKE);
        d_paint.setTextSize(height*0.75f);
        d_paint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics d_metrics = d_paint.getFontMetrics();
        float d_w = width/2;
        float d_h = height/2 - (d_metrics.ascent+d_metrics.descent)/2;


        for (int i = 0;i < answer_1.length;i++){
            for (int j = 0;j < answer_1[i].length;j++){

                if (answer_1[i][j] != 0){
                    canvas.drawText(String.valueOf(answer_1[i][j]),j*width + d_w,i*height + d_h,d_paint);
                }
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        }).run();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) (event.getX()/(getWidth()/9));
        y = (int) (event.getY()/(getHeight()/9));
        invalidate();

        return super.onTouchEvent(event);

    }

    public static void init_num(int num){

        if (source_1[y][x] == 0){
            answer_1[y][x] = num;
        }else {
            answer_1[y][x] = 0;
        }
    }
    public static void clear_num(){
        answer_1[y][x] = 0;
    }

    public int[][] fromstr(String str,int[][] data){
        for (int i = 0;i < data.length;i++){
            for (int j = 0;j < data[i].length;j++){
                data[i][j] = str.charAt(9*i + j) - '0';
            }
        }

        return data;
    }


}
