package org.webservices.testrunner.suites

import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import org.webservices.testrunner.framework.*

suspend fun TestRunner.portalUtilityServicesTests() = suite("Portal Utility Service Tests") {
    test("Homepage dashboard loads") {
        val response = client.getRawResponse("http://portal:3000")
        require(response.status == HttpStatusCode.OK) {
            "Homepage dashboard not accessible: ${response.status}"
        }

        val body = response.bodyAsText()
        require(
            body.contains("<title data-next-head=\"\">Homepage</title>") &&
                body.contains("A highly customizable homepage") &&
                body.contains("service-card")
        ) {
            "Homepage service dashboard content not detected"
        }

        println("      ✓ Homepage dashboard loads")
    }
}
