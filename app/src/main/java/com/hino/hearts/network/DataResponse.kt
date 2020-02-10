package com.hino.hearts.network

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class DataResponse<T : Any?> : BaseResponse() {
    var data: T? = null
}
