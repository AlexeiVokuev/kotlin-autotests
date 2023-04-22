package org.example

class AutotestContext(
    val operations: List<AutotestOperation>
)

/** Interface for generic autotest operation like request/response pair, send or receive message, etc. */
interface AutotestOperation {
    fun textRepresentation(): String
}

class HttpOperation(private val operationLog: RestAssuredOperationLog) : AutotestOperation {
    override fun textRepresentation(): String {
        val response = operationLog.responseLog()
        return operationLog.requestLog() + response.ifEmpty { "" }
    }
}

/** Alias for [apply]. */
inline fun <T> T.validate(validation: T.() -> Unit) = apply { validation() }
