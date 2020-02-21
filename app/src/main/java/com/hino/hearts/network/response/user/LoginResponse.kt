package com.hino.hearts.network.response.user

/**
 * Created by Dihardja Software on 2020-02-14.
 */
object LoginResponse {
//    {
//        "meta": {
//        "message": "Login success",
//        "success": true,
//        "errors": []
//    },
//        "data": {
//        "userData": {
//        "id": 1,
//        "email": "admin@hino.com",
//        "phoneNumber": "081314859652",
//        "name": "admin",
//        "roleId": 1,
//        "employeeId": "test12345",
//        "deletedAt": null,
//        "created_at": "2020-02-10T05:26:35.045Z",
//        "updated_at": "2020-02-10T05:26:35.045Z",
//        "role": {
//        "id": 1,
//        "name": "Sales Area",
//        "createdAt": "2020-02-10T05:26:35.045Z",
//        "updatedAt": "2020-02-10T05:26:35.045Z",
//        "deletedAt": null
//    }
//    },
//        "token": "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJhZG1pbkBoaW5vLmNvbSIsInBob25lTnVtYmVyIjoiMDgxMzE0ODU5NjUyIiwibmFtZSI6ImFkbWluIiwicm9sZUlkIjoxLCJlbXBsb3llZUlkIjoidGVzdDEyMzQ1IiwiZGVsZXRlZEF0IjpudWxsLCJjcmVhdGVkX2F0IjoiMjAyMC0wMi0xMFQwNToyNjozNS4wNDVaIiwidXBkYXRlZF9hdCI6IjIwMjAtMDItMTBUMDU6MjY6MzUuMDQ1WiIsInJvbGUiOnsiaWQiOjEsIm5hbWUiOiJTYWxlcyBBcmVhIiwiY3JlYXRlZEF0IjoiMjAyMC0wMi0xMFQwNToyNjozNS4wNDVaIiwidXBkYXRlZEF0IjoiMjAyMC0wMi0xMFQwNToyNjozNS4wNDVaIiwiZGVsZXRlZEF0IjpudWxsfSwiaWF0IjoxNTgyMjUwOTEzLCJleHAiOjE1ODIyODI0NDksImlzcyI6IkRTUyIsInN1YiI6Imhpbm8tZGV2IiwianRpIjoiRFNTaGlubyJ9.gdZf8fJi40yOvIhylADTS1TBFdKtoRqc3OccgSlKulw"
//    },
//        "links": null,
//        "include": null
//    }

    data class Result(val meta: Meta, val data: Data, val links: String, val include: String)
    data class Meta(val message: String, val success: Boolean)

    data class Data(val userData: UserData?, val token: String?)
    data class UserData(val id: Int, val email: String, val phoneNumber: String, val name: String, val roleId: Int, val employeeId: String, val role: Role)
    data class Role(val id: Int, val name: String)
}