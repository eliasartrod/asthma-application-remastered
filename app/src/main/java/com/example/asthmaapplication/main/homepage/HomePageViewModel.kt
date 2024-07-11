package com.example.asthmaapplication.main.homepage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.*
import com.example.asthmaapplication.main.common.AppPreferences
import com.example.asthmaapplication.main.common.Event
import com.example.asthmaapplication.main.common.SnackBarMessage
import com.example.asthmaapplication.main.database.UserAccountDataBaseHelper
import com.example.asthmaapplication.main.model.UserAccountModel
import com.example.inventory.R
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val _appPreferences: AppPreferences,
    private val _context: Context
) : ViewModel() {
    private val _loading = MutableLiveData<Event<Boolean>>()
    private val _snackBar = MutableLiveData<Event<SnackBarMessage>>()
    private val _registrationSuccess = MutableLiveData<Event<Boolean>>()
    private val _loginSuccess = MutableLiveData<Event<Boolean>>()
    private val _dbHelper = UserAccountDataBaseHelper(_context)
    private val _db: SQLiteDatabase = _dbHelper.writableDatabase

    val loading: LiveData<Event<Boolean>> = _loading
    val snackBar: LiveData<Event<SnackBarMessage>> = _snackBar
    val registrationSuccess: LiveData<Event<Boolean>> = _registrationSuccess
    val loginSuccess: LiveData<Event<Boolean>> = _loginSuccess

    fun login(emailAddress: String?, password: String?, name: String?, userType: String?) {
        _appPreferences.userType = userType
        // Check if the user exists in the database
        if (emailAddress?.let { ifUserExists(it) } == true) {
            // Retrieve the stored password for the given email
            val storedPassword = getUserPassword(emailAddress)

            // Check if the provided password matches the stored password
            if (password == storedPassword) {
                // Successful login
                _snackBar.value = Event(SnackBarMessage(R.string.successful_login))
                _loginSuccess.value = Event(true)
                _appPreferences.userName = name
            } else {
                // Incorrect password
                _snackBar.value = Event(SnackBarMessage(R.string.failed_login_wrong_password))
                _loginSuccess.value = Event(false)
            }
        } else {
            // User doesn't exist
            _snackBar.value = Event(SnackBarMessage(R.string.failed_login_user_non_existent))
            _loginSuccess.value = Event(false)
        }
    }


    fun register(emailAddress: String, password: String) {
        // Check if the user already exists in the database
        if (ifUserExists(emailAddress)) {
            _snackBar.value = Event(SnackBarMessage(R.string.duplicate_user))
        } else {
            // Insert the new user account into the database
            val newUserAccountModel = UserAccountModel(emailAddress, password)
            insertUser(newUserAccountModel)

            // Notify success
            _snackBar.value = Event(SnackBarMessage(R.string.registration_success))
            _registrationSuccess.value = Event(true)
        }
    }

    fun clearCurrentUser() {
        if (!getCurrentUser().isNullOrEmpty()) {
            _appPreferences.userName = null
            _appPreferences.userType = null
        }
    }

    fun getCurrentUser(): String? {
        return _appPreferences.userName
    }

    private fun getUserPassword(emailAddress: String): String {
        val columns = arrayOf("password")
        val selection = "email = ?"
        val selectionArgs = arrayOf(emailAddress)
        val cursor: Cursor = _db.query("user_accounts", columns, selection, selectionArgs, null, null, null)

        val passwordIndex = cursor.getColumnIndex("password")

        if (passwordIndex >= 0) {
            if (cursor.moveToFirst()) {
                val password = cursor.getString(passwordIndex)
                cursor.close()
                return password
            }
        }

        cursor.close()
        return ""
    }


    private fun ifUserExists(emailAddress: String): Boolean {
        val columns = arrayOf("email")
        val selection = "email = ?"
        val selectionArgs = arrayOf(emailAddress)
        val cursor: Cursor = _db.query("user_accounts", columns, selection, selectionArgs, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    private fun insertUser(userAccountModel: UserAccountModel) {
        val values = ContentValues()
        values.put("email", userAccountModel.emailAddress)
        values.put("password", userAccountModel.password)
        _db.insert("user_accounts", null, values)
    }

}