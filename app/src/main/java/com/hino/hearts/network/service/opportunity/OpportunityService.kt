package com.hino.hearts.network.service.opportunity

import com.hino.hearts.network.response.opportunity.OpportunityListResponse
import com.hino.hearts.network.response.opportunity.OpportunityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface OpportunityService {
    @GET("opportunity")
    fun getAllOpportunities(@Query("page") page: Int, @Query("limit") limit: Int?): Call<OpportunityListResponse.Result>

    @GET("opportunity/{opportunityId}")
    fun getOpportunity(@Path("opportunityId") opportunityId: Int): Call<OpportunityResponse.Result>
}