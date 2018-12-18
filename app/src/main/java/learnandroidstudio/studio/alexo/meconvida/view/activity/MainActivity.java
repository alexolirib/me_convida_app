package learnandroidstudio.studio.alexo.meconvida.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

import learnandroidstudio.studio.alexo.meconvida.R;
import learnandroidstudio.studio.alexo.meconvida.view.fragment.AbsentFragment;
import learnandroidstudio.studio.alexo.meconvida.view.fragment.AllInvitedFragment;
import learnandroidstudio.studio.alexo.meconvida.view.fragment.PresentFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ViewHolderMain mViewHolderMain = new ViewHolderMain();
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        this.mViewHolderMain.mFloatAddGuest = this.findViewById(R.id.float_add_guest);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //vai parar de pintar os icons\
        navigationView.setItemIconTintList(null);

        this.setListeners();

        this.startDefaultFragment();

    }

    private void startDefaultFragment() {
        Fragment fragment = null;
        Class fragmentClass = AllInvitedFragment.class;

        try {
            fragment=(Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frama_content, fragment).commit();

    }

    //Evento de voltar
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //criar menu direito do app (...)
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //trata evento de click do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    //aqui é os itens clicados no menu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //cada item irá para uma navegação diferente
        Fragment fragment = null;
        Class fragmentClass = null;

        int id = item.getItemId();
        if (id == R.id.nav_all) {
            fragmentClass = AllInvitedFragment.class;

            //evento padrão
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "all_invited_id");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "all_invited");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "menu_click");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            //customizado
            bundle = new Bundle();
            bundle.putString("menu_content", "all_invited");
            mFirebaseAnalytics.logEvent("menu_content_custom", bundle);


        } else if (id == R.id.nav_presents) {
            fragmentClass = PresentFragment.class;

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "present_id");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "present");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "menu_click");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            //customizado
            bundle = new Bundle();
            bundle.putString("menu_content", "present");
            mFirebaseAnalytics.logEvent("menu_content_custom", bundle);

        } else if (id == R.id.nav_absent) {
            fragmentClass = AbsentFragment.class;

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "absent_id");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "absent");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "menu_click");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            //customizado
            bundle = new Bundle();
            bundle.putString("menu_content", "absent");
            mFirebaseAnalytics.logEvent("menu_content_custom", bundle);

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        //assim só irá precisar inserir qual fragment você deseja instanciar
        FragmentManager fragmentManager = getSupportFragmentManager();

        //primeiro informa qual é o container que irá inserir e depois o fragmento
        fragmentManager.beginTransaction().replace(R.id.frama_content, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setListeners(){
        this.mViewHolderMain.mFloatAddGuest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.float_add_guest){
            startActivity(new Intent(MainActivity.this, GuestFormActivity.class));
        }
    }

    public static class ViewHolderMain {
        FloatingActionButton mFloatAddGuest;
    }
}
