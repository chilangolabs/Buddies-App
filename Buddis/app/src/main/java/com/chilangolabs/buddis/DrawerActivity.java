package com.chilangolabs.buddis;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

public class DrawerActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle bundle) {

        MaterialAccount account = new MaterialAccount(this.getResources(), "Aldo Alvarez", "", R.drawable.aldo, R.drawable.aldoblur);
        this.addAccount(account);
        this.addSection(newSection("Explora", new HomeFragment()));
        this.addSection(newSection("Perfil", new UserProfileFragment()));
        this.addSection(newSection("Validar SMS", new ValidationActivity()));
        this.addSection(newSection("Cerrar Sesion", new ExitFragment()));

        this.getSupportActionBar().setCustomView(R.layout.actionbarlogo);
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);

    }
}
