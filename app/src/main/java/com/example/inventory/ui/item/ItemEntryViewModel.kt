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

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat

/**
 * File ViewModel ini digunakan untuk mevalidasi input
 * dan memasukkan instansi item ke database room.
 */
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Variabel itemUiState untuk menyimpan pilihan
     * data item sementara.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Fungsi ini digunakan  mengubah data pada
     * itemUiState berdasarkan nilai input yang diberi.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /** Fungsi ini digunakan memvalidasi data input
     * nama, price, dan quantitiy sebelum menambah atau
     * mengubah data di variabel itemUiState**/
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

    /**
     * Fungsi ini digunakan untuk
     * memasukkan Item ke dalam database Room
     */
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }
}

/**
 * Kelas ini merepresentasikan Ui state dari instansi Item
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

/**
 * Kelas ini merepresentasikan atribut item yang memiliki
 * atribut sama pada file Item di package data.
 */

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * Fungsi ini digunakan untuk mengubah data class ItemDetails
 * menjadi Item agar bisa menampilkan datanya ke tampilan UI.
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * Fungsi ini digunakan untuk mengubah tipe data Item
 * menjadi ItemUiState
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Fungsi ini digunakan untuk mengubah tipe data Item
 *  menjadi ItemDetails
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
