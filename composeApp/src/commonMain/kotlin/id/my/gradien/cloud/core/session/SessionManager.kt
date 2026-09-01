package id.my.gradien.cloud.core.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SessionManager {
    val name: Flow<String?>
    val email: Flow<String?>
    val password: Flow<String?>
    val nodeIds: Flow<List<String>>
    val clusterIds: Flow<List<String>>
    suspend fun saveSession(
        name: String,
        email: String,
        password: String,
        nodeIds: List<String>,
        clusterIds: List<String>
    )
    suspend fun clearSession()
}

class SessionManagerImpl(
    private val dataStore: DataStore<Preferences>
) : SessionManager {

    override val name: Flow<String?> = dataStore.data.map { it[NAME_KEY] }
    override val email: Flow<String?> = dataStore.data.map { it[EMAIL_KEY] }
    override val password: Flow<String?> = dataStore.data.map { it[PASSWORD_KEY] }
    override val nodeIds: Flow<List<String>> = dataStore.data.map { preferences ->
        val nodesString = preferences[NODE_IDS_KEY]
        if (nodesString.isNullOrEmpty()) emptyList()
        else nodesString.split(",")
    }
    override val clusterIds: Flow<List<String>> = dataStore.data.map { preferences ->
        val clustersString = preferences[CLUSTER_IDS_KEY]
        if (clustersString.isNullOrEmpty()) emptyList()
        else clustersString.split(",")
    }

    override suspend fun saveSession(
        name: String,
        email: String,
        password: String,
        nodeIds: List<String>,
        clusterIds: List<String>
    ) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
            preferences[NODE_IDS_KEY] = nodeIds.joinToString(",")
            preferences[CLUSTER_IDS_KEY] = clusterIds.joinToString(",")
        }
    }

    override suspend fun clearSession() {
        dataStore.edit { it.clear() }
    }

    companion object {
        private val NAME_KEY = stringPreferencesKey("user_name")
        private val EMAIL_KEY = stringPreferencesKey("user_email")
        private val PASSWORD_KEY = stringPreferencesKey("user_password")
        private val NODE_IDS_KEY = stringPreferencesKey("user_nodes")
        private val CLUSTER_IDS_KEY = stringPreferencesKey("user_clusters")
    }
}
