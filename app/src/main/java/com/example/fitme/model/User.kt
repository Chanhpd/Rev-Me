package com.example.fitme.model

import com.google.gson.annotations.SerializedName

class User(
    var id: Int,
    var username: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var email: String,
    var is_active: Boolean,
    var is_staff: Boolean,
    var is_superuser: Boolean,
    var date_joined: String,
    var last_login: String,
    var avatar: String,

    var phone: String,
    var groups: List<String>,
    var user_permissions: List<String>

) {
    constructor() :
            this(0, "", "", "", "", false, false, false, "", "", "", "", listOf(), listOf())

    constructor(username: String, password: String) :
            this(0, username, "", "", "", false, false, false, "", "", "", "", listOf(), listOf())

    constructor(id: Int, last_login: String, username: String, first_name: String, last_name: String, email: String, is_active: Boolean,phone: String, is_staff: Boolean, is_superuser: Boolean, date_joined: String, avatar: String, groups: List<String>, user_permissions: List<String>) :
            this(id, username, first_name, last_name, email, is_active, is_staff, is_superuser, date_joined, last_login, avatar, phone, groups, user_permissions)



    override fun toString(): String {
        return "User(id=$id, username='$username', firstName='$firstName', lastName='$lastName', email='$email', is_active=$is_active, is_staff=$is_staff, is_superuser=$is_superuser, date_joined='$date_joined', last_login='$last_login', avatar='$avatar')"
    }


}

