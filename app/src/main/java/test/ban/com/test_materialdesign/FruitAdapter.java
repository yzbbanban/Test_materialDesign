package test.ban.com.test_materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by brander on 2017/5/26.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context context;
    private List<Fruit> fruits;


    public FruitAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View v = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(v);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = fruits.get(position);
                Intent intent = new Intent(context, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                context.startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = fruits.get(position);
        holder.fruitText.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return (fruits != null || fruits.size() > 0) ? fruits.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitText;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.iv_image);
            fruitText = (TextView) itemView.findViewById(R.id.tv_image);
        }
    }
}
