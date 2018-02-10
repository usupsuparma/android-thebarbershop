package id.usup.thebarbershop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import id.usup.thebarbershop.fragment.HomeFragment;
import id.usup.thebarbershop.fragment.MapFragment;
import id.usup.thebarbershop.fragment.NotificationFragment;
import id.usup.thebarbershop.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationleft;
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        HomeFragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();



        //======================================================================
        headerNavigationleft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                        new ProfileDrawerItem().withName("The Barber Shop").withEmail("barbershoid@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.ic_home_24dp))).build();

        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerNavigationleft)
                .withSelectedItem(0)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.ic_home_24dp)),
                        new PrimaryDrawerItem().withName("Profile").withIcon(getResources().getDrawable(R.drawable.ic_person)),
                        new PrimaryDrawerItem().withName("Notifikasi").withIcon(getResources().getDrawable(R.drawable.ic_notifications)).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Location Add").withIcon(getResources().getDrawable(R.drawable.ic_location_on)).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Pesan").withIcon(getResources().getDrawable(R.drawable.ic_message)).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Logout").withIcon(getResources().getDrawable(R.drawable.ic_logout)).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Pemberitahuan").withIcon(getResources().getDrawable(R.drawable.ic_notifications_active)).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Setting").withIcon(getResources().getDrawable(R.drawable.ic_settings)).withIdentifier(7)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        final int nilai = position;
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        switch (nilai){
                            case 0:
                                HomeFragment fragment = new HomeFragment();
                                fragmentTransaction.replace(R.id.frameLayout,fragment);
                                fragmentTransaction.commit();
                                break;
                            case 1:
                                ProfileFragment fragmentProfile = new ProfileFragment();
                                fragmentTransaction.replace(R.id.frameLayout,fragmentProfile);
                                fragmentTransaction.commit();
                                break;
                            case 2:
                                NotificationFragment fragmentNotifikasi = new NotificationFragment();
                                fragmentTransaction.replace(R.id.frameLayout,fragmentNotifikasi);
                                fragmentTransaction.commit();
                                break;
                            case 3:
                                MapFragment mapFragment = new MapFragment();
                                fragmentTransaction.replace(R.id.frameLayout,mapFragment);
                                fragmentTransaction.commit();

                                break;
                            case 4:
                                Toast.makeText(getApplicationContext(),"nilai 4",Toast.LENGTH_LONG).show();
                                break;
                            case 5:
                                Toast.makeText(getApplicationContext(),"nilai 5",Toast.LENGTH_LONG).show();
                                break;
                            case 6:
                                Toast.makeText(getApplicationContext(),"nilai 6",Toast.LENGTH_LONG).show();
                                break;
                            case 7:
                                Toast.makeText(getApplicationContext(),"nilai 7",Toast.LENGTH_LONG).show();
                                break;
                        }

                    }
                })
                .build();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        i++;
        if (i == 1) {
            Toast.makeText(MainActivity.this, "Press back once more to exit.",
                    Toast.LENGTH_SHORT).show();
        } else if(i>1) {
            finish();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        i=0;
    }
}
