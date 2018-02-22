package com.arifinfrds.papbl_sqlite.ui.barang

import android.content.Context
import android.text.TextUtils
import com.arifinfrds.papbl_sqlite.model.Barang
import com.arifinfrds.papbl_sqlite.model.database.DatabaseManager

/**
 * Created by arifinfrds on 2/22/18.
 */
class BarangInteractorImpl(private var context: Context) : BarangContract.Interactor {

    private var databaseManager: DatabaseManager? = null

    init {
        databaseManager = DatabaseManager(context)
    }

    override fun isInputEmpty(text: String): Boolean {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        return false;
    }

    override fun insert(barang: Barang, listener: BarangContract.Presenter.OnInsertFinishListener) {
        val success = databaseManager?.insert(barang)
        if (success!!) {
            listener.onInsertSuccess()
        } else {
            listener.onInsertFailure()
        }

    }

    override fun fetchAll(listener: BarangContract.Presenter.OnFetchAllFinishListener) {
    }

    override fun fetch(idBarang: Int, listener: BarangContract.Presenter.OnFetchFinishListener) {
    }

    override fun update(barang: Barang, listener: BarangContract.Presenter.OnUpdateFinishListener) {
    }

    override fun delete(barang: Barang, listener: BarangContract.Presenter.OnDeleteFinishListener) {
    }
}