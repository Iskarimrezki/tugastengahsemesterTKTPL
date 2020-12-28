package id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.TextEntity
import java.util.*

@Dao
interface TextDao {
    @Query("SELECT * from texts")
    fun getAll(): LiveData<List<TextEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(textEntity: TextEntity)

    @Delete
    fun delete(textEntity: TextEntity)

    @Query("UPDATE texts SET text =:text, timestamp =:timestamp WHERE id =:id")
    fun update(id: Long, text: String, timestamp: Date)

}