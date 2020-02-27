package com.hino.hearts.network.service.visit

class VisitRequestBody(paramUserId: Int, paramOpportunityId: Int, paramAccountId: Int, paramDescription: String, paramType: String) {
    val userId: Int = paramUserId
    val opportunityId: Int = paramOpportunityId
    val accountId: Int = paramAccountId
    val description: String = paramDescription
    val type: String = paramType
}