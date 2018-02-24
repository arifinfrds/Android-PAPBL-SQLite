package com.arifinfrds.papbl_sqlite.model.database

import android.database.Cursor
import com.arifinfrds.papbl_sqlite.model.Barang

/**
 * Created by arifinfrds on 2/22/18.
 */
interface BarangCRUDContract {

    fun insertBarang(barang: Barang): Boolean

    fun fetchAllBarang(): Cursor

    fun fetchBarang(namaBarang: String): Cursor

    fun updateBarang(barang: Barang): Boolean

    fun deleteBarang(idBarang: Int): Boolean

}