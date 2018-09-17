package doordash.com.doordash.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import doordash.com.doordash.R;
import doordash.com.doordash.databinding.CustomRowBinding;
import doordash.com.doordash.service.model.RestaurantModel;

/**
 * Created by ankit on 9/15/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RestaurantModel> dataList;
    private Context context;
    private LayoutInflater layoutInflater = null;
    private CustomRowBinding customRowBinding = null;

    public CustomAdapter(Context context, List<RestaurantModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(final CustomRowBinding itemBinding) {
            super(itemBinding.getRoot());
            customRowBinding = itemBinding;
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        customRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_row, parent, false);
        return new CustomViewHolder(customRowBinding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final RestaurantModel restaurantModel = dataList.get(position);
        customRowBinding.setRestaurantData(restaurantModel);
        Picasso.with(customRowBinding.coverImage.getContext().getApplicationContext())
                .load(customRowBinding.getRestaurantData().getCoverImgUrl())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(R.drawable.ic_launcher_background)
                .config(Bitmap.Config.RGB_565)
                .into(customRowBinding.coverImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
