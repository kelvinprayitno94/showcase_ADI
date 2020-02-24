package com.hino.hearts.network.service.opportunity

class StatusRequestBody(changedStatus: String) {
    val stage: String = changedStatus
}