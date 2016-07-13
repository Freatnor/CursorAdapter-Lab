package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private GroceryListSQLiteHelper mGroceryListSQLiteHelper;

    //For recycler view...
    private RecyclerView mRecyclerView;
    private GroceryRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        mGroceryListSQLiteHelper = GroceryListSQLiteHelper.getInstance(MainActivity.this);

        //mListView = (ListView) findViewById(R.id.shopping_list_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Cursor cursor = mGroceryListSQLiteHelper.getShoppingList();

        CursorAdapter adapter = new CursorAdapter(MainActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.shopping_inner_layout, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView nameText = (TextView) view.findViewById(R.id.name_text);
                TextView descText = (TextView) view.findViewById(R.id.description_text);
                TextView priceText = (TextView) view.findViewById(R.id.price_text);
                TextView typeText = (TextView) view.findViewById(R.id.type_text);

                nameText.setText(cursor.getString(cursor.getColumnIndex(GroceryListSQLiteHelper.COL_ITEM_NAME)));
                descText.setText(cursor.getString(cursor.getColumnIndex(GroceryListSQLiteHelper.COL_DESCRIPTION)));
                priceText.setText(cursor.getString(cursor.getColumnIndex(GroceryListSQLiteHelper.COL_PRICE)));
                typeText.setText(cursor.getString(cursor.getColumnIndex(GroceryListSQLiteHelper.COL_TYPE)));
            }
        };

        mListView.setAdapter(adapter);
        /*mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GroceryRecyclerViewAdapter(MainActivity.this, mGroceryListSQLiteHelper.getShoppingListAsList());
        mRecyclerView.setAdapter(mAdapter);*/

    }
}
