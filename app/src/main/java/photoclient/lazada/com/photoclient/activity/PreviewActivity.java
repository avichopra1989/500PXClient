package photoclient.lazada.com.photoclient.activity;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import cn.pedant.SweetAlert.SweetAlertDialog;
import photoclient.lazada.com.photoclient.R;
import photoclient.lazada.com.photoclient.adapters.CategoriesAdapter;
import photoclient.lazada.com.photoclient.adapters.PhotoPreviewAdapter;
import photoclient.lazada.com.photoclient.model.Photos;
import photoclient.lazada.com.photoclient.net.RetrofitInstance;
import photoclient.lazada.com.photoclient.net.Routes;
import photoclient.lazada.com.photoclient.utility.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviewActivity extends AppCompatActivity {

    private RecyclerView mPreviewList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        int category = getIntent().getIntExtra(Constants.CategoryName, 0);
        mPreviewList = (RecyclerView) findViewById(R.id.previewList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPreviewList.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mPreviewList.setLayoutManager(mLayoutManager);

        Routes instance = RetrofitInstance.getInstance().createService(Routes.class);

        Call<Photos> call = instance.getPhotos(Constants.categoriesList[category]);
        call.enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                mAdapter = new PhotoPreviewAdapter(response.body().getPhotos());
                mPreviewList.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                t.printStackTrace();
                new SweetAlertDialog(PreviewActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
