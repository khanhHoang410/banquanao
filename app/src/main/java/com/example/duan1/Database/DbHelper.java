package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "FaciwayDB";
    private static final int DB_VERSION= 1;

    // Bảng danh mục
    static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE Categories (\n" +
            "    categoryId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    categoryName TEXT NOT NULL\n" +
            ");";

    // Bảng sản phẩm
    static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE Products (\n" +
            "    productId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    productName TEXT NOT NULL,\n" +
            "    price INTEGER NOT NULL,\n" +
            "    description TEXT,\n" +
            "    categoryId INTEGER,\n" +
            "    FOREIGN KEY (categoryId) REFERENCES Categories(categoryId)\n" +
            ");";

    // Bảng giỏ hàng
    static final String CREATE_TABLE_CART = "CREATE TABLE Cart (\n" +
            "    cartId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    userId INTEGER NOT NULL,\n" +
            "    productId INTEGER NOT NULL,\n" +
            "    quantity INTEGER NOT NULL,\n" +
            "    FOREIGN KEY (userId) REFERENCES Users(userId),\n" +
            "    FOREIGN KEY (productId) REFERENCES Products(productId)\n" +
            ");";

    // Bảng đơn hàng
    static final String CREATE_TABLE_ORDERS = "CREATE TABLE Orders (\n" +
            "    orderId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    userId INTEGER NOT NULL,\n" +
            "    orderDate DATE NOT NULL,\n" +
            "    totalAmount INTEGER NOT NULL,\n" +
            "    status TEXT NOT NULL,\n" +
            "    FOREIGN KEY (userId) REFERENCES Users(userId)\n" +
            ");";

    // Bảng chi tiết đơn hàng
    static final String CREATE_TABLE_ORDER_ITEMS = "CREATE TABLE OrderItems (\n" +
            "    orderItemId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    orderId INTEGER NOT NULL,\n" +
            "    productId INTEGER NOT NULL,\n" +
            "    quantity INTEGER NOT NULL,\n" +
            "    price INTEGER NOT NULL,\n" +
            "    FOREIGN KEY (orderId) REFERENCES Orders(orderId),\n" +
            "    FOREIGN KEY (productId) REFERENCES Products(productId)\n" +
            ");";

    public DbHelper(@Nullable Context context){
        super(context, DB_Name,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_ORDER_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Categories");
        db.execSQL("DROP TABLE IF EXISTS Products");
        db.execSQL("DROP TABLE IF EXISTS Cart");
        db.execSQL("DROP TABLE IF EXISTS Orders");
        db.execSQL("DROP TABLE IF EXISTS OrderItems");
        onCreate(db);
    }
}
