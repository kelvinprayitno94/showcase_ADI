package com.hino.hearts.network.response.opportunity

import com.hino.hearts.model.OpportunityModel

/**
 * Created by Dihardja Software on 2020-02-14.
 */
object ChangeStatusResponse {
    /*{
        "meta": {
        "message": "Opportunity Updated",
        "success": true,
        "errors": []
    },
        "data": {
        "id": 1,
        "createdBy": 1,
        "opportunityName": "Buy 11 truck",
        "stage": "Negotiation",
        "budget": 100,
        "accountId": 1,
        "deletedAt": null,
        "createdAt": "2020-02-20T05:20:56.521Z",
        "updatedAt": "2020-02-24T07:41:21.584Z"
    },
        "links": null,
        "include": null
    }*/

    data class Result(val meta: Meta, val data: OpportunityModel)
    data class Meta(val message: String, val success: Boolean)
}