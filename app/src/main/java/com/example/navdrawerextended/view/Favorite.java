package com.example.navdrawerextended.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navdrawerextended.R;

public class Favorite extends Fragment {
    TextView comingSoonTxt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_favorite,container,false) ;
        comingSoonTxt = view.findViewById(R.id.comingSoonTxt);
        return view;
    }
}
