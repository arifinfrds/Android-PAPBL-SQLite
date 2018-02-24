package com.arifinfrds.papbl_sqlite.model.database

import android.database.Cursor
import com.arifinfrds.papbl_sqlite.model.Barang
import com.arifinfrds.papbl_sqlite.model.MitraDagang

/**
 * Created by arifinfrds on 2/23/18.
 */
interface MitraDagangCRUDContract {

    fun insertMitraDagang(mitraDagang: MitraDagang): Boolean

    fun fetchAllMitraDagang(): Cursor

    fun fetchMitraDagang(nama: String): Cursor // belum

    fun updateMitraDagang(mitraDagang: MitraDagang): Boolean

    fun deleteMitraDagang(idMitraDagang: Int): Boolean

}