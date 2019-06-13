package com.example.hangouts;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.content.Context;
import android.widget.SearchView;
import android.widget.Toast;
//import android.demo.adapters.ContactListAdapter;

import java.util.List;

import static android.widget.SearchView.*;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCES_STORAGE = "ExtraStorage";
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    public static SQLiteDatabase ContactDB;
    public static DBHelper dbHelper;
    public static final String TAG = "myLogs";
    public static int THEME = R.style.AppTheme_Green;
    public static String MODE = "Light";
    public static String COLOR = "Green";
    public static String mPhoneNumber = "";
    public static String current_countrycode = "+380";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREFERENCES_STORAGE, MODE_PRIVATE);
        THEME = sharedPreferences.getInt("THEME", R.style.AppTheme_Green);
        COLOR = THEME ==  R.style.AppTheme_Green || THEME ==  R.style.AppTheme_Green_Dark ? "Green" : "Blue";
        MODE = THEME ==  R.style.AppTheme_Green ||  THEME ==  R.style.AppTheme_Blue ? "Light" : "Dark";
        setTheme(THEME);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        ContactDB =  dbHelper.getWritableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this, dbHelper.getAllItems());
        recyclerView.setAdapter(mAdapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        if(!checkPermission(Manifest.permission.RECEIVE_SMS)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 222);
        }

        if(!checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 222);
        }
        if(checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            mPhoneNumber = tMgr.getLine1Number();
            String countryCodeValue = tMgr.getNetworkCountryIso();
            Country_code.setCountry_codes();
            current_countrycode = (String) Country_code.get_Countrycode(countryCodeValue.toUpperCase());
            mPhoneNumber = Country_code.get_Countrycode(countryCodeValue.toUpperCase()) + mPhoneNumber;
        }

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });
    }

    private void addContact()
    {
        int requestcode = 1;
        Intent myIntent = new Intent(MainActivity.this, New_contact.class);
        this.startActivityForResult(myIntent, requestcode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            updateData();
        }
    }

    public void updateData(){
        mAdapter = new MyAdapter(this, dbHelper.getAllItems());
        recyclerView.setAdapter(mAdapter);
    }

    public void updateDataSearch(List<Contact> contacts){
        Search_Adapter searchAdapter = new Search_Adapter(this, contacts);
        recyclerView.setAdapter(searchAdapter);
    }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()){
                    updateData();
                }
                List<Contact> contacts = dbHelper.search(query.toLowerCase());
                try {
                    updateDataSearch(contacts);
                }
                catch (Exception e) {
                    return false;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                updateData();
                return true;

            }

        });


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.app_bar_search:
                    Toast.makeText(this, "switch", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.Modes_menu:
                    case R.id.Light_mode:
                        MODE = "Light";
                        switch_theme(COLOR, "Light");
                        return true;
                    case R.id.Night_mode:
                        Log.d(TAG, "Dark");
                        MODE = "Dark";
                        switch_theme(COLOR, "Dark");
                        return true;
                case R.id.Design_menu:
                     case R.id.Blue_design:
                         COLOR = "Blue";
                         switch_theme(COLOR, MODE);
                        return true;
                     case R.id.Green_design:
                         COLOR = "Green";
                         switch_theme(COLOR, MODE);
                         return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
    }

    public void switch_theme(String color, String mode){
        int theme = 0;
        if (color.equals("Green") && mode.equals("Light")) {
            theme =  R.style.AppTheme_Green;
        }
        else if (color.equals("Green") && mode.equals("Dark")) {
            theme =  R.style.AppTheme_Green_Dark;
        }
        else if (color.equals("Blue") && mode.equals("Light")) {
            theme = R.style.AppTheme_Blue;
        }
        else if (color.equals("Blue") && mode.equals("Dark")){
            theme = R.style.AppTheme_Blue_Dark;
        }
        if (theme != THEME) {
            sharedPreferences = getSharedPreferences(PREFERENCES_STORAGE, MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putInt("THEME", theme);
            editor.commit();
            Intent Intent = new Intent(MainActivity.this, MainActivity.class);
            this.startActivity(Intent);
            finish();
        }
    }

}
