package com.example.navdrawerextended.view;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navdrawerextended.R;
import com.example.navdrawerextended.controller.NavigationController;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final float END_SCALE = 0.7f;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private View contentView;
    private NavigationController navigation;
    private ImageView menu_back;
    private TextView menuHeading;


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            navigation.backPressFrag();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = new NavigationController(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        contentView = findViewById(R.id.content);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        menu_back = header.findViewById(R.id.menu_back);
        menuHeading = header.findViewById(R.id.menuHeading);
        toolbar.setNavigationIcon(new DrawerArrowDrawable(this));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     if (drawerLayout.isDrawerOpen(navigationView)) {
                                                         drawerLayout.closeDrawer(navigationView);
                                                     } else {
                                                         drawerLayout.openDrawer(navigationView);
                                                     }
                                                 }
                                             }
        );

        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerSlide(View drawerView, float slideOffset) {

                                               // Scale the View based on current slide offset
                                               final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                                               final float offsetScale = 1 - diffScaledOffset;
                                               contentView.setScaleX(offsetScale);
                                               contentView.setScaleY(offsetScale);

                                               // Translate the View, accounting for the scaled width
                                               final float xOffset = drawerView.getWidth() * slideOffset;
                                               final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                                               final float xTranslation = xOffset - xOffsetDiff;
                                               contentView.setTranslationX(xTranslation);
                                           }

                                           @Override
                                           public void onDrawerClosed(View drawerView) {

                                           }
                                       }
        );
        fragment(new Favorite());
        menu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.menu_items);
                menu_back.setVisibility(View.GONE);
                menuHeading.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        disPlaySelectedMenu(menuItem.getItemId());
        return false;
    }

    private void disPlaySelectedMenu(int itemId) {
        switch (itemId) {
            case R.id.nav_favorites:
                Toast.makeText(this, "Fav Clicked", Toast.LENGTH_SHORT).show();
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.submenu_items);
                menu_back.setVisibility(View.VISIBLE);
                menuHeading.setVisibility(View.VISIBLE);
                menuHeading.setText(R.string.favorites);
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.nav_about:
                Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();
                fragment(new Favorite());
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                fragment(new Favorite());
                break;
            case R.id.nav_signOut:
                Toast.makeText(this, "Sign Out Clicked", Toast.LENGTH_SHORT).show();
                fragment(new Favorite());
                break;
            case R.id.nav_movies:
                Toast.makeText(this, "Movies Clicked", Toast.LENGTH_SHORT).show();
                fragment(new Favorite());
                break;
            case R.id.nav_news:
                Toast.makeText(this, "News Clicked", Toast.LENGTH_SHORT).show();
                fragment(new Favorite());
                break;
            case R.id.nav_music:
                Toast.makeText(this, "Music Clicked", Toast.LENGTH_SHORT).show();
                fragment(new Favorite());
                break;
        }
    }

    private void fragment(Fragment fragment){
        navigation.replaceFragment(R.id.main_content,fragment);
    }
}