package com.scheddy.domain.model

data class Task(
    val id: Int,
    val category: Category,
    val startTime: Long,
    val endTime: Long?
)