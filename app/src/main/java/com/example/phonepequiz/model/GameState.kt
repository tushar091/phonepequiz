package com.example.phonepequiz.model

import android.os.Parcel
import android.os.Parcelable

data class GameState(
    var isPaused: Boolean,
    var currentQuestionIndex: Int = 0,
    var isGameOver: Boolean,
    var skippedQuestionCount: Int,
    var correctAnswerCount: Int,
    var wrongAnswerCount: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isPaused) 1 else 0)
        parcel.writeInt(currentQuestionIndex)
        parcel.writeByte(if (isGameOver) 1 else 0)
        parcel.writeInt(skippedQuestionCount)
        parcel.writeInt(correctAnswerCount)
        parcel.writeInt(wrongAnswerCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameState> {
        override fun createFromParcel(parcel: Parcel): GameState {
            return GameState(parcel)
        }

        override fun newArray(size: Int): Array<GameState?> {
            return arrayOfNulls(size)
        }
    }
}