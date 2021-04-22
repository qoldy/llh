package com.example.lifeline.utils

import com.example.lifeline.data.Pulse
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class ChartHelper<T> {
    fun preparePulseData(dataList:ArrayList<Pulse>){
        var zdt:ZonedDateTime
        val entries=ArrayList<Entry>()
        for(data in dataList){
            zdt=data.dateTime.atZone(ZoneId.of("UTC"))
            entries.add(Entry(zdt.toInstant().toEpochMilli().toFloat(), data.value.toFloat()))
        }
        val vl = LineDataSet(entries, "Пульс")
    }
}