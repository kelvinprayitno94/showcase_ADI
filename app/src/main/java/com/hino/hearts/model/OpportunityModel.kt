package com.hino.hearts.model

object OpportunityModel {
    data class Result(val meta: Meta, val data: Data)
    data class Meta(val message: String, val success: Boolean)
    data class Data(val userData: OpportunityData, val token: String)
    data class OpportunityData(val id: Int, val title: String, val accountName: String, val opportunityValue: Long)
}