package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/12/16.
 */
public class GroceryRecyclerViewAdapter extends RecyclerView.Adapter<GroceryListViewHolder> {

    private ArrayList<ShoppingListItem> mList;
    private Context mContext;

    public GroceryRecyclerViewAdapter(Context context, ArrayList list){
        mContext = context;
        mList = list;
    }


    @Override
    public GroceryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shopping_inner_layout, parent, false);
        return new GroceryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroceryListViewHolder holder, int position) {

        holder.setNameText(mList.get(position).getItemName());
        holder.setDescText(mList.get(position).getDescription());
        holder.setPriceText(mList.get(position).getPrice());
        holder.setTypeText(mList.get(position).getType());

        //How do I notify it on changes?
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class GroceryListViewHolder extends RecyclerView.ViewHolder{

    private TextView nameText;
    private TextView descText;
    private TextView priceText;
    private TextView typeText;

    public GroceryListViewHolder(View itemView) {
        super(itemView);
        nameText = (TextView) itemView.findViewById(R.id.name_text);
        descText = (TextView) itemView.findViewById(R.id.description_text);
        priceText = (TextView) itemView.findViewById(R.id.price_text);
        typeText = (TextView) itemView.findViewById(R.id.type_text);
    }
    
    public String getNameText(){
        return nameText.getText().toString();
    }

    public String getDescText(){
        return descText.getText().toString();
    }

    public String getPriceText(){
        return priceText.getText().toString();
    }

    public String getTypeText(){
        return typeText.getText().toString();
    }
    
    public void setNameText(String name){
        nameText.setText(name);
    }

    public void setDescText(String desc){
        descText.setText(desc);
    }

    public void setPriceText(String price){
        priceText.setText(price);
    }

    public void setTypeText(String type){
        typeText.setText(type);
    }
}
