package com.example.appmakeuppam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewProducts extends BaseAdapter {
    private final int layout;
    private final Context context;
    List<Product> listProducts;

    public ListViewProducts(Context context, int layout, List<Product> listProducts){
        this.layout = layout;
        this.context = context;
        this.listProducts = listProducts;
    }

    @Override
    public int getCount(){
        return listProducts.size();
    }

    @Override
    public Object getItem(int position){
        return listProducts.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    private class ViewHolder{
        TextView txtName;
        ImageView imgMakeupItem;
        Button btnViewItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ListViewProducts.ViewHolder holder = new ListViewProducts.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = row.findViewById(R.id.txtName);
            holder.imgMakeupItem = row.findViewById(R.id.imgMakeupItem);
            holder.btnViewItem = row.findViewById(R.id.btnViewItem);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Product product = listProducts.get(position);

//        holder.btnViewItem.setOnClickListener(v -> {
//            Intent intentOpen = new Intent(context, ProductInfosActivity.class);
//            intentOpen.putExtra("Product", product);
//            context.startActivity(intentOpen);
//        });

        holder.txtName.setText(product.getName());

        byte[] imgProduct = product.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgProduct, 0, imgProduct.length);
        holder.imgMakeupItem.setImageBitmap(bitmap);

        return row;
    }
}
