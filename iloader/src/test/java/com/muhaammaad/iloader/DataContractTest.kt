package com.muhaammaad.iloader

import com.muhaammaad.iloader.contract.implementation.DataContractImp
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class DataContractTest {
    private lateinit var testBytes: Any

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        runBlocking {
            testBytes = DataContractImp.getData("https://httpbin.org/get")!!
        }
    }

    @Test
    fun getResponse() {
        assert(testBytes as? ByteArray != null)
    }
}