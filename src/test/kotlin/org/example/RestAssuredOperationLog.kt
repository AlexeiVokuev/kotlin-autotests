package org.example

import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets.UTF_8

/** Implementation is not thread-safe. */
class RestAssuredOperationLog {

    private val requestLog = ByteArrayOutputStream()
    private val responseLog = ByteArrayOutputStream()
    private val requestPrintStream = PrintStream(requestLog)
    private val responsePrintStream = PrintStream(responseLog)

    private var specificationSpecified: Boolean = false

    fun forSpecification(requestSpecification: RequestSpecification) {
        requestSpecification.filters(
                RequestLoggingFilter(PrintStream(requestLog)),
                ResponseLoggingFilter(PrintStream(responseLog))
        )
        specificationSpecified = true
    }

    fun requestLog(): String {
        if (!specificationSpecified) throw IllegalStateException("RequestSpecification unspecified")
        requestPrintStream.flush()
        return String(requestLog.toByteArray(), UTF_8)
    }

    fun responseLog(): String {
        if (!specificationSpecified) throw IllegalStateException("RequestSpecification unspecified")
        responsePrintStream.flush()
        return String(responseLog.toByteArray(), UTF_8)
    }
}
