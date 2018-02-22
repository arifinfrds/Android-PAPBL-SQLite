package com.arifinfrds.papbl_sqlite.ui.barang

import android.content.Context
import android.database.Cursor
import android.text.TextUtils
import com.arifinfrds.papbl_sqlite.model.Barang
import com.arifinfrds.papbl_sqlite.model.database.DatabaseHelper

/**
 * Created by arifinfrds on 2/22/18.
 */
class BarangInteractorImpl(private var context: Context) : BarangContract.Interactor {

    private var databaseHelper: DatabaseHelper? = null

    init {
        databaseHelper = DatabaseHelper(context)
    }

    override fun isInputEmpty(text: String): Boolean {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        return false;
    }

    override fun insert(barang: Barang, listener: BarangContract.Presenter.OnInsertFinishListener) {
        val success = databaseHelper?.insert(barang)
        if (success!!) {
            listener.onInsertSuccess()
        } else {
            listener.onInsertFailure()
        }

    }

    override fun fetchAll(listener: BarangContract.Presenter.OnFetchAllFinishListener) {
        val res = databaseHelper?.fetchAll()
        if (res?.getCount() == 0) {
            listener.onFetchAllFailure()
        }
        val buffer = StringBuffer()
        while (res!!.moveToNext()) {
            buffer.append("ID Barang   : " + res.getString(0) + "\n")
            buffer.append("Nama        : " + res.getString(1) + "\n")
            buffer.append("Brand       : " + res.getString(2) + "\n")
            buffer.append("\n")
        }
        databaseHelper?.close()
        listener.onFetchAllSuccess(buffer)
    }

    override fun fetch(idBarang: Int, listener: BarangContract.Presenter.OnFetchFinishListener) {
    }

    override fun update(barang: Barang, listener: BarangContract.Presenter.OnUpdateFinishListener) {
    }

    override fun delete(barang: Barang, listener: BarangContract.Presenter.OnDeleteFinishListener) {
    }
}