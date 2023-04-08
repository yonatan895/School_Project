package com.example.school_app;
import android.os.Bundle;
import android.widget.ImageView;
// Picasso is used in order to retrieve images from the web
import com.squareup.picasso.Picasso;

public class JavaEcosystemActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_ecosystem);
        setupNavigationDrawer(R.id.eco_drawer_layout, getApplicationContext());
        setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
        ImageView jdk_image = findViewById(R.id.jdk_image_view);

        Picasso.get()
                .load("https://techvidvan.com/tutorials/wp-content/uploads/sites/2/2020/03/Built-in-packages-in-java.jpg")
                .into(jdk_image);



    }



}
