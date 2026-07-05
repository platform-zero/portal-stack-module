package org.webservices.testrunner.suites

import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import org.webservices.testrunner.framework.*

suspend fun TestRunner.portalUtilityServicesTests() = suite("Portal Utility Service Tests") {
test("Homepage dashboard loads") {
        val response = client.getRawResponse("${env.endpoints.portal!!}")
        require(response.status == HttpStatusCode.OK) {
            "Homepage dashboard not accessible: ${response.status}"
        }

        val body = response.bodyAsText()
        require(body.contains("Datamancy") && body.contains("Keycloak") && body.contains("Grafana")) {
            "Homepage service dashboard content not detected"
        }

        println("      ✓ Homepage dashboard loads")
    }
}
