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

package controllers

import helpers.FakeRequestHelper
import play.api.mvc.{Request, Result}
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}
import play.api.test.Helpers._
import play.api.mvc.Action
import uk.gov.hmrc.passcode.authentication.PlayRequestTypes._


class TimeoutControllerSpec extends UnitSpec with FakeRequestHelper with WithFakeApplication {

  object TestController extends TimeoutController {
    override def PasscodeAuthenticatedActionAsync(body: => AsyncPlayRequest) = Action.async(body)
  }

  "timeout" should {

    lazy val result = TestController.timeout(fakeRequest)

    "return a 200" in {
      status(result) shouldBe OK
    }

    "return HTML" in {
      contentType(result) shouldBe Some("text/html")
      charset(result) shouldBe Some("utf-8")
    }

  }

}
