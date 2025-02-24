package com.aio.kotlin.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aio.kotlin.data.datastore.UserPreferences
import com.aio.kotlin.data.datastore.UserPreferencesSerializer
import com.aio.kotlin.studylist.jetpack.datastore.ProtoDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Preferences DataStore : SharedPreferences와 마찬가지로 스키마를 먼저 정의하지 않은 상태에서 키를 기반으로 데이터에 액세스합니다.
 * Proto DataStore :
 * 프로토콜 버퍼를 사용하여 스키마를 정의합니다. Protobuf를 사용하기 때문에 강타입(strongly typed) 데이터를 유지할 수 있습니다.
 * 이러한 데이터는 XML 등 다른 유사한 데이터 형식보다 빠르고 작고 간결하며 덜 모호합니다.
 * Proto Datastore를 사용하려면 새로운 직렬화 메커니즘을 배워야 하지만 Proto Datastore의 강타입 이점이 그만한 가치가 있습니다.
 */

// Preferences DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

private const val USER_PREFERENCES_NAME = "user_preferences"
private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
private const val SORT_ORDER_KEY = "sort_order"

// Proto DataStore
private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserPreferencesSerializer
)

class DataStoreUtil(private val context: Context) {

    private val stringKey = stringPreferencesKey("key_name") // string

    val testString: Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[stringKey] ?: ""
        }


    // Preference에 Setting 하는 방법
    suspend fun setText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[stringKey] = text
        }
    }

    val show_complete = booleanPreferencesKey("show_completed")

    val userPreferencesFlow: Flow<ProtoDataStore> = context.userPreferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            Log.d("HereHere", "Excpetion : $exception")
            if (exception is IOException) {
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            // Get our show completed value, defaulting to false if not set:
            val showCompleted = preferences.showCompleted
            val name = preferences.username
            ProtoDataStore(showCompleted, name)
        }

    suspend fun updateShowCompleted(completed: Boolean, data: String) {
        Log.d("HereHere", "3. saveProtoDataStore data : $data")
        context.userPreferencesStore.updateData { preferences ->
            Log.d("HereHere", "3-1. saveProtoDataStore data : $data")
            preferences
                .toBuilder()
                .setShowCompleted(completed)
                .setUsername(data)
                .build()
        }
    }


    private object PreferencesKeys {
        val NAME = stringPreferencesKey("name")
        val AGE = stringPreferencesKey("age")
        val SALARY = stringPreferencesKey("salary")
        val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
    }


//    val sampleData: Flow<SampleData> = context.dataStore.data.map { preferences ->
//        SampleData(
//            name = preferences[PreferencesKeys.NAME]?: "",
//            age = preferences[PreferencesKeys.AGE]?: "",
//            salary = preferences[PreferencesKeys.SALARY]?: ""
//        )
//    }
//
//    suspend fun setEmployee(sampleData: SampleData) {
//        context.dataStore.edit { settings ->
//            settings[PreferencesKeys.NAME] = sampleData.name
//            settings[PreferencesKeys.AGE] = sampleData.age
//            settings[PreferencesKeys.SALARY] = sampleData.salary
//        }
//    }
}