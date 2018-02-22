package com.arifinfrds.papbl_sqlite.model.database

import android.database.Cursor
import com.arifinfrds.papbl_sqlite.model.Barang

/**
 * Created by arifinfrds on 2/22/18.
 */
interface BarangCRUDContract {

    fun insert(barang: Barang): Boolean

    fun fetchAll(): Cursor

    fun fetch(idBarang: Int) // belum

    fun update(barang: Barang): Boolean

    fun delete(idBarang: Int): Boolean

}