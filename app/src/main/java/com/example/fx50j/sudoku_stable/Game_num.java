package com.example.fx50j.sudoku_stable;

/**
 * Created by FX50J on 2017/1/16.
 */

public class Game_num {
    String source;
    String key;
    String answer;
    int mode;

    public Game_num(String source,String key){
        this.source = source;
        this.key = key;
    }
    public Game_num(String source,String key,String answer,int mode){
        this.source = source;
        this.key = key;
        this.answer = answer;
        this.mode = mode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
