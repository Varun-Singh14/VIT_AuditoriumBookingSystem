package com.helloworld.vitauditoriumbookingsys.HomeAdapter.MostViewed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.HomeAdapter.Featured.FeaturedAdapter;
import com.helloworld.vitauditoriumbookingsys.R;
import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {

    ArrayList<MostViewedHelperClass> mostViewed;
    final private ListItemClickListener nOnClickListener;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewed, ListItemClickListener listener) {
        this.mostViewed = mostViewed;
        this.nOnClickListener = listener;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedAdapter.MostViewedViewHolder holder, int position) {

        MostViewedHelperClass mostViewedHelperClass = mostViewed.get(position);

        holder.image.setImageResource(mostViewedHelperClass.getImage());
        holder.title.setText(mostViewedHelperClass.getTitle());
        holder.desc.setText(mostViewedHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return mostViewed.size();
    }

    public interface ListItemClickListener {
        void onMostViewedListClick(int clickedItemIndex);
    }

    public class MostViewedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView title, desc;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //Hooks
            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);


        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            nOnClickListener.onMostViewedListClick(clickedPosition);
        }
    }

}
