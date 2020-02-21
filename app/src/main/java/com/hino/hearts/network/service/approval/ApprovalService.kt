package com.hino.hearts.network.service.approval

import com.hino.hearts.network.response.ErrorResponse
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.network.response.approve.ApprovalListResponse
import com.hino.hearts.network.response.user.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface ApprovalService {
    @GET("approval")
    fun ApprovalList(): Deferred<ApprovalListResponse>

    @PUT("approval/{id}")
    @FormUrlEncoded
    fun Approve(@Path("id") id: String, @Field("approverIndex") index:Int): Deferred<ErrorResponse>
}