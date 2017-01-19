package com.example.fx50j.sudoku_stable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fx50j.sudoku_stable.Game.Game_View;
import com.example.fx50j.sudoku_stable.UI.Game;

/**
 * Created by FX50J on 2017/1/16.
 */

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = new Game_View(getContext());
        return view;
    }
}
