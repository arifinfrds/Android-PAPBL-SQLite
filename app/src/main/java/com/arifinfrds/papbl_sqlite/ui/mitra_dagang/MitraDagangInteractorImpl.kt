package com.arifinfrds.papbl_sqlite.ui.mitra_dagang

import android.content.Context
import android.text.TextUtils
import com.arifinfrds.papbl_sqlite.model.MitraDagang
import com.arifinfrds.papbl_sqlite.model.database.DatabaseHelper

/**
 * Created by arifinfrds on 2/23/18.
 */
class MitraDagangInteractorImpl(private var context: Context) : MitraDagangContract.Interactor {

    private var databaseHelper: DatabaseHelper? = null

    init {
        databaseHelper = DatabaseHelper(context)
    }

    override fun isInputEmpty(text: String): Boolean {
        if (TextUtils.isEmpty(text)) {
            return true
        }
        return false
    }

    override fun insert(mitraDagang: MitraDagang, listener: MitraDagangContract.Presenter.OnInsertFinishListener) {
        val success = databaseHelper?.insertMitraDagang(mitraDagang)
        if (success!!) {
            listener.onInsertSuccess()
        } else {
            listener.onInsertFailure()
        }
    }

    override fun fetchAll(listener: MitraDagangContract.Presenter.OnFetchAllFinishListener) {
        val res = databaseHelper?.fetchAllMitraDagang()
        if (res?.getCount() == 0) {
            listener.onFetchAllFailure()
        }
        val buffer = StringBuffer()
        while (res!!.moveToNext()) {
            buffer.append("ID Mitra Dagang          : " + res.getString(0) + "\n")
            buffer.append("Nama                     : " + res.getString(1) + "\n")
            buffer.append("Tahun Kerjasama          : " + res.getString(2) + "\n")
            buffer.append("\n")
        }
        databaseHelper?.close()
        listener.onFetchAllSuccess(buffer)
    }

    override fun fetch(idMitraDagang: Int, listener: MitraDagangContract.Presenter.OnFetchFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(mitraDagang: MitraDagang, listener: MitraDagangContract.Presenter.OnUpdateFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(idMitraDagang: Int, listener: MitraDagangContract.Presenter.OnDeleteFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}