package com.arifinfrds.papbl_sqlite.ui.mitra_dagang

import android.content.Context
import com.arifinfrds.papbl_sqlite.model.MitraDagang


/**
 * Created by arifinfrds on 2/23/18.
 */
class MitraDagangPresenterImpl
(
        private val view: MitraDagangContract.View,
        private var context: Context
) :
        MitraDagangContract.Presenter,
        MitraDagangContract.Presenter.OnInsertFinishListener,
        MitraDagangContract.Presenter.OnFetchAllFinishListener,
        MitraDagangContract.Presenter.OnUpdateFinishListener,
        MitraDagangContract.Presenter.OnDeleteFinishListener {

    // MARK: - Properties
    private var interactor: MitraDagangInteractorImpl? = null

    // MARK: - Init
    init {
        interactor = MitraDagangInteractorImpl(context)
    }

    override fun attemptShowToasMessage(message: String) {
        view.showToastMessage(message)
    }

    override fun attemptShowDialog(title: String, message: String) {
        view.showDialog(title, message)
    }

    override fun attemptInsert(mitraDagang: MitraDagang) {
        if (interactor!!.isInputEmpty(mitraDagang.nama) || mitraDagang.tahunKerjasama == null) {
            view.showToastMessage("Please check your input.")

            if (interactor?.isInputEmpty(mitraDagang.nama)!!) {
                view?.showNamaBarangError()
            }
            if (mitraDagang.tahunKerjasama == null) {
                view?.showTahunKerjasamaBarangError()
            }
        } else {
            interactor?.insert(mitraDagang, this)
        }
    }

    override fun attemptFetchAll() {
        interactor!!.fetchAll(this)
    }

    override fun attemptFetch(idMitraDagang: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attemptUpdate(mitraDagang: MitraDagang) {
        if (mitraDagang.id == null) {
            view.showToastMessage("Please check your input.")

            if (interactor?.isInputEmpty(mitraDagang.nama)!!) {
                view?.showNamaBarangError()
            }
            if (mitraDagang.tahunKerjasama == null) {
                view?.showTahunKerjasamaBarangError()
            }
            if (mitraDagang.id == null) {
                view?.showIDBarangError()
            }
        } else {
            interactor?.update(mitraDagang, this)
        }
    }

    override fun attemptDelete(idMitraDagang: Int) {
        if (idMitraDagang == null) {
            view.showToastMessage("Please check your input.")
            view?.showIDBarangError()

        } else {
            interactor?.delete(idMitraDagang, this)
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
        view.showDialog("Mitra Dagang", stringBuffer.toString())
    }

    override fun onFetchAllFailure() {
        view.showDialog("Mitra Dagang", "No Data.")
    }

    // MARK : - OnUpdateFinishListener
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
}