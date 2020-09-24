package com.pumpkinapplabs.ordexpress.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pumpkinapplabs.ordexpress.R;
import com.pumpkinapplabs.ordexpress.data.model.FavoriteModelClass;

import java.util.List;

public class FavoriteRecycleAdapter extends RecyclerView.Adapter<FavoriteRecycleAdapter.MyViewHolder>{

    Context context;


    private List<FavoriteModelClass> OfferList;

    boolean showingfirst = true;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView title;


        public MyViewHolder(View view) {
            super(view);

            image = (ImageView)view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);


        }

    }
    public FavoriteRecycleAdapter(Context context, List<FavoriteModelClass> offerList) {
        this.OfferList = offerList;
        this.context = context;
    }

    @Override
    public FavoriteRecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categories, parent, false);


        return new FavoriteRecycleAdapter.MyViewHolder(itemView);




    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final FavoriteModelClass lists = OfferList.get(position);
        holder.image.setImageResource(lists.getImage());
        holder.title.setText(lists.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return OfferList.size();

    }

}
