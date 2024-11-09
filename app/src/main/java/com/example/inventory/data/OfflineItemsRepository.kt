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

/** File OfflineRepository ini akan meng-overide semua fungsi
 * yang ada pada file Repository yang nanti akan disambungkan
 * dengan fungsi pada ItemDao agar bisa terhubung dengan database
 * dan menampilkan hasillnya pada tampilan aplikasi.**/

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    /** Fungsi ini meng-overide fungsi pada Repository untuk
     * mengambil semua instansi data melalui ItemDao pada
     * fungsi getAllItems() **/
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /** Fungsi ini meng-overide fungsi pada Repository untuk
     * mengambil instansi data tertentu dengan ID melalui
     * ItemDao pada fungsi getItem **/
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /** Fungsi ini meng-overide fungsi pada Repository untuk
     * memasukkan instansi baru melalui ItemDao pada
     * fungsi insert **/
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /** Fungsi ini meng-overide fungsi pada Repository untuk
     * menghapus instansi melalui ItemDao pada
     * fungsi delete **/
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /** Fungsi ini meng-overide fungsi pada Repository untuk
     * mengubah data baru melalui ItemDao pada
     * fungsi update **/
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}
