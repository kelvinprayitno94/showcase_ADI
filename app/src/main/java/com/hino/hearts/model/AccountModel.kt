package com.hino.hearts.model

import java.io.Serializable

/*{
    "id": 1,
    "accountName": "PT New Data",
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
}*/

data class AccountModel(val id: Int, val accountName: String, val address: String?,
                        val country: String?, val province: String?, val city: String?,
                        val district: String?, val longitude: Double?, val latitude: Double?,
                        val postalCode: String?, val phone: String?, val fax: String?) : Serializable