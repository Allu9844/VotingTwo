package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseLongArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;
import com.guruprasadhiremathgmail.bmsit.activity.activity.manager.LoginManager;
import com.guruprasadhiremathgmail.bmsit.activity.activity.services.GFGDestinationLocationFetchService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = DashboardActivity.class.getSimpleName();

    private RecyclerView mScheduleRecyclerView;
   // private ArrayList<ScheduleData> mScheduleData;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mNavigationUsername, mLoadAvailable, mLoadRequest, mLoadApproved,mLoadRejected;
    private String mLoadApprovedQuantity, mLoadAvailableQuantity, mLoadRequestedQuantity;
    private ProgressDialog mDialog;
    private ProgressDialog mAppDialog;
    private String mVerNameApp, mVersionPath, mVerNameServer;
    private static boolean mRestart = false;
    private static boolean mStoragePerm = false;
    private TextView noSchedule;
    private  NotificationManager nManager;
    private  TextView mTodaysSummary;
    private boolean isMember=false;
    private CardView mCardView;
    public LoginManager lm;


    private TextView mVersionText,mIntro,mIntro2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.activity_dashboard);


        GFGDestinationLocationFetchService.start(this);

        lm=new LoginManager(this);
//
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        View headerLayout = navigationView.getRootView();


        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mVerNameApp = info.versionName;


//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Vote");

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        Log.i("test","iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii-----------------"+lm.isTokenAdded()+"   "+lm.getIstokenGen());


        stateAndDistict();

    }
//    public  boolean isStoragePermissionGranted() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    == PackageManager.PERMISSION_GRANTED) {
//                Log.v(TAG,"Permission is granted");
//                getAppUpdateIfAny();
//                return true;
//            } else {
//                Log.v(TAG,"Permission is revoked");
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                return false;
//            }
//        } else { //permission is automatically granted on sdk<23 upon installation
//            Log.v(TAG,"Permission is granted");
//            return true;
//        }
//    }



    public static void isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
        } else {
            // not connected to the internet
            Toast.makeText(context, context.getResources().getString(R.string.notContoInt), Toast.LENGTH_LONG).show();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.add_order) {
//          //  startActivity(new Intent(this, AddOrderActivity.class));
//        }
//        else if(id == R.id.department)
//        {
//
////            startActivity(new Intent(this,Department.class));
//
//        }
//        else if(id == R.id.canteen)
//        {
//
////            startActivity(new Intent(this,CanteenActivity.class));
//
//        }
//        else if(id == R.id.placement)
//        {
//
////            startActivity(new Intent(this,PlacementActivity.class));
//
//        }
//        else if(id == R.id.admisions)
//        {
//
////            startActivity(new Intent(this,AdmisionActivity.class));
//
//        }
//        else if(id == R.id.timeline)
//        {
//
////            startActivity(new Intent(this,TimelineActivity.class));
//
//        }
//        else if(id == R.id.developer)
//        {
//
////            startActivity(new Intent(this,DeveloperActivity.class));
//
//        }
//        else if(id == R.id.about)
//        {
//
////            startActivity(new Intent(this,AboutActivity.class));
//
//        }
//        else if(id == R.id.facility)
//        {
//
////            startActivity(new Intent(this,Facilities.class));
//
//        }
//        else if(id == R.id.bmsitlife)
//        {
//
////            startActivity(new Intent(this,BmsitLifeActivity.class));
//
//        }
//        else if(id == R.id.coe)
//        {
//
////            startActivity(new Intent(this,COEActivity.class));
//        }
//        else if(id == R.id.nearbyyou)
//        {
//
////            startActivity(new Intent(this,NearByYouActivity.class));
//        }
//        else if(id == R.id.workshop)
//        {
//
////            startActivity(new Intent(this,WorkshopActivity.class));
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    public  void addTokenToDB()
    {
//        RestAdapter.Builder builder = new RestAdapter.Builder();
//        builder.setEndpoint("http://codeminds.xyz");
////        String credentials = String.format("%s:%s", getResources().getString(R.string.username), getResources().getString(R.string.password));
////        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
////        builder.setRequestInterceptor(new RequestInterceptor() {
////            @Override
////            public void intercept(RequestFacade request) {
////                request.addHeader("Authorization", basic);
////                request.addHeader("Accept", "application/json");
////            }
////        });
//        RestAdapter restAdapter = builder.build();
//        RegisterFcm api = restAdapter.create(RegisterFcm.class);
//
//        api.register(FirebaseInstanceId.getInstance().getToken(), new Callback<String>() {
//
//            @Override
//            public void success(String s, Response response) {
//                lm.setFirstTimeLaunch(true);
//                lm.setIsTokenGen(false);
//                Log.i("Success","--------------------------------------------------------------------");
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("Retro Error","-----------------------------------------------------"+error.toString());
//            }
//        });
    }

    public void vote(View view){

        startActivity(new Intent(this,VoteActivity.class));
    }

    public void result(View view){

        startActivity(new Intent(this,ResultActivity.class));
    }

    public void stateAndDistict(){




    }

}
