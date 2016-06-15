package photoclient.lazada.com.photoclient.activity;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.pedant.SweetAlert.SweetAlertDialog;
import photoclient.lazada.com.photoclient.R;
import photoclient.lazada.com.photoclient.adapters.PhotoPreviewAdapter;
import photoclient.lazada.com.photoclient.model.DetailPhotoObject;
import photoclient.lazada.com.photoclient.model.Photo;
import photoclient.lazada.com.photoclient.model.Photos;
import photoclient.lazada.com.photoclient.net.RetrofitInstance;
import photoclient.lazada.com.photoclient.net.Routes;
import photoclient.lazada.com.photoclient.utility.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * When Showing the detail, show the best quality image
 */
public class PhotoDetailActivity extends AppCompatActivity {

    private ImageView mDetailImage;
    private TextView mCopyright;
    Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        photo = (Photo) getIntent().getSerializableExtra(Constants.PHOTO);
        getSupportActionBar().setTitle(photo.getName());
        mDetailImage = (ImageView) findViewById(R.id.detailImageView);
        mCopyright = (TextView) findViewById(R.id.copyrightText);

        mCopyright.append(photo.getUser().getFullname() + "/ 500px");

//        Picasso.with(this)
//                .load(photo.getImage_url())
//                .placeholder(R.drawable.no_image)
//                .error(R.drawable.no_image)
//                .into(mDetailImage);


        Routes instance = RetrofitInstance.getInstance().createService(Routes.class);

        Call<DetailPhotoObject> call = instance.getPhotos(Integer.valueOf(photo.getId()));
        call.enqueue(new Callback<DetailPhotoObject>() {
            @Override
            public void onResponse(Call<DetailPhotoObject> call, Response<DetailPhotoObject> response) {
                Picasso.with(PhotoDetailActivity.this)
                        .load(response.body().getPhoto().getImage_url())
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image)
                        .into(mDetailImage);
            }

            @Override
            public void onFailure(Call<DetailPhotoObject> call, Throwable t) {
                new SweetAlertDialog(PhotoDetailActivity.this, SweetAlertDialog.ERROR_TYPE)
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
