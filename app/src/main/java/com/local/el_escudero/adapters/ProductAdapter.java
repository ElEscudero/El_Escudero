package com.local.el_escudero.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.local.el_escudero.R;
import com.local.el_escudero.model.Product;
import com.local.el_escudero.picasso.PicassoCache;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mainContext;
    private List<Product> productsList;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView productName;
        TextView productPrice;
        ImageView productPhoto;
        CheckBox productSelect;

        ProductViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv);
            productPhoto = itemView.findViewById(R.id.product_photo);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productSelect = itemView.findViewById(R.id.product_select);
        }
    }

    /**
     * ProductAdapter Constructor
     *
     * @param products
     */
    public ProductAdapter(List<Product> products, Context mainContext) {
        this.productsList = products;
        this.mainContext = mainContext;
    }

    @Override
    public int getItemCount() {

       /* if (productsList != null) {
            return productsList.size();
        }*/

        return productsList.size();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);

        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


        // TODO: Este método obtiene la imagen de internet y la muestra en nuestro ImageView
       PicassoCache.getPicassoInstance(this.mainContext).load(productsList.get(position).getPhoto())
                .resize(200, 200)
                .into(holder.productPhoto);

        //holder.productPhoto.setImageBitmap(productsList.get(position).getPhoto());


        holder.productName.setText(productsList.get(position).getProduct());
        holder.productPrice.setText((String.valueOf(productsList.get(position).getPrice())));

        if (holder.productSelect.isSelected()){
            holder.productSelect.setChecked(true);
        }else{
            holder.productSelect.setChecked(false);
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
