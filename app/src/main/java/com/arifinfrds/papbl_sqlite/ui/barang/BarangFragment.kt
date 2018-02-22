package com.arifinfrds.papbl_sqlite.ui.barang


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arifinfrds.papbl_sqlite.R


class BarangFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_barang, container, false)
    }


}// Required empty public constructor
