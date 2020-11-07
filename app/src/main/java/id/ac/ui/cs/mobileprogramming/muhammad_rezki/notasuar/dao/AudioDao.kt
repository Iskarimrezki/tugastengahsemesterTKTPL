package id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.AudioEntity

@Dao
interface AudioDao {
    @Query("SELECT * from audio")
    fun getAll(): LiveData<List<AudioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(audioEntity: AudioEntity)

    @Delete
    fun delete(audioEntity: AudioEntity)
}