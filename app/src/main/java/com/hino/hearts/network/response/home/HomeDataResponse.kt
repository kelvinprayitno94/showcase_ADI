package com.hino.hearts.network.response.home

/**
 * Created by Dihardja Software on 2020-02-20.
 */
object HomeDataResponse {
//    {
//        "meta": {
//        "message": "Home Data",
//        "success": true,
//        "errors": []
//    },
//        "data": {
//        "username": "Joshua",
//        "monthlyVisit": 10,
//        "totalOpportunity": 100000,
//        "todayVisit": [
//        {
//            "id": 1,
//            "organization": "PT Dihardja Software",
//            "visit": true
//        },
//        {
//            "id": 2,
//            "organization": "PT Bayu Kencana",
//            "visit": false
//        },
//        {
//            "id": 3,
//            "organization": "PT Nathan Bunis",
//            "visit": false
//        },
//        {
//            "id": 4,
//            "organization": "PT Test",
//            "visit": false
//        }
//        ]
//    },
//        "links": null,
//        "include": null
//    }

    data class Result(val meta: Meta, val data: Data, val links: String, val include: String)
    data class Meta(val message: String, val success: Boolean)
    data class Data(val userName: String, val monthlyVisit: Int, val totalOpportunity: Int, val todayVisit: ArrayList<TodayVisit>)
    data class TodayVisit(val id: Int, val organization: String, val visit: Boolean)
}