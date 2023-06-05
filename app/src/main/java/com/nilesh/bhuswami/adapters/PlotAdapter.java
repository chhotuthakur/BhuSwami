package com.nilesh.bhuswami.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.models.Plots;

import java.util.List;

public class PlotAdapter  extends FirebaseRecyclerAdapter<Plots,PlotAdapter.plotViewHolder>  {
   private  Context context;

   private  List<Plots>list;
   private ItemClickListener clickListener;


   //9630158652
    public PlotAdapter(@NonNull FirebaseRecyclerOptions<Plots> options) {
        super(options);
    }

    public PlotAdapter(@NonNull FirebaseRecyclerOptions<Plots> options, Context context) {
        super(options);
        this.context = context;
    }

    public PlotAdapter(@NonNull FirebaseRecyclerOptions<Plots> options, List<Plots> list) {
        super(options);
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull PlotAdapter.plotViewHolder holder, int position, @NonNull Plots model) {


        holder.title.setText(list.get(position).getTitle());
        Glide.with(holder.pimage.getContext()).load(list.get(position).getImage1_url())
                .into(holder.pimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickListener.onItemClick(list.get(position));

            }
        });
    }

    @NonNull
    @Override
    public PlotAdapter.plotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_title, parent, false);
        return new PlotAdapter.plotViewHolder(view);

    }




    public class plotViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView pimage;



        public plotViewHolder(@NonNull View itemView) {
            super(itemView);
            Fragment fragment;
            title = itemView.findViewById(R.id.info_text);
            pimage = itemView.findViewById(R.id.image_plot);



        }
    }
    public interface ItemClickListener{
        public void onItemClick(Plots plots);
    }
}
