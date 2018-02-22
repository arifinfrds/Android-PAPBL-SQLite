package com.arifinfrds.papbl_sqlite.ui.barang

import com.arifinfrds.papbl_sqlite.model.Barang
import java.util.*

/**
 * Created by arifinfrds on 2/22/18.
 */
interface BarangContract {

    interface View {

        fun showToastMessage(message: String)

        fun showDialog(title: String, message: String)

        fun showIDBarangError()

        fun showNamaBarangError()

        fun showBrandBarangError()

        fun emptyInput()

    }

    interface Presenter {

        fun attemptShowToasMessage(message: String)

        fun attemptShowDialog(title: String, message: String)

        fun attemptInsert(barang: Barang)

        fun attemptFetchAll()

        fun attemptFetch(idBarang: Int)

        fun attemptUpdate(barang: Barang)

        fun attemptDelete(idBarang: Int)


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

            fun onSuccess()

            fun onFailure()

        }

        interface OnDeleteFinishListener {

            fun onSuccess()

            fun onFailure()

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

        fun insert(barang: Barang, listener: Presenter.OnInsertFinishListener)

        fun fetchAll(listener: Presenter.OnFetchAllFinishListener)

        fun fetch(idBarang: Int, listener: Presenter.OnFetchFinishListener)

        fun update(barang: Barang, listener: Presenter.OnUpdateFinishListener)

        fun delete(barang: Barang, listener: Presenter.OnDeleteFinishListener)
    }


}