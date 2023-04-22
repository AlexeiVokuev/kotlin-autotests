package org.example

import io.qameta.allure.Step
import io.restassured.RestAssured
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers

typealias ReqSpecOp = RequestSpecification.() -> RequestSpecification
typealias ResSpecOp = ValidatableResponse.() -> ValidatableResponse

val REQ_SPEC_NOP: RequestSpecification.() -> RequestSpecification = { this }
val RES_SPEC_NOP: ValidatableResponse.() -> ValidatableResponse = { this }

fun restAssured(
        method: HttpMethod,
        url: String,
        givenOp: ((RequestSpecification) -> RequestSpecification) = { it },
        encodeUrl: Boolean
): Pair<ValidatableResponse, RestAssuredOperationLog> {
    RestAssured.useRelaxedHTTPSValidation()
    val opLog = RestAssuredOperationLog()
    val response = RestAssured
            .given()
            .urlEncodingEnabled(encodeUrl)
            .logOperation(opLog)
            .let { givenOp.invoke(it) }
            .`when`()
            .let { request ->
                when (method) {
                    HttpMethod.GET -> request.get(url)
                    HttpMethod.PUT -> request.put(url)
                    HttpMethod.POST -> request.post(url)
                    HttpMethod.DELETE -> request.delete(url)
                }
            }
            .then()
    return response to opLog
}

enum class HttpMethod {
    GET, POST, PUT, DELETE
}

fun RequestSpecification.logOperation(operationLog: RestAssuredOperationLog): RequestSpecification {
    operationLog.forSpecification(this)
    return this
}

fun RequestSpecification.When() = `when`()

@Step("Check json response status")
fun ValidatableResponse.bodyStatusCode(code: Int): ValidatableResponse =
    body("status.code", Matchers.equalTo(code))

fun ValidatableResponse.toJsonText(): JsonText = JsonText(extract().response().jsonPath())