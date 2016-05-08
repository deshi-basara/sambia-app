package com.hdm.mobileapplication.sambiaapp.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hdm.mobileapplication.sambiaapp.pages.MyAdapter;
import com.hdm.mobileapplication.sambiaapp.pages.MyViewPager;
import com.hdm.mobileapplication.sambiaapp.pages.NavBar;
import com.hdm.mobileapplication.sambiaapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hdm.mobileapplication.sambiaapp.listener.MainActivityListener;
import com.hdm.mobileapplication.sambiaapp.readers.PropertyReader;

public class MainActivity extends AppCompatActivity implements
        MainActivityListener,
        ViewPager.OnPageChangeListener {

    private final String TAG = "MainActivity";


    boolean mShowingBack = true;


    private FragmentPagerAdapter mAdapter;
    private MyViewPager mPager;
    private NavBar navBar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;


    private PropertyReader propertyReader;
    private Context context;
    private Properties properties;


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiLayout();
        initStartPage(0);

        context = this;
        propertyReader = new PropertyReader(context);
        properties = propertyReader.getMyProperties("configuration.properties");
        String a = properties.getProperty("hello");

        if( a!= null) {
            Toast.makeText(this, a, Toast.LENGTH_LONG).show();
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        initMenu(menu);
        return true;
    }





    private void changePage() {
        int currentPage = mPager.getCurrentItem();
        if (currentPage == 0) {
            setPage(1);
        } else {
            setPage(0);
        }
    }



    /*****************************
     * Listeners
     *******************************************/

    // MainActivity Listener
    @Override
    public void setPage(int i) {
        mPager.setCurrentItem(i);
        drawer.closeDrawer(GravityCompat.START);
        setHeading(i);

    }

    private void setHeading(int i) {
        setTitle(mAdapter.getPageTitle(mPager.getCurrentItem()));
        navBar.setItemChecked(i);
    }


    // OptionItems Listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_pageChange:
                changePage();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    // PageChange Listener
    @Override
    public void onPageSelected(int position) {
    // This method will be invoked when a new page becomes selected.
        setHeading(position);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    // This method will be invoked when the current page is scrolled
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    // Called when the scroll state changes:
    // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
    }








    /*****************************
     * Init
     *******************************************/


    private void intiLayout() {


        mPager = (MyViewPager) findViewById(R.id.pager);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setSwipeable(true); // Enable or disable if view is swipable
        mPager.addOnPageChangeListener(this);


        navBar = (NavBar) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }





    private void initStartPage(int i) {
        setPage(i);
    }



    private void initMenu(Menu menu) {
//        // Add either a "photo" or "finish" button to the action bar, depending on which page
//        // is currently selected.
//        MenuItem item = menu.add(Menu.NONE, R.id.item_pageChange, Menu.NONE, mShowingBack
//                ? R.string.action_photo
//                : R.string.action_info);
//
//        item.setIcon(mShowingBack
//                ? R.drawable.ic_action_photo
//                : R.drawable.ic_action_info);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

    }
}
