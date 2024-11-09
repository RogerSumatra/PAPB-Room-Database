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

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity data class represents a single row in the database.
 */

/**
 * Kelas entity digunakan sebagai representasi item
 * yang akan disimpan di database. Setiap baris
 * merepresentasikan setiap instansi yang dibuat dan disimpan
 * di database. entity menyimpan informasi bergantung dari
 * data atribut yang didefinisikan di kelas.
 * Disini nama tabel dari entitas Item adalah "items"**/
@Entity(tableName = "items")
data class Item(

    /** Anotasi @PrimaryKey digunakan untuk menjadikan suatu data
     * kolom menjadi primary key (unique key). Disini fitur ID
     * dijadikan sebagai primary key dengan nilai awal 0.
     * Di anotasi terdapat"autoGenerate = true" agar primary
     * key dibuat secara otomatis
     * **/
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
