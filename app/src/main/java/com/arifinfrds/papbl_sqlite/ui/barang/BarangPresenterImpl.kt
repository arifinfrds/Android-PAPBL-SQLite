package com.arifinfrds.papbl_sqlite.ui.barang

import android.content.Context
import com.arifinfrds.papbl_sqlite.model.Barang

/**
 * Created by arifinfrds on 2/22/18.
 */
class BarangPresenterImpl(
        private val view: BarangContract.View,
        private var context: Context
) : BarangContract.Presenter, BarangContract.Presenter.OnInsertFinishListener, BarangContract.Presenter.OnFetchAllFinishListener {

    // MARK: - Properties
    private var interactor: BarangInteractorImpl? = null

    // MARK: - Init
    init {
        interactor = BarangInteractorImpl(context)
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
        interactor!!.fetchAll(this)
    }

    // TODO : nanti
    override fun attemptFetch(idBarang: Int) {
    }

    override fun attemptUpdate(barang: Barang) {
    }

    override fun attemptDelete(idBarang: Int) {
    }

    // MARK: - OnInsertFinishListener
    override fun onInsertSuccess() {
        view.showToastMessage("Insert success.")
        view.emptyInput()
    }

    override fun onInsertFailure() {
        view.showToastMessage("Insert failed.")
    }

    override fun onFetchAllSuccess(stringBuffer: StringBuffer) {
        view.showDialog("Barang", stringBuffer.toString())
    }

    override fun onFetchAllFailure() {
        view.showDialog("Barang", "No Data.")
    }
}