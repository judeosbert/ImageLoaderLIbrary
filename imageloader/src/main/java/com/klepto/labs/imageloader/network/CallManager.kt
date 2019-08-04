package com.klepto.labs.imageloader.network

import io.reactivex.disposables.Disposable
import java.util.*

class CallManager {
    private val callsList = Hashtable<Int,Disposable>()

    fun addNewCall(call: Disposable):Int{
        val  callId = getNewCallId()
        callsList[callId] = call
        return callId
    }

    fun removeCall(callId:Int):Boolean{
        return if(callsList.containsKey(callId)){
            callsList.remove(callId)
            true
        }
        else{
            false
        }
    }


    private fun getNewCallId():Int{
        var uniqueRandomNumberGenerated = false
        var callId:Int = -1
        while(!uniqueRandomNumberGenerated){
            callId = (1..100000).shuffled().first()
            uniqueRandomNumberGenerated = callsList.containsKey(callId)
        }
        return callId
    }
}