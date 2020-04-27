package com.hino.hearts

import com.showcase.movie.network.responses.GenresResponse
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PosNegUnitTest {

    val genre: GenresResponse.GenresModel = mockk()

    @BeforeEach
    fun setup() {

    }

    @AfterEach
    fun tearDown() {

    }

    @Test
    fun CheckID() {
        every { genre.id > 0 }
    }
}
