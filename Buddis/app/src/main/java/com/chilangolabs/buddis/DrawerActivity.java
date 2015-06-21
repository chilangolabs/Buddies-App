package com.chilangolabs.buddis;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

public class DrawerActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle bundle) {

        MaterialAccount account = new MaterialAccount(this.getResources(), "Aldo Alvarez", "", R.drawable.imagetest, R.drawable.imagetest);
        this.addAccount(account);
        this.addSection(newSection("Home", new HomeFragment()));
        this.addSection(newSection("Home", new HomeFragment()));
        this.addSection(newSection("Home", new HomeFragment()));
        this.addSection(newSection("Home", new HomeFragment()));

        this.getSupportActionBar().setCustomView(R.layout.actionbarlogo);
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);

    }
}
