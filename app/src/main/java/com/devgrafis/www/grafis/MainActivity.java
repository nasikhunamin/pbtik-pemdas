package com.devgrafis.www.grafis;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.devgrafis.www.grafis.sharedpreferences.SaveSharedPreferences;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment;
    private NavigationView navigationView;
    private EditText search;
    private Dialog dialogResult;
    //private ImageView help;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            search = findViewById(R.id.seacrh);
            //help = findViewById(R.id.helpMain);
            search.setFocusable(false);
            search.setFocusableInTouchMode(false);
            dialogResult = new Dialog(this);

            /*help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                }
            });*/
            search.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    /*Rect editTextRect = new Rect();
                    search.getHitRect(editTextRect);
                    if(!editTextRect.contains((int)event.getX(), (int)event.getY())){
                        search.setFocusable(false);
                        search.setFocusableInTouchMode(false);
                    }else{
                        search.setFocusable(true);
                        search.setFocusableInTouchMode(true);
                    }*/
                    search.setEnabled(true);
                    search.setFocusable(true);
                    search.setFocusableInTouchMode(true);
                    return false;
                }
            });

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            final ImageView profil = findViewById(R.id.profil);
            setSupportActionBar(toolbar);
            navigationView = findViewById(R.id.nav_view);

            profil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                    showPopupMenuProfil(profil);
                }
            });
            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabButton);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                    showPopupMenu(fab);
                }
            });
            selectedFragemnt(R.id.material);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            /*if(drawer != null){
                drawer.addDrawerListener(toogle);
            }*/
            drawer.addDrawerListener(toogle);
            toogle.syncState();
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        } catch (Exception ex) {

        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int id = item.getItemId();
        selectedFragemnt(id);
        return true;
    }


    private void selectedFragemnt(int id) {
        if (id == R.id.material) {
            fragment = new MaterialFragment();
        } else if (id == R.id.quiz) {
            fragment = new QuizFragment();
        } else if (id == R.id.video) {
            fragment = new VideoFragment();
        /*} else if (id == R.id.index) {
            fragment = new IndexFragment();*/
        }
        // Communication
        else if (id == R.id.message) {
            fragment = new MessageFragment();
        } else if (id == R.id.group) {
            fragment = new GroupFragment();
        }

        // Help
        else if (id == R.id.help) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            search.setFocusable(false);
            search.setFocusableInTouchMode(false);
            ft.replace(R.id.contentFrameLayout, fragment);
            ft.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(getApplicationContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_fab, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.messageFab:
                        showDialog();
                        break;
                    case R.id.groupFab:
                        showDialog();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    private void showPopupMenuProfil(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(getApplicationContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_profile, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        finish();
                        SaveSharedPreferences.setLoggedIn(MainActivity.this, false);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        break;
                    case R.id.editProfile:
                        showDialog();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    public void showDialog(){
        dialogResult.setContentView(R.layout.dialog_info);
        ImageView closeDialog = dialogResult.findViewById(R.id.closeDialog);

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogResult.dismiss();
            }
        });
        dialogResult.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogResult.show();
    }

}
