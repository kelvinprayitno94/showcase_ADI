package com.hino.hearts.network.response.visit

import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.model.OpportunityVisitModel
import com.hino.hearts.network.service.visit.VisitRequestBody

/**
 * Created by Dihardja Software on 2020-02-14.
 */

/*{
    "meta": {
    "message": "Visit created",
    "success": true,
    "errors": []
},
    "data": [
    {
        "userID": 1,
        "opportunity": 36,
        "account": 7,
        "visit": false,
        "description": "Call Log Donec scelerisque mi varius. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eget metus sit amet felis posuere finibus. Donec scelerisque mi varius.",
        "type": "CallLog",
        "createdAt": "2020-02-26T08:58:40.125Z"
    }
    ],
    "links": null,
    "include": null
}*/

object CreateVisitResponse {
    data class Result(val meta: Meta, val data: List<VisitRequestBody>)
    data class Meta(val message: String, val success: Boolean)
}