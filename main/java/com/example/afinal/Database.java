package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.afinal.Model.Category;
import com.example.afinal.Model.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DBName = "myshop.db";
    private static final int DBVersion = 9;

    //category Table
    private static final String TABLE_CATEGORIES = "categories";
    private static final String COLUMN_CATEGORY_ID = "id";
    private static final String COLUMN_CATEGORY_NAME = "name";

    // Products table
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_CATEGORY_ID = "category_id";

    //User table
    private static String tblUsers = "tblUsers";
    private static  String UserID = "UserID";
    private static String UserFullName = "UserFullName";
    private static  String UserPassword = "UserPassword";
    private static  String UserEmail = "UserEmail";
    private  static  String userDate = "Date";



    //Order table
    private static String tblOrders = "Orders";
    private static String OrderID = "OrderID";
    private static String OrderName = "OrderName";
    private static String OrderPrice = "OrderPrice";
    private static String OrderQuantity = "OrderQuantity";

    private Context mContext; // Store the context here


    public Database(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
        mContext = context; // Store the context passed to the constructor
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create categories table
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "(" + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CATEGORY_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CATEGORIES_TABLE);
        //products table
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_DESCRIPTION + " TEXT," + COLUMN_PRODUCT_PRICE + " TEXT," + COLUMN_PRODUCT_CATEGORY_ID + " INTEGER," + "FOREIGN KEY(" + COLUMN_PRODUCT_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORIES + "(" + COLUMN_CATEGORY_ID + ")" + ")";
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);

        String Users = "CREATE TABLE " + tblUsers + "(" + UserID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UserFullName + " TEXT,  " + UserPassword + " TEXT, " + UserEmail + " TEXT, " + userDate + " TEXT)";
        sqLiteDatabase.execSQL(Users);


        String Orders = "CREATE TABLE " + tblOrders + "(" + OrderID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + OrderName + " TEXT, " + OrderPrice + " REAL, " + OrderQuantity + " INTEGER)";
        sqLiteDatabase.execSQL(Orders);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + tblUsers);
        db.execSQL("DROP TABLE IF EXISTS " + tblOrders);
             // Create new tables
        onCreate(db);
    }
//Add users
public void AddUser(String userFullName, String userPassword, String strEmail) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(UserFullName, userFullName);
    values.put(UserEmail, strEmail);
    values.put(userDate, getCurrentDate()); // Insert current date
    values.put(UserPassword, userPassword);

    db.insert(tblUsers, null, values);
    db.close();
}

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }





    //AddOrder
    public void AddOrder(String orderName, String orderPrice, int orderQuantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderName, orderName);
        values.put(OrderPrice, orderPrice);
        values.put(OrderQuantity, orderQuantity); // Insert OrderQuantity value

        db.insert(tblOrders, null, values);
        db.close();
    }
    //Admin category
    public void addCategory(String categoryName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, categoryName);
        db.insert(TABLE_CATEGORIES, null, values);
        db.close();
    }



    //checkUserCredentials
    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { UserFullName };
        String selection = UserFullName + " = ? AND " + UserPassword + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(tblUsers, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        if (count > 0) {
            // User credentials are correct, save login status in session
            SharedPreferences preferences = mContext.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("IS_USER_LOGGED_IN", true);
            editor.putString("LOGGED_IN_USER_NAME", username);
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COLUMN_CATEGORY_ID, COLUMN_CATEGORY_NAME };
        Cursor cursor = db.query(TABLE_CATEGORIES, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME));
            Category category = new Category(id, name);
            categories.add(category);
        }
        cursor.close();
        db.close();
        return categories;
    }

    public void deleteCategory(int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORIES, COLUMN_CATEGORY_ID + " = ?", new String[] { String.valueOf(categoryId)});
        db.close();
    }


    public void updateCategory(int categoryId, String categoryName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, categoryName);
        db.update(TABLE_CATEGORIES, values, COLUMN_CATEGORY_ID + " = ?",
                new String[]{String.valueOf(categoryId)});
        db.close();
    }



    public List<order> getAllOrders() {
        SQLiteDatabase db = getReadableDatabase();
        List<order> orders = new ArrayList<>();
        String[] columns = {OrderID, OrderName, OrderPrice, OrderQuantity}; // Add OrderQuantity column
        Cursor cursor = db.query(tblOrders, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int orderId = cursor.getInt(cursor.getColumnIndexOrThrow(OrderID));
                String orderName = cursor.getString(cursor.getColumnIndexOrThrow(OrderName));
                double orderPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(OrderPrice));
                int orderQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(OrderQuantity)); // Retrieve OrderQuantity value
                order order = new order(orderId, orderName, orderPrice, orderQuantity); // Pass orderQuantity to Order constructor
                orders.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orders;
    }

    public void deleteOrder(int orderId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tblOrders, "orderId=?", new String[]{String.valueOf(orderId)});
        db.close();
    }





}