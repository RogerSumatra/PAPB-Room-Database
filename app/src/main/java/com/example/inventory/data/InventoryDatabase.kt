package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** Anotasi @Database digunakan untuk membuat abstract class
 * database yang akan digunakan untuk membuat database, atau
 * menggunakan database apabila sudah ada. File ini dibuat
 * untuk menyediakan aplikasi dengan objek DAO yang telah dibuat
 * agar aplikasi mampu menggunakan DAO untuk melakukan CRUD (Create,
 * Read, Update, Delete). Paramater dari @Database terdiri dari
 * entities sebagai tabel database, version sebagai versi database,
 * exportSchema untuk menentukan apakan room database mengekspor
 * skema database ke dalam file JSON.**/
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase(){
    /** metode yang tertulis di ItemDao akan digunakan **/
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            /** Apabila Instance (Database) tidak null, akan me-return
             * database. Apabila null, akan membuat database dengan
             * nama database = "item_database".**/
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}