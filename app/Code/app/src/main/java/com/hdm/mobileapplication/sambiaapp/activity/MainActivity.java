package com.hdm.mobileapplication.sambiaapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hdm.mobileapplication.sambiaapp.pages.MyAdapter;
import com.hdm.mobileapplication.sambiaapp.pages.MyViewPager;
import com.hdm.mobileapplication.sambiaapp.pages.NavBar;
import com.hdm.mobileapplication.sambiaapp.R;

import com.hdm.mobileapplication.sambiaapp.listener.MainActivityListener;

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

        initConfiguration();
        intiLayout();
        initStartPage(0);

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


    // Listen from ChooseActivityFragment if CurrentActivity has changed
    @Override
    public void setCurrentActivity(String currentActivity) {
        EventManager.getInstance().setCurrentActivity(currentActivity);

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






    private void initConfiguration() {

        EventManager.init();
        FileHandler fl = new FileHandler(this);
        fl.initPropertyReader();

//        // Create Main Folder
//        String mainFolder = fl.getPropertiesFromAssets("configuration.properties")
//                .getProperty("mainFolder");
//        fl.createExternalFolder(mainFolder);

        // Create Main ImageFolder
        String imageFolder = fl.getPropertiesFromAssets("configuration.properties")
                .getProperty("imageFolder");
        fl.createExternalFolder(imageFolder);


        String jsonString = fl.readFromAssets(this, "activitys.json");
        MyJsonParser mJasonParser = new MyJsonParser();
        mJasonParser.createOjectFromJson("activitys", jsonString);



        int a = R.drawable.onfarmwork_bagging;
        int b = R.drawable.onfarmwork_weeding;
        int[] resources = new int[] {a, b};
        fl.CopyImagesFromResourceToStorage(resources, imageFolder);



        EventManager.getInstance().createImageMap();


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        options.inSampleSize = 4; //reduce quality

        String imgPath = Environment.getExternalStorageDirectory() + "/" + "SambiaApp/Images/" + "onfarmwork_bagging.png";
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
        EventManager.getInstance().putImage("onfarmwork_bagging", bitmap);
    }








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
    }





    private void initStartPage(int i) {
        setPage(i);
    }



    private void initMenu(Menu menu) {
        // Add either a "photo" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.item_pageChange, Menu.NONE, mShowingBack
                ? R.string.action_photo
                : R.string.action_info);

        item.setIcon(mShowingBack
                ? R.drawable.ic_action_photo
                : R.drawable.ic_action_info);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

}
