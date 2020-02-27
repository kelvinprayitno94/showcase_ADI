package com.hino.hearts.network.service.visit

import com.hino.hearts.network.response.opportunity.ChangeStatusResponse
import com.hino.hearts.network.response.opportunity.OpportunityListResponse
import com.hino.hearts.network.response.opportunity.OpportunityResponse
import com.hino.hearts.network.response.visit.CreateVisitResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface VisitService {
    @POST("visit")
    fun createVisit(@Body visit: VisitRequestBody): Call<CreateVisitResponse.Result>
}