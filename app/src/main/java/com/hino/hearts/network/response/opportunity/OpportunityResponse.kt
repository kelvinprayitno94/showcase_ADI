package com.hino.hearts.network.response.opportunity

import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.model.OpportunityVisitModel

/**
 * Created by Dihardja Software on 2020-02-14.
 */
object OpportunityResponse {
    /*{
        "meta": {
            "message": "Opportunity Found",
            "success": true,
            "errors": []
        },
        "data": {
            "service": {
                "id": 1,
                "createdBy": 1,
                "opportunityName": "Buy 11 truck",
                "stage": "Lost",
                "budget": 100,
                "accountId": 1,
                "deletedAt": null,
                "createdAt": "2020-02-20T05:20:56.521Z",
                "updatedAt": "2020-02-24T08:19:35.678Z"
            },
            "opportunityVisit": [
            {
                "userID": 1,
                "opportunity": {
                    "id": 1,
                    "createdBy": 1,
                    "opportunityName": "Buy 11 truck",
                    "stage": "Lost",
                    "budget": 100,
                    "accountId": 1,
                    "deletedAt": null,
                    "createdAt": "2020-02-20T05:20:56.521Z",
                    "updatedAt": "2020-02-24T08:19:35.678Z"
                },
                "account": {
                    "id": 1,
                    "accountName": "Endri Supeni  Yunita",
                    "address": "Jl Ngambar 157 A, Jawa Timur",
                    "country": "Indonesia",
                    "province": "Jawa Timur",
                    "city": "Surabaya",
                    "district": "Genteng",
                    "longitude": 112.7392,
                    "latitude": -7.25889,
                    "postalCode": "60271",
                    "phone": "08131415161718",
                    "fax": "02156778910",
                    "createdBy": 1,
                    "createdAt": "2020-02-10T05:26:35.045Z",
                    "updatedAt": "2020-02-10T05:26:35.045Z",
                    "deletedAt": null
                },
                "visit": false,
                "description": "Call Log Donec scelerisque mi varius. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eget metus sit amet felis posuere finibus. Donec scelerisque mi varius.",
                "type": "CallLog",
                "createdAt": "2020-02-24T09:17:51.264Z"
            }
        ]
        },
        "links": null,
        "include": null
    }*/

    data class Result(val meta: Meta, val data: OpportunityWithVisitData)
    data class Meta(val message: String, val success: Boolean)
    data class OpportunityWithVisitData(val service: OpportunityModel, val opportunityVisit: MutableList<OpportunityVisitModel>)
}