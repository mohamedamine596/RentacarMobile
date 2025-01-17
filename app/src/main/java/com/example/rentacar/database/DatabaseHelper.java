// DatabaseHelper.java
package
        com.example.rentacar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rentacar.database.models.Rental;
import com.example.rentacar.database.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "VehicleRental.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_VEHICLES = "vehicles";
    public static final String TABLE_RENTALS = "rentals";

    // Common column names
    public static final String COLUMN_ID = "id";

    // Users Table columns
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_IS_ADMIN = "is_admin";

    // Vehicles Table columns
    public static final String COLUMN_VEHICLE_NAME = "name";
    public static final String COLUMN_VEHICLE_TYPE = "type";
    public static final String COLUMN_DAILY_RATE = "daily_rate";
    public static final String COLUMN_IS_AVAILABLE = "is_available";

    // Rentals Table columns
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_VEHICLE_ID = "vehicle_id";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_TOTAL_COST = "total_cost";
    public static final String COLUMN_STATUS = "status";

    // Create table statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT UNIQUE NOT NULL,"
            + COLUMN_PASSWORD + " TEXT NOT NULL,"
            + COLUMN_IS_ADMIN + " INTEGER DEFAULT 0)";

    private static final String CREATE_TABLE_VEHICLES = "CREATE TABLE " + TABLE_VEHICLES + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_VEHICLE_NAME + " TEXT NOT NULL,"
            + COLUMN_VEHICLE_TYPE + " TEXT NOT NULL,"
            + COLUMN_DAILY_RATE + " REAL NOT NULL,"
            + COLUMN_IS_AVAILABLE + " INTEGER DEFAULT 1)";

    private static final String CREATE_TABLE_RENTALS = "CREATE TABLE " + TABLE_RENTALS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_VEHICLE_ID + " INTEGER,"
            + COLUMN_START_DATE + " TEXT NOT NULL,"
            + COLUMN_END_DATE + " TEXT NOT NULL,"
            + COLUMN_TOTAL_COST + " REAL NOT NULL,"
            + COLUMN_STATUS + " TEXT NOT NULL,"
            + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + "),"
            + "FOREIGN KEY(" + COLUMN_VEHICLE_ID + ") REFERENCES " + TABLE_VEHICLES + "(" + COLUMN_ID + "))";
    private String vehicleName ;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_VEHICLES);
        db.execSQL(CREATE_TABLE_RENTALS);

        // Insert default admin user
        ContentValues adminValues = new ContentValues();
        adminValues.put(COLUMN_USERNAME, "admin");
        adminValues.put(COLUMN_PASSWORD, "admin123");
        adminValues.put(COLUMN_IS_ADMIN, 1);
        db.insert(TABLE_USERS, null, adminValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENTALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // User authentication method
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // Check if user is admin
    public boolean isAdmin(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_IS_ADMIN};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        boolean isAdmin = false;
        if (cursor.moveToFirst()) {
            isAdmin = cursor.getInt(0) == 1;
        }
        cursor.close();
        return isAdmin;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VEHICLES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String vehicleModel = null;

                Vehicle vehicle = null;
                vehicle.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                vehicle.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_NAME)));
                vehicle.setType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_TYPE)));
                vehicle.setDailyRate(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_DAILY_RATE)));
                vehicle.setAvailable(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_AVAILABLE)) == 1);
                vehicleList.add(vehicle);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vehicleList;
    }

    public List<Rental> getAllRentals() {
        List<Rental> rentalList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RENTALS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Rental rental = new Rental();
                rental.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                rental.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)));
                rental.setVehicleId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_ID)));
                rental.setStartDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_START_DATE)));
                rental.setEndDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_END_DATE)));
                rental.setTotalCost(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_COST)));
                rental.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)));
                rentalList.add(rental);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rentalList;
    }
}