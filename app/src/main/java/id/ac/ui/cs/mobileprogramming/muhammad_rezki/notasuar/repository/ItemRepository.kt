package com.example.notasuar.repository

import android.app.Application
import com.example.notasuar.database.AudioDatabase
import com.example.notasuar.database.TextDatabase
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.dao.AudioDao
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.dao.TextDao

import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.AudioEntity
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.ItemEntity
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.TextEntity


@Suppress("DEPRECATION")
class ItemRepository(application: Application) {
    private var textDao: TextDao = TextDatabase.getInstance(application)!!.textDao()
    private var audioDao: AudioDao = AudioDatabase.getInstance(application)!!.audioDao()

    fun getAllItem(): LiveData<List<ItemEntity>> {
        val textItems = textDao.getAll() as LiveData<List<ItemEntity>>
        val audioItems = audioDao.getAll() as LiveData<List<ItemEntity>>
        val itemLiveData = MediatorLiveData<List<ItemEntity>>()
        itemLiveData.addSource(textItems) {
            itemLiveData.value = combineLiveData(textItems, audioItems)
        }
        itemLiveData.addSource(audioItems) {
            itemLiveData.value = combineLiveData(textItems, audioItems)
        }
        return itemLiveData
    }

    fun combineLiveData(
        liveData1: LiveData<List<ItemEntity>>,
        liveData2: LiveData<List<ItemEntity>>
    ): List<ItemEntity> {
        val temp: ArrayList<ItemEntity> = arrayListOf()
        if (liveData1.value != null) {
            temp.addAll(liveData1.value!!)
        }
        if (liveData2.value != null) {
            temp.addAll(liveData2.value!!)
        }
        return temp.sortedWith(compareBy { it.getTimeStamp() }).reversed()
    }

    fun insertText(text: TextEntity) {
        insertTextAsyncTask(textDao).execute(text)
    }

    fun updateText(text: TextEntity) {
        updateTextAsyncTask(textDao).execute(text)
    }

    fun insertAudio(audio: AudioEntity) {
        insertAudioAsyncTask(audioDao).execute(audio)
    }

    private class updateTextAsyncTask internal constructor(private val mAsyncTaskDao: TextDao) :
        AsyncTask<TextEntity, Void, Void>() {

        override fun doInBackground(vararg params: TextEntity): Void? {
            mAsyncTaskDao.update(params[0].id, params[0].text, params[0].timestamp)
            return null
        }
    }

    private class insertTextAsyncTask internal constructor(private val mAsyncTaskDao: TextDao) :
        AsyncTask<TextEntity, Void, Void>() {

        override fun doInBackground(vararg params: TextEntity): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class insertAudioAsyncTask internal constructor(private val mAsyncTaskDao: AudioDao) :
        AsyncTask<AudioEntity, Void, Void>() {

        override fun doInBackground(vararg params: AudioEntity): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

}