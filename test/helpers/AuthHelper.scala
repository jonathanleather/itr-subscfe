/*
 * Copyright 2016 HM Revenue & Customs
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

package helpers

import models.{AddressModel, CompanyRegistrationReviewDetailsModel}
import play.api.libs.json.Json

object AuthHelper {

  val validJson = Json.parse(
    """{
      |"businessName":"Company",
      |"businessType":"Corporate Body",
      |"businessAddress":{
      |"line_1":"23 High Street",
      |"line_2":"Park View",
      |"line_3":"Gloucester",
      |"line_4":"Gloucestershire",
      |"postcode":"NE98 1ZZ",
      |"country":"GB"
      |},
      |"sapNumber":"1234567890",
      |"safeId":"XE0001234567890",
      |"isAGroup":false,
      |"directMatch":false,
      |"agentReferenceNumber":"JARN1234567"
      |}""".stripMargin)

  val validModel = new CompanyRegistrationReviewDetailsModel(
    "Company",
    Some("Corporate Body"),
    new AddressModel(
      "23 High Street",
      "Park View",
      Some("Gloucester"),
      Some("Gloucestershire"),
      Some("NE98 1ZZ"),
      "GB"
    ),
    "1234567890",
    "XE0001234567890",
    false,
    false,
    Some("JARN1234567")
  )

}
