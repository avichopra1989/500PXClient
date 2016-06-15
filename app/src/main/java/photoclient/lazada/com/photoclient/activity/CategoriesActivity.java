package photoclient.lazada.com.photoclient.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import photoclient.lazada.com.photoclient.R;
import photoclient.lazada.com.photoclient.adapters.CategoriesAdapter;
import photoclient.lazada.com.photoclient.utility.Constants;

/**
 * Show the categories supported
 */
public class CategoriesActivity extends AppCompatActivity {

    private RecyclerView mCategoriesList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getSupportActionBar().setTitle(getResources().getString(R.string.categoryActivity));
        mCategoriesList = (RecyclerView) findViewById(R.id.categoriesList);

        mCategoriesList.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mCategoriesList.setLayoutManager(mLayoutManager);

        mAdapter = new CategoriesAdapter(Constants.categoriesList);
        mCategoriesList.setAdapter(mAdapter);
    }
}
