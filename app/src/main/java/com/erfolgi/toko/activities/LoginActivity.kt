package com.erfolgi.toko.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import com.erfolgi.toko.R
import com.erfolgi.toko.activities.MainActivity.Companion.first
import com.erfolgi.toko.activities.MainActivity.Companion.last
import com.erfolgi.toko.activities.MainActivity.Companion.uname
import com.erfolgi.toko.db.UserModel
import com.erfolgi.toko.db.database
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bt_login.setOnClickListener {
            database.use {
                val result = select(UserModel.TABLE_USER).whereArgs("(USERNAME = {userName}) and (PASSWORD = {pass})",
                    "userName" to log_user.text.toString(),
                    "pass" to log_password.text.toString())
                val user = result.parseList(classParser<UserModel>())

                if (user.isNotEmpty()){
                    Log.e("quick fox", "loging in : $user")
                    val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    mainIntent.putExtra(first,user[0].firstName)
                    mainIntent.putExtra(last,user[0].lastName)
                    mainIntent.putExtra(uname,user[0].username)
                    startActivity(mainIntent)
                    finish()
                }else{
                    Toast.makeText(ctx, "Incorrect Username or Password!",Toast.LENGTH_SHORT).show()
                    val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibratorService.vibrate(100)
                }

            }

        }
        bt_register.setOnClickListener {
            val mainIntent = Intent(this@LoginActivity, FormActivity::class.java)
            startActivity(mainIntent)
        }
    }
}
