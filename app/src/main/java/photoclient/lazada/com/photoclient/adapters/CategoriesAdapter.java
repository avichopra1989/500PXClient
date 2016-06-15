package photoclient.lazada.com.photoclient.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import photoclient.lazada.com.photoclient.R;
import photoclient.lazada.com.photoclient.activity.PreviewActivity;
import photoclient.lazada.com.photoclient.utility.Constants;

/**
 * Created by avi_chopra on 16/06/16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private String[] mCategoriesList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.categoryTextView);

            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(view.getContext(), PreviewActivity.class);
                    in.putExtra(Constants.CategoryName, getPosition());
                    view.getContext().startActivity(in);
                }
            });
        }
    }

    public CategoriesAdapter(String[] categoriesList){
        mCategoriesList = categoriesList;
}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mCategoriesList[position].equals("Uncategorized")){
            holder.mTextView.setText("All");
        }else{
            holder.mTextView.setText(mCategoriesList[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.length;
    }
}
