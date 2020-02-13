package com.hino.hearts.model

import com.hino.hearts.model.CardViewModel

class OpportunityModel(
    parentIds: String?,
    name: String,
    cardList: MutableList<CardViewModel>
) {
    var parentIds: String? = parentIds
    var name: String? = name
    var cardList: MutableList<CardViewModel>? = cardList
}