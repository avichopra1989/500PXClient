package photoclient.lazada.com.photoclient.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import photoclient.lazada.com.photoclient.R;
import photoclient.lazada.com.photoclient.activity.PhotoDetailActivity;
import photoclient.lazada.com.photoclient.model.Photo;
import photoclient.lazada.com.photoclient.utility.Constants;

/**
 * Created by avi_chopra on 16/06/16.
 */
public class PhotoPreviewAdapter extends RecyclerView.Adapter<PhotoPreviewAdapter.ViewHolder> {

    private static List<Photo> mPhotos;

    public PhotoPreviewAdapter(List<Photo> photos){
        mPhotos = photos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mAuthorName;
        public TextView mDesc;
        public ImageView mPreview;
        public RelativeLayout collectiveLayout;

        public ViewHolder(View v){
            super(v);
            collectiveLayout = (RelativeLayout) v.findViewById(R.id.collectiveLayout);
            mAuthorName = (TextView) v.findViewById(R.id.authorNameTextView);
            mDesc = (TextView) v.findViewById(R.id.photoNameTextView);
            mPreview = (ImageView) v.findViewById(R.id.previewImageView);

            collectiveLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(view.getContext(), PhotoDetailActivity.class);
                    in.putExtra(Constants.PHOTO, mPhotos.get(getPosition()));
                    view.getContext().startActivity(in);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_preview, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mDesc.setText(mPhotos.get(position).getName());
        holder.mAuthorName.setText(mPhotos.get(position).getUser().getFullname());
        Picasso.with(holder.mPreview.getContext())
                .load(mPhotos.get(position).getImage_url())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.mPreview);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
