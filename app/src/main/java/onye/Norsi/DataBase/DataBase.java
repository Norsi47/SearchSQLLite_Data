package onye.Norsi.DataBase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import onye.Norsi.Model.Friends;

public class DataBase extends SQLiteAssetHelper {

    //using the db name in assets folder
    public static final String DB_NAME = "friends.db";
    private static final int DB_VER = 1;

    //constructor to get the files saved and version num
    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    //get all functions
    //should return a list of strings and int in Friends class
    @SuppressLint("Range")//added this in to fix error in line 53-57
    public List<Friends> getFriends() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        //need to make sure these match column names in table (can find in Model class as well)
        //setting these names in a string array
        String[] sqlSelect = {
                "id", "name", "address", "email", "Phone"
        };
        //needs to match the table name
        //check db browser sql lite to confirm
        String tableName = "friends";

        //setting query builder to the table name
        sqLiteQueryBuilder.setTables(tableName);
        /*cursor to use the queryBuilder using the table name
         * in the body read what is in the database, then use the sql array string array list */
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, sqlSelect, null, null, null, null, null);

        //creating empty array list to show result
        List<Friends> friendsListResult = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                /*in this do while loop
                 * first create the Friends class to get its variable names
                 * then set the variable names t match what the cursor will do first
                 * then match it with the columns by their names in sqlSelect
                 * since cursor was set to that in line 48*/

                Friends friends = new Friends();
                friends.setId(cursor.getInt(cursor.getColumnIndex("id")));
                friends.setName(cursor.getString(cursor.getColumnIndex("name")));
                friends.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                friends.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                friends.setPhone(cursor.getString(cursor.getColumnIndex("Phone")));

                friendsListResult.add(friends);
            } while (cursor.moveToNext());
        }

        return friendsListResult;
    }

    //function to get all friends name
    @SuppressLint("Range") //again this removes error in using cursor.getColumn (line 182)
    public List<String> getNames() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        //need to make sure these match column names in table (can find in Model class as well)
        //setting these names in a string array
        String[] sqlSelect = {
                "name"
        };
        //needs to match the table name
        //check db browser sql lite to confirm
        String tableName = "friends";

        //setting query builder to the table name
        sqLiteQueryBuilder.setTables(tableName);
        /*cursor to use the queryBuilder using the table name
         * in the body read what is in the database, then use the sql array string array list */
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, sqlSelect, null, null, null, null, null);

        //creating empty array list to show result
        List<String> friendsListResult = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                friendsListResult.add(cursor.getString(cursor.getColumnIndex("Name")));
            } while (cursor.moveToNext());
        }

        return friendsListResult;
    }

    //get friend by name
    //String name in body because we are looking for the name
    @SuppressLint("Range")
    public List<Friends> getFriendByName(String name) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        //need to make sure these match column names in table (can find in Model class as well)
        //setting these names in a string array
        String[] sqlSelect = {
                "name"
        };
        //needs to match the table name
        //check db browser sql lite to confirm
        String tableName = "friends";

        //setting query builder to the table name
        sqLiteQueryBuilder.setTables(tableName);
        /*cursor to use the queryBuilder using the table name
         * in the body read what is in the database, then use the sql array string array list */
        //query to select(*) all from friends, wher Name LIKE %pattern%

      /*  //to get exact name use this way
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, sqlSelect, "Name = ?", new String[]{name},
                null, null, null);*/

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, sqlSelect, "Name LIKE ?", new String[]{"%" + name + "%"}, null, null, null);

        //creating empty array list to show result
        List<Friends> friendsListResult = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                /*in this do while loop
                 * first create the Friends class to get its variable names
                 * then set the variable names t match what the cursor will do first
                 * then match it with the columns by their names in sqlSelect
                 * since cursor was set to that in line 48*/

                Friends friends = new Friends();
                friends.setId(cursor.getInt(cursor.getColumnIndex("id")));
                friends.setName(cursor.getString(cursor.getColumnIndex("name")));
                friends.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                friends.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                friends.setPhone(cursor.getString(cursor.getColumnIndex("Phone")));

                friendsListResult.add(friends);
            }
            while (cursor.moveToNext());

        }
        return friendsListResult;


    }
}



