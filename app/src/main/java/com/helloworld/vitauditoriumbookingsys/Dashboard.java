package com.helloworld.vitauditoriumbookingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import com.helloworld.vitauditoriumbookingsys.Common.Common;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.Categories.CategoriesAdapter;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.Categories.CategoriesHelperClass;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.Featured.FeaturedAdapter;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.Featured.FeaturedHelperClass;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.MostViewed.MostViewedAdapter;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.MostViewed.MostViewedHelperClass;
import com.helloworld.vitauditoriumbookingsys.Room.Room1;
import com.helloworld.vitauditoriumbookingsys.Room.Room2;
import com.helloworld.vitauditoriumbookingsys.Room.Room3;
import com.helloworld.vitauditoriumbookingsys.Room.Room4;
import com.helloworld.vitauditoriumbookingsys.Room.Room5;
import com.helloworld.vitauditoriumbookingsys.Room.Room6;
import java.util.ArrayList;


public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler;
    RecyclerView mostViewedRecycler;
    RecyclerView categoriesRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;
    LinearLayout contentView;
    TextView txtHeaderName, txtHeaderRegNo;
    RelativeLayout audiCard;
    RelativeLayout confiCard;
    RelativeLayout smartclCard;
    RelativeLayout eventsCard;


    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //Top Category Hooks
        audiCard = findViewById(R.id.audi_card_category);
        confiCard = findViewById(R.id.confi_card_category);
        smartclCard = findViewById(R.id.smart_card_category);
        eventsCard = findViewById(R.id.events_card_category);

        audiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AuditoriumsCategory.class);
                startActivity(intent);
            }
        });


        //Hide or Show items
        Menu menu= navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);


        navigationDrawer();
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
    }



    //Navigation Drawer Functions
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        View headerView = navigationView.getHeaderView(0);
            txtHeaderName = (TextView) headerView.findViewById(R.id.header_user_name);
            txtHeaderRegNo = (TextView) headerView.findViewById(R.id.header_user_regNo);
            txtHeaderName.setText(Common.currentUser.getName());
            txtHeaderRegNo.setText(Common.currentUser.getRegNo());


        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
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
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id==R.id.nav_home)
        {
            Intent intent = new Intent(this,Dashboard.class);
            startActivity(intent);
        } else if (id==R.id.nav_events)
        {
            Intent intent = new Intent(this,AllEvents.class);
            startActivity(intent);
        } else if (id==R.id.nav_booking)
        {
            Intent intent = new Intent(this, AllBookingTickets.class);
            startActivity(intent);
        } else if (id==R.id.nav_profile) {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        } else if (id==R.id.nav_logout) {
            logout(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    
    public static void logout(final Activity activity){
        //Initialize alert dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set Ttitle
        builder.setTitle("Logout");
        //Set Message
        builder.setMessage("Are you sure you want to logout ?");
        //Positive Yes Button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit App
                System.exit(0);
            }
        });
        //Negative No Button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss Dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }
    
    protected void onPause() {
        super.onPause();
        //Close Drawer
        Dashboard.closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
    }


    //Recycler Views Functions
    private void featuredRecycler() {

        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featuredRecycler.setHasFixedSize(true);

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();


        featuredLocations.add(new FeaturedHelperClass(R.drawable.auditorium_a, "Mahatma Gandhi Auditorium", "It is a long established fact that a reader."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.auditorium_a, "Nethaji Auditorium", "It is a long established fact that a reader."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.auditorium_a, "V.O.C. Auditorium", "It is a long established fact that a reader."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.audtiorium_b, "Conference Room - AB", "It is a long established fact that a reader."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.audtiorium_b, "RUBY (Admin Block)", "It is a long established fact that a reader."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.smart_classroom, "Smart Class", "It is a long established fact that a reader."));

        adapter = new FeaturedAdapter(featuredLocations, this::onfeaturedListClick);
        featuredRecycler.setAdapter(adapter);

    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewed = new ArrayList<>();

        mostViewed.add(new MostViewedHelperClass(R.drawable.auditorium_a, "Mahatma Gandhi Auditorium", "It is a long established fact that a reader."));
        mostViewed.add(new MostViewedHelperClass(R.drawable.audtiorium_b, "Nethaji Auditorium", "It is a long established fact that a reader."));
        mostViewed.add(new MostViewedHelperClass(R.drawable.smart_classroom, "Smart Class", "It is a long established fact that a reader."));

        adapter = new MostViewedAdapter(mostViewed, this::onMostViewedListClick);
        mostViewedRecycler.setAdapter(adapter);

    }

    private void categoriesRecycler() {

        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> categories = new ArrayList<>();

        categories.add(new CategoriesHelperClass(R.drawable.auditorium_vec_img, "Auditoriums"));
        categories.add(new CategoriesHelperClass(R.drawable.conference_vec_img, "Conference Rooms"));
        categories.add(new CategoriesHelperClass(R.drawable.smart_class_vec_img, "Smart Class"));
        categories.add(new CategoriesHelperClass(R.drawable.smart_class_vec_img, "Events"));

        adapter = new CategoriesAdapter(categories, this::onCategoriesListClick);
        categoriesRecycler.setAdapter(adapter);

    }

    public void onfeaturedListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex) {
            case 0: //First item in Recycler View
                mIntent = new Intent(Dashboard.this, Room1.class);
                startActivity(mIntent);
                break;
            case 1: //Second item in Recycler View
                mIntent = new Intent(Dashboard.this, Room2.class);
                startActivity(mIntent);
                break;
            case 2: //Third item in Recycler View
                mIntent = new Intent(Dashboard.this, Room3.class);
                startActivity(mIntent);
                break;
            case 3: //Fourth item in Recycler View
                mIntent = new Intent(Dashboard.this, Room4.class);
                startActivity(mIntent);
                break;
            case 4: //Fifth item in Recycler View
                mIntent = new Intent(Dashboard.this, Room5.class);
                startActivity(mIntent);
                break;
            case 5: //Sixth item in Recycler View
                mIntent = new Intent(Dashboard.this, Room6.class);
                startActivity(mIntent);
                break;
        }
    }

    public void onMostViewedListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex) {
            case 0: //First item in Recycler View
                mIntent = new Intent(Dashboard.this, Room1.class);
                startActivity(mIntent);
                break;
            case 1: //Second item in Recycler View
                mIntent = new Intent(Dashboard.this, Room2.class);
                startActivity(mIntent);
                break;
            case 2: //Third item in Recycler View
                mIntent = new Intent(Dashboard.this, Room6.class);
                startActivity(mIntent);
                break;
        }
    }

    public void onCategoriesListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex) {
            case 0: //First item in Recycler View
                mIntent = new Intent(Dashboard.this, Room1.class);
                startActivity(mIntent);
                break;
            case 1: //Second item in Recycler View
                mIntent = new Intent(Dashboard.this, Room2.class);
                startActivity(mIntent);
                break;
            case 2: //Third item in Recycler View
                mIntent = new Intent(Dashboard.this, Room6.class);
                startActivity(mIntent);
                break;
            case 3: //Fourth item in Recycler View
                mIntent = new Intent(Dashboard.this, Room6.class);
                startActivity(mIntent);
                break;
        }
    }


    public void auditoriumLoader(View view) {
        audiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aintent = new Intent(Dashboard.this, AuditoriumsCategory.class);
                startActivity(aintent);
            }
        });
    }

    public void conferenceLoader(View view) {
        confiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cintent = new Intent(Dashboard.this, ConferenceRoomCategory.class);
                startActivity(cintent);
            }
        });
    }

    public void smartClassLoader(View view) {
        smartclCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sintent = new Intent(Dashboard.this, SmartClassCategory.class);
                startActivity(sintent);
            }
        });
    }

    public void eventsLoader(View view) {
        eventsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eintent = new Intent(Dashboard.this, AllEvents.class);
                startActivity(eintent);
            }
        });
    }

    public void ProfileLoader(View view) {
        Intent yintent = new Intent(Dashboard.this, Profile.class);
        startActivity(yintent);
    }
}