package com.example.jet2feed.utils

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object TimeUtils {

    private const val serverDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTime(): String {
        val current = LocalDateTime.now(ZoneId.systemDefault())
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")//2020-04-17T12:13:44.575Z

        return current.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeDifference(dateCreatedAt: String): String {
        val finalDate = DateUtils.getRelativeTimeSpanString(
            getDateInMillis(dateCreatedAt),
            getDateInMillis(getCurrentTime()),
            DateUtils.MINUTE_IN_MILLIS
        )
        return finalDate.toString()
    }

    private fun getDateInMillis(srcDate: String): Long {
        val desiredFormat = SimpleDateFormat(
            serverDateFormat, Locale.getDefault()
        )
        val dateInMillis: Long
        try {
            val date: Date? = desiredFormat.parse(srcDate)
            dateInMillis = date!!.time
            return dateInMillis
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }
}