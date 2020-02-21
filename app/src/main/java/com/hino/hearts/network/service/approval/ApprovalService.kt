package com.hino.hearts.network.service.approval

import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.network.response.approve.ApprovalListResponse
import com.hino.hearts.network.response.user.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface ApprovalService {
    @GET("approval")
    fun ApprovalList(): Deferred<ApprovalListResponse>
}