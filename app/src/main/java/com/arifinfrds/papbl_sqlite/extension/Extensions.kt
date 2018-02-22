package com.arifinfrds.papbl_sqlite.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by arifinfrds on 2/22/18.
 */

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

