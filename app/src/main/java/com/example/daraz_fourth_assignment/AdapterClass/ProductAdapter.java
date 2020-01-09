package com.example.daraz_fourth_assignment.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daraz_fourth_assignment.ModelClass.DarazProductModelClass;
import com.example.daraz_fourth_assignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{

    Context context;
    List<DarazProductModelClass> productModels;

    public ProductAdapter(Context context, List<DarazProductModelClass> productModals) {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_ui,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //// getting data according to position
        final DarazProductModelClass DarazProductModalClass = productModels.get(position);

        holder.txt_item_product_name.setText(DarazProductModalClass.getProduct_name());
        holder.txt_item_product_price.setText(DarazProductModalClass.getProduct_price());

        // get Product Image
        Picasso.get()
                .load("http://www.piyushp.com.np/sport_fanatic/api/member/image/daraz_image/product/"+DarazProductModalClass.getProduct_image())
                .placeholder(R.drawable.ic_launcher_background)
                .resize(220, 220)
                .centerCrop()
                .into(holder.item_product_image);
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_item_product_name,txt_item_product_price;
        ImageView item_product_image;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_item_product_name = itemView.findViewById(R.id.txtProductName);
            txt_item_product_price = itemView.findViewById(R.id.txtPrice);
            item_product_image = itemView.findViewById(R.id.imgProduct);
        }
    }
}


