package com.example.careconnect.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.careconnect.models.AuthUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class StorageManager(private val context: Context) {
    companion object {
        val Context.userDataStore: DataStore<User> by dataStore(
            fileName = "cc_user.pb",
            serializer = AuthUserSerializer
        )
    }
    lateinit var storageData: Flow<User>
    init{
        storageData = context.userDataStore.data
    }

//    val storageData : Flow<User> = context.userDataStore.data

    suspend fun setData(authUser:AuthUser){
        try{
            context.userDataStore.updateData { user : User ->
                user.toBuilder()
                    .setAccessToken(authUser.access_token)
                    .setAddress(authUser.address)
                    .setAppointmentDate(authUser.appointment_date?.let{it }.toString())
                    .setDob(authUser.dob)
                    .setEmail(authUser.email)
                    .setGender(authUser.gender)
                    .setId(authUser.id)
                    .setImage(authUser.image)
                    .setIqumaNo(authUser.iquma_no)
                    .setMobileNo(authUser.mobile_no)
                    .setMrnNo(authUser.mrn_no)
                    .setName(authUser.name)
                    .setNameAr(authUser.name_ar)
                    .setOtp(authUser.otp)
                    .setPatientBloodGroup(authUser.patient_blood_group)
                    .setPatientId(authUser.patient_id)
                    .build()
            }
        }catch(e : Exception){
            throw e
        }
    }

    suspend fun clearPrefernces(){
        context.userDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}