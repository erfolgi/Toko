package com.erfolgi.toko.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "User.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(UserModel.TABLE_USER, true,
            UserModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            UserModel.USERNAME to TEXT + UNIQUE,
            UserModel.FIRSTNAME to TEXT,
            UserModel.LASTNAME to TEXT,
            UserModel.PASSWORD to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(UserModel.TABLE_USER, true)
    }
}
// Access property for Context
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)