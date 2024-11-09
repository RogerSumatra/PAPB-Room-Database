package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/** Data Access Object (DAO) merupakan pola yang digunakan
 * menyembunyikan kerumitan yang terlibat dalam menjalankan
 * proses database seperti @Insert, @Update, @Delete, dan @Query.
 * Metode ini menghasilkan kode SQL yang dapat digunakan
 * oleh Room untuk berkomunikasi dengan SQLite. Ini memungkinkan
 * untuk mengubah data secara independen dari kode yang
 * menggunakan data tersebut.
 **/

@Dao
interface ItemDao {
    /** Anotasi @Insert digunakan untuk memasukkan data ke
     * dalam database. Pada paramater anotasi terdapat onConflict
     * untuk menghindari konflik**/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /** Anotasi @Update digunakan untuk mengubah data yang sudah
     * ada pada database dengan data yang baru. **/
    @Update
    suspend fun update(item: Item)

    /** Anotasi @Delete digunakan untuk menghapus data pada database **/
    @Delete
    suspend fun delete(item: Item)

    /** Anotasi @Query digunakan untuk membuat perintah SQL
     * yang kompleks atau kustom. **/

    /** Fungsi ini digunakan untuk mengambil data di database
     *  items berdasarkan ID instansi.**/
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /** Fungsi ini digunakan untuk mengambil semua data pada
     * database items dan diurutkan secara naik berdasarkan
     * nama instansi.**/
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

}