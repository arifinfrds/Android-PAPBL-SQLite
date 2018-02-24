package com.arifinfrds.papbl_sqlite.ui.barang

import android.content.Context
import com.arifinfrds.papbl_sqlite.model.Barang

/**
 * Created by arifinfrds on 2/22/18.
 */
class BarangPresenterImpl(
        private val view: BarangContract.View,
        private var context: Context
) :
        BarangContract.Presenter,
        BarangContract.Presenter.OnInsertFinishListener,
        BarangContract.Presenter.OnFetchAllFinishListener,
        BarangContract.Presenter.OnUpdateFinishListener,
        BarangContract.Presenter.OnDeleteFinishListener,
        BarangContract.Presenter.OnFetchFinishListener {

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
    override fun attemptFetch(namaBarang: String) {
        if (interactor!!.isInputEmpty(namaBarang)) {
            view.showToastMessage("Search cannot be empty.")
        } else {
            interactor?.fetch(namaBarang, this)
        }
    }

    override fun attemptUpdate(barang: Barang) {
        if (barang.id == null) {
            view.showToastMessage("Please check your input.")

            if (interactor?.isInputEmpty(barang.nama)!!) {
                view?.showNamaBarangError()
            }
            if (interactor?.isInputEmpty(barang.brand)!!) {
                view?.showBrandBarangError()
            }
            if (barang.id == null) {
                view?.showIDBarangError()
            }
        } else {
            interactor?.update(barang, this)
        }
    }

    override fun attemptDelete(idBarang: Int) {
        if (idBarang == null) {
            view.showToastMessage("Please check your input.")
            view?.showIDBarangError()

        } else {
            interactor?.delete(idBarang, this)
        }
    }

    // MARK: - OnInsertFinishListener
    override fun onInsertSuccess() {
        view.showToastMessage("Insert success.")
        view.emptyInput()
    }

    override fun onInsertFailure() {
        view.showToastMessage("Insert failed.")
    }

    // MARK: - OnFetchAllFinishListener
    override fun onFetchAllSuccess(stringBuffer: StringBuffer) {
        view.showDialog("Barang", stringBuffer.toString())
    }

    override fun onFetchAllFailure() {
        view.showDialog("Barang", "No Data.")
    }

    // MARK: - OnUpdateFinishListener
    override fun onUpdateSuccess() {
        view.showToastMessage("Update success.")
        view.emptyInput()
    }

    override fun onUpdateFailure() {
        view.showToastMessage("Update failed.")
    }

    // MARK: - OnDeleteFinishListener
    override fun onDeleteSuccess() {
        view.showToastMessage("Delete success.")
        view.emptyInput()
    }

    override fun onDeleteFailure() {
        view.showToastMessage("Delete failed.")
    }

    // MARK: - OnFetchFinishListener
    override fun onFetchFinishSuccess(stringBuffer: StringBuffer) {
        view.showDialog("Barang", stringBuffer.toString())
    }

    override fun onFetchFinishFailure() {
        view.showDialog("Barang", "No Data.")
    }
}