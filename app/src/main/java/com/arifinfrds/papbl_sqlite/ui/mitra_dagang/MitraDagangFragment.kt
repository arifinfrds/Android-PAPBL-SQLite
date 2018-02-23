package com.arifinfrds.papbl_sqlite.ui.mitra_dagang


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arifinfrds.papbl_sqlite.R
import com.arifinfrds.papbl_sqlite.extension.toast
import com.arifinfrds.papbl_sqlite.model.MitraDagang
import com.arifinfrds.papbl_sqlite.model.database.DatabaseHelper
import kotlinx.android.synthetic.main.fragment_barang.*
import kotlinx.android.synthetic.main.fragment_mitra_dagang.*


class MitraDagangFragment : Fragment(), MitraDagangContract.View {

    private var presenter: MitraDagangPresenterImpl? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_mitra_dagang, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper(context)
        presenter = MitraDagangPresenterImpl(this, context)


        insertMitraDagangButton.setOnClickListener {
            val tahunKerjasamaString = mitraDagangTahunKerjasamaTextView.text.toString()
            if (!tahunKerjasamaString.isEmpty()) {
                val tahunKerjasamaInt = Integer.parseInt(tahunKerjasamaString)
                val mitraDagang = MitraDagang(
                        id = -1,
                        nama = mitraDagangNameTextView.text.toString(),
                        tahunKerjasama = tahunKerjasamaInt
                )
                presenter?.attemptInsert(mitraDagang)
            }
        }

        viewAllMitraDagangButton.setOnClickListener {
            presenter?.attemptFetchAll()
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

    override fun showTahunKerjasamaBarangError() {
        barangBrandEditText.error = "Important"
    }

    override fun emptyInput() {
        mitraDagangIdEditText.setText("")
        mitraDagangNameTextView.setText("")
        mitraDagangTahunKerjasamaTextView.setText("")
    }
}// Required empty public constructor
