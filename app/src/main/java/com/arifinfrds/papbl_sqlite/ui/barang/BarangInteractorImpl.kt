package com.arifinfrds.papbl_sqlite.ui.barang

import android.text.TextUtils
import com.arifinfrds.papbl_sqlite.model.Barang

/**
 * Created by arifinfrds on 2/22/18.
 */
class BarangInteractorImpl : BarangContract.Interactor {

    override fun isInputEmpty(text: String): Boolean {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        return false;
    }

    override fun insert(barang: Barang, listener: BarangContract.Presenter.OnInsertFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAll(listener: BarangContract.Presenter.OnFetchAllFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetch(idBarang: Int, listener: BarangContract.Presenter.OnFetchFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(barang: Barang, listener: BarangContract.Presenter.OnUpdateFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(barang: Barang, listener: BarangContract.Presenter.OnDeleteFinishListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}