package com.rai.irregularverbs.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "verbs")
data class IrregularVerbs (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "form1") val form1: String,
    @ColumnInfo(name = "form2") val form2: String,
    @ColumnInfo(name = "form3") val form3: String,
    @ColumnInfo(name = "ru") val ru: String,
    @ColumnInfo(name = "example1") val example1: String,
    @ColumnInfo(name = "example2") val example2: String,
    @ColumnInfo(name = "example3") val example3: String,
    @ColumnInfo(name = "part") val part: Int,
    @ColumnInfo(name = "numCorrectV2") val numCorrectV2: Int,
    @ColumnInfo(name = "numCorrectV3") val numCorrectV3: Int
)