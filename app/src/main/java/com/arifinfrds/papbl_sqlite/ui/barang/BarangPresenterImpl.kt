package com.arifinfrds.papbl_sqlite.ui.barang

import android.content.Context
import com.arifinfrds.papbl_sqlite.model.Barang

/**
 * Created by arifinfrds on 2/22/18.
 */
class BarangPresenterImpl(
        private val view: BarangContract.View,
        private var context: Context
) : BarangContract.Presenter, BarangContract.Presenter.OnInsertFinishListener {

    // MARK: - Properties
    private var interactor: BarangInteractorImpl? = null

    // MARK: - Init
    init {
        interactor = BarangInteractorImpl()
    }

    // MARK: - Presenter
    override fun attemptShowToasMessage(message: String) {
        view.showToastMessage(message)
    }

    override fun attemptShowDialog(title: String, message: String) {
        view.showDialog(title, message)
    }

    override fun attemptInsert(barang: Barang) {
        if (interactor!!.isInputEmpty(barang.nama) || interactor!!.isInputEmpty(barang.brand)) {
            view.showToastMessage("Please check your input.")

            if (interactor?.isInputEmpty(barang.nama)!!) {
                view?.showNamaBarangError()
            }
            if (interactor?.isInputEmpty(barang.brand)!!) {
                view?.showBrandBarangError()
            }
        } else {
            interactor?.insert(barang, this)
        }
    }

    override fun attemptFetchAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attemptFetch(idBarang: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attemptUpdate(barang: Barang) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attemptDelete(idBarang: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    // MARK: - OnInsertFinishListener
    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}