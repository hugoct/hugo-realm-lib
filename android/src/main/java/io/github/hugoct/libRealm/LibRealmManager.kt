package io.github.hugoct.libRealm

import android.util.Log
import android.widget.Toast
import com.facebook.react.bridge.*
import com.google.gson.Gson
import io.realm.Realm

import java.lang.Exception


class LibRealmManager(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    init {
        Realm.init(getReactApplicationContext())
    }
    override fun getName(): String {
        return "libRealm"
    }

    @ReactMethod
    fun select(responseCallback: Callback) {

        try {
            val realm = Realm.getDefaultInstance()
            val users = realm.where(UserSchema::class.java).findAll()

            responseCallback.invoke( users.asJSON(), null)
//            responseCallback.invoke( """[]""", null)
        } catch (e: Exception) {
            responseCallback.invoke("""{}""", e.message.toString())
        }
    }

    @ReactMethod
    fun insert(obj: String, responseCallback: Callback) {
        try {
            val gson = Gson()
            val user = gson.fromJson(obj, UserSchema::class.java)

            val realm = Realm.getDefaultInstance()
            realm?.beginTransaction()
            val currentIdNum: Number? = realm?.where(UserSchema::class.java)?.max("id")

            if (currentIdNum == null) user.id = 1
            else user.id = currentIdNum.toLong() + 1

            val realmUser = realm?.copyToRealm(user)
            realm?.commitTransaction()

            Log.i("Inserted", gson.toJson(user))


            responseCallback.invoke(gson.toJson(user), false)
        } catch (e: Exception) {
            responseCallback.invoke("""{}""", e.message.toString())
        }
    }

    @ReactMethod
    fun insertMany(objs: String, responseCallback: Callback) {
        try {
            val gson = Gson()
            val users = gson.fromJson(objs, Array<UserSchema>::class.java)

            val realm = Realm.getDefaultInstance()
            realm?.beginTransaction()
            val currentIdNum: Number? = realm?.where(UserSchema::class.java)?.max("id")
            var nextId: Long
            if (currentIdNum == null) {
                nextId = 1
            }
            else nextId = currentIdNum.toLong() + 1

            users.forEach {
                it.id = nextId
                nextId += 1
            }
            realm?.insert(users.asList())
            realm?.commitTransaction()

            responseCallback.invoke(gson.toJson(users), false)
        } catch (e: Exception) {
            responseCallback.invoke("""{}""", e.message.toString())
        }
    }

    @ReactMethod
    fun insertOrUpdateMany(objs: String, responseCallback: Callback) {

        try {
            val gson = Gson()
            val users = gson.fromJson(objs, Array<UserSchema>::class.java)

            if (users.isNotEmpty()) {
                val realm = Realm.getDefaultInstance()
                realm?.beginTransaction()
                val currentIdNum: Number? = realm?.where(UserSchema::class.java)?.max("id")
                var nextId: Long
                if (currentIdNum == null) {
                    nextId = 1
                }
                else nextId = currentIdNum.toLong() + 1


                users.forEach {
                    if (it.id.toInt() == 0) {
                        it.id = nextId
                        nextId += 1
                    }
                }

                realm?.insertOrUpdate(users.toList())
                realm?.commitTransaction()
            }

            responseCallback.invoke(gson.toJson(users), false)
        } catch (e: Exception) {
            responseCallback.invoke("""{}""", e.message.toString())
        }
    }

    @ReactMethod
    fun update(obj: String, responseCallback: Callback) {

        try {
            val gson = Gson()
            val user = gson.fromJson(obj, UserSchema::class.java)

            if (user.id > 0) {
                val realm = Realm.getDefaultInstance()
                realm?.beginTransaction()
                val userDB = realm.where(UserSchema::class.java).equalTo("id", user.id).findFirst()
                if (userDB != null) {
                    userDB.name = user.name
                    userDB.email = user.email
                    userDB.address = user.address
                }
                realm?.commitTransaction()
                responseCallback.invoke(gson.toJson(user), false)
            }
        } catch (e: Exception) {
                responseCallback.invoke(null, e.message.toString())
        }
    }

    @ReactMethod
    fun delete(id: Int, responseCallback: Callback) {
        try {
            if (id.toLong() > 0) {
                val realm = Realm.getDefaultInstance()
                realm?.beginTransaction()
                val result = realm.where(UserSchema::class.java).equalTo("id", id.toLong()).findAll()
                result.deleteAllFromRealm()
                realm?.commitTransaction()
            }
            responseCallback.invoke(true, false)
        } catch (e: Exception) {
            responseCallback.invoke(null, e.message.toString())
        }
    }

    @ReactMethod
    fun deleteMany(ids: String, responseCallback: Callback) {
        try {
            val gson = Gson()
            val idsArray = gson.fromJson(ids, Array<Int>::class.java)

            if (idsArray != null && idsArray.isNotEmpty()) {
                val realm = Realm.getDefaultInstance()
                realm?.beginTransaction()
                val results = realm?.where(UserSchema::class.java)?.`in`("id", idsArray)?.findAll()
                results?.deleteAllFromRealm()
                realm?.commitTransaction()
            }

            responseCallback.invoke(true, false)
        } catch (e: Exception) {
            responseCallback.invoke(null, e.message.toString())
        }
    }
}