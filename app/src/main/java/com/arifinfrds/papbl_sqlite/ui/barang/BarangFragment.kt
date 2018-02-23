package com.arifinfrds.papbl_sqlite.ui.barang


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arifinfrds.papbl_sqlite.R
import com.arifinfrds.papbl_sqlite.extension.toast
import com.arifinfrds.papbl_sqlite.model.Barang
import com.arifinfrds.papbl_sqlite.model.database.DatabaseHelper
import kotlinx.android.synthetic.main.fragment_barang.*


class BarangFragment : Fragment(), BarangContract.View {

    private var presenter: BarangPresenterImpl? = null
    private var databaseHelper: DatabaseHelper? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_barang, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper(context)
        presenter = BarangPresenterImpl(this, context)

        insertButton.setOnClickListener {
            val barang = Barang(
                    id = -1,
                    nama = barangNameEditText.text.toString(),
                    brand = barangBrandEditText.text.toString()
            )
            presenter!!.attemptInsert(barang)
        }

        viewAllButton.setOnClickListener {
            presenter?.attemptFetchAll()
        }


        updateButton.setOnClickListener {
            val idString = barangIdEditText.text.toString()
            if (!idString.isEmpty()) {
                val id = Integer.parseInt(idString)
                val barang = Barang(
                        id = id,
                        nama = barangNameEditText.text.toString(),
                        brand = barangBrandEditText.text.toString()
                )
                presenter?.attemptUpdate(barang)
            } else {
                presenter?.attemptShowToasMessage("ID tidak boleh kosong.")
                showIDBarangError()
            }

        }

    }

    override fun showToastMessage(message: String) {
        context.toast(message)
    }

    override fun showDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }

    override fun showIDBarangError() {
        barangIdEditText.error = "Important"
    }

    override fun showNamaBarangError() {
        barangNameEditText.error = "Important"
    }

    override fun showBrandBarangError() {
        barangBrandEditText.error = "Important"
    }

    override fun emptyInput() {
        barangIdEditText.setText("")
        barangNameEditText.setText("")
        barangBrandEditText.setText("")
    }

}// Required empty public constructor
