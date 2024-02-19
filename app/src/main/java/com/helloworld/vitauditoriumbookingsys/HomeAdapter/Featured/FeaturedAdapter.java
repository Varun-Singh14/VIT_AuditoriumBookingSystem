package com.helloworld.vitauditoriumbookingsys.HomeAdapter.Featured;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.helloworld.vitauditoriumbookingsys.R;
import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> featuredLocations;
    final private ListItemClickListener mOnClickListener;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations, ListItemClickListener listener) {
        this.featuredLocations = featuredLocations;
        this.mOnClickListener = listener;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedAdapter.FeaturedViewHolder holder, int position) {

        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public interface ListItemClickListener {
        void onfeaturedListClick(int clickedItemIndex);
    }


    public class FeaturedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title, desc;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            desc = itemView.findViewById(R.id.featured_desc);


        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onfeaturedListClick(clickedPosition);
        }
    }





}
