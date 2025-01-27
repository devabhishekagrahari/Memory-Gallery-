package dev.abhishekagrahari.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task_table")
data class todo(
    @PrimaryKey(autoGenerate = true)
    var id :Int =0,
    var taskName: String ="",
    var taskDescription: String ="" ,
    var image: ByteArray ,
)