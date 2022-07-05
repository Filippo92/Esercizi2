package com.example.navigation_drawer;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumMenu;



    public PagerAdapter(androidx.fragment.app.FragmentManager fm, int NumMenu) {
        super(fm);
        this.mNumMenu=NumMenu;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:return new Menu_drawer();
           case 1:return new BlankFragment2();
           case 2:return new BlankFragment3();
           default: return null;
       }

    }

    @Override
    public int getCount() {
        return mNumMenu;
    }
}
