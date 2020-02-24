package com.hino.hearts.network.service.opportunity

import com.hino.hearts.network.response.opportunity.ChangeStatusResponse
import com.hino.hearts.network.response.opportunity.OpportunityListResponse
import com.hino.hearts.network.response.opportunity.OpportunityResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface OpportunityService {
    @GET("opportunity")
    fun getAllOpportunities(@Query("page") page: Int, @Query("limit") limit: Int?, @Query("accountId") accountId: Int?): Call<OpportunityListResponse.Result>

    @GET("opportunity/{opportunityId}")
    fun getOpportunity(@Path("opportunityId") opportunityId: Int): Call<OpportunityResponse.Result>

    @PUT("opportunity/{opportunityId}/status")
    fun changeStatus(@Path("opportunityId") opportunityId: Int, @Body status: StatusRequestBody): Call<ChangeStatusResponse.Result>
}