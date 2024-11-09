/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * File Repository ini digunakan untuk menerapkan proses-proses
 * yang ada di ItemDao pada database agar bisa ditampilkan ke
 * tampilan aplikasi.
 */
interface ItemsRepository {
    /**
     * Fungsi ini digunakan untuk mengambil semua instansi item di database
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Fungsi ini digunakan untuk mengambil instansi item di database
     * berdasarkan nomor ID instansi.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Fungsi ini digunakan untuk memasukkan instansi item ke database.
     */
    suspend fun insertItem(item: Item)

    /**
     * Fungsi ini digunakan untuk menghapus instansi item di database.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Fungsi ini digunakan untuk mengubah data di database.
     */
    suspend fun updateItem(item: Item)
}