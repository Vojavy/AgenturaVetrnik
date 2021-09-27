package com.vojavy.sektaschool.database

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class DataBaseConnector {
    var storage = Firebase.storage
    var storageRef = storage.reference
    val gsReference = storage.getReferenceFromUrl("gs://agenturavetrnik-bs1337.appspot.com/articles")
    fun checkingArticleNum(){
    }
}