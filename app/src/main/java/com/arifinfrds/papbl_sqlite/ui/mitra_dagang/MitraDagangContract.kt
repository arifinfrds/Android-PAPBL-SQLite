package com.arifinfrds.papbl_sqlite.ui.mitra_dagang

import com.arifinfrds.papbl_sqlite.model.Barang
import com.arifinfrds.papbl_sqlite.model.MitraDagang

/**
 * Created by arifinfrds on 2/23/18.
 */
interface MitraDagangContract {

    interface View {

        fun showToastMessage(message: String)

        fun showDialog(title: String, message: String)

        fun showIDBarangError()

        fun showNamaBarangError()

        fun showTahunKerjasamaBarangError()

        fun emptyInput()

    }

    interface Presenter {

        fun attemptShowToasMessage(message: String)

        fun attemptShowDialog(title: String, message: String)

        fun attemptInsert(mitraDagang: MitraDagang)

        fun attemptFetchAll()

        fun attemptFetch(idMitraDagang: Int)

        fun attemptUpdate(mitraDagang: MitraDagang)

        fun attemptDelete(idMitraDagang: Int)


        interface OnInsertFinishListener {

            fun onInsertSuccess()

            fun onInsertFailure()

        }

        interface OnFetchAllFinishListener {

            fun onFetchAllSuccess(stringBuffer: StringBuffer)

            fun onFetchAllFailure()

        }

        interface OnFetchFinishListener {

            fun onSuccess()

            fun onFailure()

        }

        interface OnUpdateFinishListener {

            fun onUpdateSuccess()

            fun onUpdateFailure()

        }

        interface OnDeleteFinishListener {

            fun onDeleteSuccess()

            fun onDeleteFailure()

        }


    }

    interface Interactor {

        /**
         * method is used for checking valid input empty or not.
         *
         * @param text
         * @return boolean true for valid false for invalid
         */

        fun isInputEmpty(text: String): Boolean

        fun insert(mitraDagang: MitraDagang, listener: Presenter.OnInsertFinishListener)

        fun fetchAll(listener: Presenter.OnFetchAllFinishListener)

        fun fetch(idMitraDagang: Int, listener: Presenter.OnFetchFinishListener)

        fun update(mitraDagang: MitraDagang, listener: Presenter.OnUpdateFinishListener)

        fun delete(idMitraDagang: Int, listener: Presenter.OnDeleteFinishListener)
    }
}