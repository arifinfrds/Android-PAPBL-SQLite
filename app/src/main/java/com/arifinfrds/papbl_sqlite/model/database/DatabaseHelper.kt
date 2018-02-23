package com.arifinfrds.papbl_sqlite.model.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.arifinfrds.papbl_sqlite.model.Barang

/**
 * Created by arifinfrds on 2/22/18.
 */
class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), BarangCRUDContract {

    // MARK: - Static
    companion object {
        val DATABASE_NAME = "PAPBL-SQLite.db"
        val DATABASE_VERSION = 1
    }

    // MARK: - Properties

    // Table
    private val TABLE_BARANG = "TABLE_BARANG"
    private val COLUMN_1_ID = "ID"
    private val COLUMN_2_NAMA = "NAMA"
    private val COLUMN_3_BRAND = "BRAND"

    private val TABLE_MITRA_DAGANG = "MITRA_DAGANG"
    private val COLUMN_1_ID_MITRA_DAGANG = "ID"
    private val COLUMN_2_NAMA_MITRA_DAGANG = "NAMA"
    private val COLUMN_3_TAHUN_KERJASAMA_MITRA_DAGANG = "TAHUN_KERJASAMA"


    // MARK: - SQLiteOpenHelper
    override fun onCreate(p0: SQLiteDatabase?) {

        val queryTableBarang = "CREATE TABLE " + TABLE_BARANG + "(" +
                COLUMN_1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_2_NAMA + " TEXT, " +
                COLUMN_3_BRAND + " TEXT " +
                ");"
        p0?.execSQL(queryTableBarang)

        val queryTableMitraDagang = "CREATE TABLE " + TABLE_MITRA_DAGANG + "(" +
                COLUMN_1_ID_MITRA_DAGANG + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_2_NAMA_MITRA_DAGANG + " TEXT, " +
                COLUMN_3_TAHUN_KERJASAMA_MITRA_DAGANG + " TEXT " +
                ");"
        p0?.execSQL(queryTableMitraDagang)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS" + TABLE_BARANG)
        p0?.execSQL("DROP TABLE IF EXISTS" + TABLE_MITRA_DAGANG)
        onCreate(p0)
    }


    // MARK: - BarangCRUDContract
    override fun insert(barang: Barang): Boolean {
        val contentValues = ContentValues()
        val db = this.writableDatabase

        contentValues.put(COLUMN_2_NAMA, barang.nama)
        contentValues.put(COLUMN_3_BRAND, barang.brand)

        val result = db.insert(TABLE_BARANG, null, contentValues)

        db.close()

        return result != -1L
    }

    override fun fetchAll(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_BARANG, null)
    }

    override fun fetch(idBarang: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(barang: Barang): Boolean {
        val contentValues = ContentValues()
        val db = this.writableDatabase

        contentValues.put(COLUMN_2_NAMA, barang.nama)
        contentValues.put(COLUMN_3_BRAND, barang.brand)

        val result = db.update(TABLE_BARANG, contentValues, "ID = " + barang.id, null)

        db.close()

        return result != -1
    }

    override fun delete(idBarang: Int): Boolean {
        val db = this.writableDatabase
        return db.delete(TABLE_BARANG, COLUMN_1_ID + "=" + idBarang, null) > 0
    }
}