package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/12/16.
 */
public class GroceryListSQLiteHelper extends SQLiteOpenHelper{

    private static GroceryListSQLiteHelper ourInstance;

    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final int DATABASE_VERSION = 7;

    public static final String SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST";
    public static final String COL_GROCERY_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";


    public static GroceryListSQLiteHelper getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new GroceryListSQLiteHelper(context);
        }
        return ourInstance;
    }

    private GroceryListSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createString = "CREATE TABLE " + SHOPPING_LIST_TABLE_NAME + " (" +
                COL_GROCERY_ID + " INTEGER PRIMARY KEY, " +
                COL_ITEM_NAME + " TEXT, " +
                COL_DESCRIPTION + " TEXT," +
                COL_PRICE + " TEXT," +
                COL_TYPE + " TEXT)";

        db.execSQL(createString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOPPING_LIST_TABLE_NAME);
        onCreate(db);
    }

    public Cursor getShoppingList(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {COL_GROCERY_ID, COL_ITEM_NAME, COL_DESCRIPTION, COL_PRICE, COL_TYPE};

        return db.query(SHOPPING_LIST_TABLE_NAME, projection, null, null, null, null, COL_ITEM_NAME + " ASC", null);
    }

    public ArrayList getShoppingListAsList(){
        Cursor cursor = getShoppingList();
        ArrayList<ShoppingListItem> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()) {
                list.add(new ShoppingListItem(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
                cursor.moveToNext();
            }
        }
        return list;
    }


}
