package com.app.notapsavhll.appnotas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.notapsavhll.appnotas.Datos.Nota;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;
    private Nota[] dummyModels0;
    private Nota[] dummyModels1;


    public PagerAdapter(FragmentManager fm, int numTabs, Nota[] dummyModels0, Nota[] dummyModels1){

        super(fm);
        this.numTabs = numTabs;
        this.dummyModels0 = dummyModels0;
        this.dummyModels1 = dummyModels1;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ItemFragment tab1 = ItemFragment.newInstance(dummyModels0);
                return tab1;
            case 1:
                ItemFragment tab2 = ItemFragment.newInstance(dummyModels1);
                return tab2;
            default:
                throw new RuntimeException("Tab position invalid " + position);
        }

    }

    @Override
    public int getCount() {
        return numTabs;
    }

}
