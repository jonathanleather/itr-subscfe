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

package utils

import org.scalatestplus.play.{OneServerPerSuite, PlaySpec}

class CountriesHelperSpec extends PlaySpec with OneServerPerSuite {

  "CountriesHelper" must {

    "getSelectedCountry" must {
      "bring the correct country from the file" in {
        CountriesHelper.getSelectedCountry("GB") must be("United Kingdom")
        CountriesHelper.getSelectedCountry("US") must be("USA")
        CountriesHelper.getSelectedCountry("VG") must be("British Virgin Islands")
        CountriesHelper.getSelectedCountry("UG") must be("Uganda")
        CountriesHelper.getSelectedCountry("zz") must be("zz")
      }
    }

    "getIsoCodeMap" must {
      "return map of country iso-code to country name" in {
        CountriesHelper.getIsoCodeTupleList must contain(("US" , "USA :United States of America"))
        CountriesHelper.getIsoCodeTupleList must contain(("GB" , "United Kingdom :UK, GB, Great Britain"))
        CountriesHelper.getIsoCodeTupleList must contain(("UG" , "Uganda"))
      }
    }
  }

}
