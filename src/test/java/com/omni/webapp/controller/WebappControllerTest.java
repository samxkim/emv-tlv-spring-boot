package com.omni.webapp.controller;

import io.mockk.mockkClass;
import org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class WebappControllerTest {
    private val rulesService = mockkClass(RulesService::class, relaxed = true)
    private val xmlMapper = mockkClass(XmlMapper::class, relaxed = true)
    private val controller = FiservController(rulesService, xmlMapper)

    @Test
    fun `test simple credit request with authorization`() {
        val request = GMFMessageVariants()
        val creditRequestDetails = CreditRequestDetails()
        request.creditRequest = creditRequestDetails
        creditRequestDetails.commonGrp = CommonGrp()
        creditRequestDetails.commonGrp.txnType = TxnTypeType.AUTHORIZATION
        val response = controller.creditRequest(request)

        assertNotNull(response)
    }
}
