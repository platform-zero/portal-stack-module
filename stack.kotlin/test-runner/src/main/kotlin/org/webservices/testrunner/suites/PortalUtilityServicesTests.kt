package org.webservices.testrunner.suites

import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import org.webservices.testrunner.framework.*

suspend fun TestRunner.portalUtilityServicesTests() = suite("Portal Utility Service Tests") {
test("Stack Portal dashboard loads") {
        val response = client.getRawResponse("${env.endpoints.portal!!}")
        require(response.status == HttpStatusCode.OK) {
            "Stack Portal not accessible: ${response.status}"
        }

        val body = response.bodyAsText()
        require(body.contains("Stack Portal") && body.contains("Role dashboards")) {
            "Stack Portal content not detected"
        }

        println("      ✓ Stack Portal dashboard loads")
    }

    test("Stack Portal serves generated module API") {
        
        val response = client.getRawResponse("${env.endpoints.portal!!}/api/modules") {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
        }

        require(response.status == HttpStatusCode.OK) {
            "Portal module API not loading: ${response.status}"
        }

        val body = response.bodyAsText()
        require(body.contains("\"component\"") || body == "[]") {
            "Portal module API returned unexpected payload: $body"
        }

        println("      ✓ Stack Portal generated module API accessible")
    }

    test("Stack Portal profile API endpoint accessible") {
        
        val response = client.getRawResponse("${env.endpoints.portal!!}/api/profiles")
        
        require(response.status == HttpStatusCode.OK) {
            "Portal profile API should respond, got ${response.status}"
        }

        println("      ✓ Stack Portal profile API endpoint responds")
    }
}
