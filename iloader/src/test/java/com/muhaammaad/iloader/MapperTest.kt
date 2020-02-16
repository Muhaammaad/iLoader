package com.muhaammaad.iloader

import com.muhaammaad.iloader.contract.implementation.DataContractImp
import com.muhaammaad.iloader.util.Mapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MapperTest {
    @Mock
    private lateinit var mapper: Mapper<ByteArray, String>

    private lateinit var testBytes: Any

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        runBlocking {
            testBytes = DataContractImp.getData("https://httpbin.org/get")!!
        }

        `when`(mapper.map(testBytes as ByteArray))
            .thenAnswer { invocation -> String(invocation.arguments.get(0) as ByteArray) }
    }

    @Test
    fun checkMapperResponse() {
        assert(testBytes as? ByteArray != null)
        val isMapped = mapper.map(testBytes as ByteArray) != "{\n" +
                "  \"args\": {}, \n" +
                "  \"headers\": {\n" +
                "    \"Accept-Encoding\": \"gzip\", \n" +
                "    \"Host\": \"httpbin.org\", \n" +
                "    \"User-Agent\": \"okhttp/4.3.1\", \n" +
                "    \"X-Amzn-Trace-Id\": \"Root=1-5e49a346-29d025b4dda6fca8637af5b2\"\n" +
                "  }, \n" +
                "  \"origin\": \"92.97.66.142\", \n" +
                "  \"url\": \"https://httpbin.org/get\"\n" +
                "}\n"
        assert(isMapped)
    }
}
