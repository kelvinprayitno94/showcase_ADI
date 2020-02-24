package com.hino.hearts.model

import java.io.Serializable

/*
  "id": 1,
  "createdBy": 1,
  "opportunityName": "Buy 11 truck",
  "stage": "Not Started",
  "budget": 100,
  "accountId": 1,
  "deletedAt": null,
  "createdAt": "2020-02-13T07:21:25.773Z",
  "updatedAt": "2020-02-13T09:59:57.793Z",
  "account": { AccountModel }
*/

data class OpportunityModel(val id: Int, val opportunityName: String, var stage: String,
                            val account: AccountModel?, val budget: Long) : Serializable