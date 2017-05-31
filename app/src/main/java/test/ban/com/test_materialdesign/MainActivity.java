package test.ban.com.test_materialdesign;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private FruitAdapter adapter;
    private RecyclerView mRecyclerView;
    private List<Fruit> fruitList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private Fruit[] fruits = {
            new Fruit("apple:1", R.drawable.img1), new Fruit("apple:2", R.drawable.img2),
            new Fruit("apple:3", R.drawable.img3), new Fruit("apple:4", R.drawable.img4),
            new Fruit("apple:5", R.drawable.img5), new Fruit("apple:6", R.drawable.img6),
            new Fruit("apple:7", R.drawable.img7), new Fruit("apple:8", R.drawable.img8),
            new Fruit("apple:9", R.drawable.img9), new Fruit("apple:10", R.drawable.img10),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher_round);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                mDrawerLayout.openDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this, "call", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this, "friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this, "location", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this, "mail", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(MainActivity.this, "task", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "snack bar click", Snackbar.LENGTH_SHORT)
                        .setAction("undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        initData();
        adapter = new FruitAdapter(fruitList);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });


            }
        }).start();
    }


    private void initData() {
        if (fruitList != null && fruitList.size() > 0) {
            fruitList.clear();
        }
        for (int i = 0; i < 50; i++) {
            int index = new Random().nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}
