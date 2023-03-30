package com.example.privasee.database.viewmodel.repository

import com.example.privasee.database.model.Record
import com.example.privasee.database.viewmodel.repository.dao.RecordDao

class RecordRepository(private val recordDao: RecordDao) {

    suspend fun addRecord(record: Record) {
        recordDao.addRecord(record)
    }

}