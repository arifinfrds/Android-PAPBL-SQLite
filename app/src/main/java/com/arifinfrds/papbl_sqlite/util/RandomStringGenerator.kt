package com.arifinfrds.papbl_sqlite.util

import java.util.*
import java.util.UUID.randomUUID
import java.util.Collections.replaceAll


/**
 * Created by arifinfrds on 2/25/18.
 */
class RandomStringGenerator {

    companion object {

        val DEFAULT_STRING_OF_LENGTH = 16

        fun randomStringOfLength(length: Int): String {
            val buffer = StringBuffer()
            while (buffer.length < length) {
                buffer.append(uuidString())
            }

            //this part controls the length of the returned string
            return buffer.substring(0, length)
        }


        private fun uuidString(): String {
            return UUID.randomUUID().toString().replace("-", "")
        }
    }


}