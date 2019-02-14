package com.erfolgi.toko.activities

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.erfolgi.toko.R
import com.erfolgi.toko.db.UserModel
import com.erfolgi.toko.db.database
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.lang.Exception

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        bt_registnew.setOnClickListener {
            if (reg_username.text.toString()==""){
                reg_username.error="Must be filled!"
            }
            if (reg_first.text.toString()==""){
                reg_first.error="Must be filled!"
            }
            if (reg_last.text.toString()==""){
                reg_last.error="Must be filled!"
            }
            if (reg_pass.text.toString()==""){
                reg_pass.error="Must be filled!"
            }
            if (reg_copass.text.toString()==""){
                reg_copass.error="Must be filled!"
            }

            if (reg_username.text.toString()!=""&&
                reg_first.text.toString()!=""&&
                reg_last.text.toString()!=""&&
                reg_pass.text.toString()!=""&&
                reg_copass.text.toString()!=""
                    ) {


                database.use {
                    val result = select(UserModel.TABLE_USER).whereArgs(
                        "(USERNAME = {userName})",
                        "userName" to reg_username.text.toString()
                    )

                    val user = result.parseList(classParser<UserModel>())
                    Log.d("REGIST","ini : $user")
                    if (user.isNotEmpty()) {
                        reg_username.error = "Already used!"
                        Log.d("REGIST2","ini : $user")
                        return@use

                    } else {
                        if (reg_pass.text.toString() == reg_copass.text.toString()) {
                            try {
                                database.use {
                                    insert(
                                        UserModel.TABLE_USER,
                                        UserModel.USERNAME to reg_username.text.toString(),
                                        UserModel.FIRSTNAME to reg_first.text.toString(),
                                        UserModel.LASTNAME to reg_last.text.toString(),
                                        UserModel.PASSWORD to reg_pass.text.toString()
                                    )
                                }
                                Toast.makeText(this@FormActivity, "Account Registered", Toast.LENGTH_LONG).show()
                                val mainIntent = Intent(this@FormActivity, LoginActivity::class.java)
                                startActivity(mainIntent)
                                finish()

                            } catch (e: SQLiteConstraintException) {
                                Toast.makeText(this@FormActivity, "Error: $e", Toast.LENGTH_LONG).show()

                            }
                        } else {
                            reg_pass.error = "Password unmatched"
                            reg_copass.error = "Password unmatched"
                        }

                    }
                }
            }



        }
    }
}
