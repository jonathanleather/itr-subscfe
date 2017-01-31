/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package connectors

import auth.MockConfig
import common.BaseTestSpec
import models.etmp.{CorrespondenceDetailsModel, SubscriptionTypeModel}
import org.mockito.Matchers
import org.mockito.Mockito._
import play.api.libs.json.JsValue
import uk.gov.hmrc.play.http.HttpResponse
import play.api.test.Helpers._

class SubscriptionConnectorSpec extends BaseTestSpec {

  val safeID = "ABC123"
  val postcode = "AB11AB"
  val subscriptionModel = SubscriptionTypeModel(CorrespondenceDetailsModel(None,None,None))

  val testConnector = new SubscriptionConnectorImpl(mockHttp, MockConfig)

  "subscribe" should {

    "create a url using the safeID and postcode" in {
      when(mockHttp.POST[JsValue, HttpResponse](Matchers.eq(s"${testConnector.serviceUrl}/investment-tax-relief-subscription/$safeID/$postcode/subscribe"),
        Matchers.any(),Matchers.any())(Matchers.any(),Matchers.any(),Matchers.any())).thenReturn(HttpResponse(OK))
      val result = testConnector.subscribe(subscriptionModel,safeID,postcode)
      await(result).status shouldBe OK
    }

  }

}
