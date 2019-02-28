package examples.aaronhoskins.com.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private static final String IMAGE_URL = "";
    //List of beverages that will be populated into the recycler view
    ArrayList<Beverages> beveragesArrayList;

    //Constructor for the Adapter
    public MyRecyclerViewAdapter(ArrayList<Beverages> beveragesArrayList) {
        this.beveragesArrayList = beveragesArrayList;
    }

    //Inflate the xml into active memeory (renders)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        //return new instance of the viewholder( We need to inflate(render) the view to passing
        //                                           by telling the context of where this is going
        //                                           to be rendered, the layout to inflate, the viewgroup
        //                                            to  assign it to, and if we need a root level attachment)
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bev_item, viewGroup, false));
    }

    // Once we have the viewholder, we then populated the data when we bind to the created viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        //Get the item's information which we wish to populate for that viewholder
        Beverages currentBeverageBeingPopulated = beveragesArrayList.get(position);
        //use the passed viewholder to access the items view and populate
        viewHolder.tvBevName.setText(currentBeverageBeingPopulated.getName());
        viewHolder.tvServingSize.setText(currentBeverageBeingPopulated.getServingSize());
        viewHolder.tvAdditionalInfo.setText(additionalInfo(currentBeverageBeingPopulated));
        viewHolder.rtBevRating.setRating(currentBeverageBeingPopulated.getRating());

        //Using glide to associate the web resourced image to our imageView
        Glide
                .with(viewHolder.itemView.getContext())
                .load("https://images.freeimages.com/images/large-previews/25d/eagle-1523807.jpg")
                .into(viewHolder.imgMeme);


        Log.d("TAG", "onBindViewHolder: item being rendered = " + position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), beveragesArrayList.get(position).getName() + "clicked", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putParcelable("bev", beveragesArrayList.get(position));
                Intent intentToStartDetails = new Intent(v.getContext(), DetailActivity.class);
                intentToStartDetails.putExtras(bundle);
                v.getContext().startActivity(intentToStartDetails);
            }
        });
    }

    //Add to list, notify the adapter that the info in the list has changed
    public void addBeverageToList(Beverages beverages) {
        beveragesArrayList.add(beverages);
        notifyDataSetChanged();
    }

    //Just creates a string bassed off of boolean values
    private String additionalInfo(Beverages passBeverage) {
        StringBuilder sb = new StringBuilder();
        if(passBeverage.isAlcoholic()) {
            sb.append(" GETS YOU DRUNK ");
        }
        if(passBeverage.isCaffinated()){
            sb.append(" WAKES YOU UP ");
        }
        if(passBeverage.isCarbonated()) {
            sb.append(" MAKES YOU BELCH ");
        }
        return sb.toString();
    }

    @Override
    public int getItemCount() {
        return beveragesArrayList.size();
    }


    //Inner class view container.  This container holds the views that we will use for each item.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBevName;
        TextView tvServingSize;
        TextView tvAdditionalInfo;
        ImageView imgMeme;
        RatingBar rtBevRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAdditionalInfo = itemView.findViewById(R.id.tvAdditionalInfo);
            tvBevName = itemView.findViewById(R.id.tvBevName);
            tvServingSize = itemView.findViewById(R.id.tvServingSize);
            rtBevRating = itemView.findViewById(R.id.rtRating);
            imgMeme = itemView.findViewById(R.id.imgMemeImage);
        }
    }
}
