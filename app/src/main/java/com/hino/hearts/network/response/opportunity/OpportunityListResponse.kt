package com.hino.hearts.network.response.opportunity

import com.hino.hearts.model.OpportunityModel

/**
 * Created by Dihardja Software on 2020-02-14.
 */
object OpportunityListResponse {
    /*
    "meta": {
      "message": "Opportunity Found",
      "success": true,
      "errors": []
    },
    "data": [{
      "id": 1,
      "createdBy": 1,
      "opportunityName": "Buy 11 truck",
      "stage": "Not Started",
      "budget": 100,
      "accountId": 1,
      "deletedAt": null,
      "createdAt": "2020-02-13T07:21:25.773Z",
      "updatedAt": "2020-02-13T09:59:57.793Z"
     },
     */

    data class Result(val meta: Meta, val data: List<OpportunityModel>)
    data class Meta(val message: String, val success: Boolean)
}