package com.vplate.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmListenerService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage?) {
        val from = message!!.from
        val data = message.data
        val title = data["title"]
        val msg = data["message"]

        // 전달 받은 정보로 뭔가를 하면 된다.
    }
}