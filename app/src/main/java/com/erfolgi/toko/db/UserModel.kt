package com.erfolgi.toko.db

data class UserModel(val id: Long?,
                     val username: String?,
                     val firstName: String?,
                     val lastName: String?,
                     val password: String?
                     ) {

    companion object {
        const val TABLE_USER: String = "TABLE_USER"
        const val ID: String = "ID_"
        const val USERNAME: String = "USERNAME"
        const val FIRSTNAME: String = "FIRST_NAME"
        const val LASTNAME: String = "LAST_NAME"
        const val PASSWORD: String = "PASSWORD"
    }
}