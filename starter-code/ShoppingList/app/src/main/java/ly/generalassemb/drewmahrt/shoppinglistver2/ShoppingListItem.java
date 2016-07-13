package ly.generalassemb.drewmahrt.shoppinglistver2;

/**
 * Created by Jonathan Taylor on 7/12/16.
 */
public class ShoppingListItem {
    private String mItemName;
    private String mDescription;
    private String mPrice;
    private String mType;

    public ShoppingListItem(String itemName, String description, String price, String type) {
        mItemName = itemName;
        mDescription = description;
        mPrice = price;
        mType = type;
    }

    public String getItemName() {
        return mItemName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getType() {
        return mType;
    }
}
