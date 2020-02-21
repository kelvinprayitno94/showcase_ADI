package com.hino.hearts.network.service.account

import com.hino.hearts.network.response.account.AccountDetailResponse
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.network.response.user.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface AccountService {
    @GET("accounts")
    fun fetchAccountList(
        @Query("page") employeeId: String?,
        @Query("limit") password: String?
    ): Deferred<AccountListResponse>

    @GET("accounts")
    fun fetchAccountDetail(): Deferred<AccountDetailResponse>
}