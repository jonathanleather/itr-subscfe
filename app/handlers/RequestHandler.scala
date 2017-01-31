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

package handlers

import com.google.inject.{Inject, Singleton}
import play.api.Configuration
import play.api.http.{DefaultHttpRequestHandler, HttpConfiguration, HttpErrorHandler, HttpFilters}
import play.api.mvc.{Handler, RequestHeader}

@Singleton
class RequestHandler @Inject()(errorHandler: HttpErrorHandler, httpConfiguration: HttpConfiguration, filters: HttpFilters,
                               configuration: Configuration, prodRouter: prod.Routes, testRouter: testOnlyDoNotUseInAppConf.Routes)
  extends DefaultHttpRequestHandler(
    if(configuration.getString("application.router").getOrElse("").equals("testOnlyDoNotUseInAppConf.Routes")) testRouter else prodRouter,
    errorHandler, httpConfiguration, filters) {

  private def removeTrailingSlash(request: RequestHeader) = {
    super.routeRequest(request).orElse {
      Some(request.path).filter(_.endsWith("/")).flatMap(p => super.routeRequest(request.copy(path = p.dropRight(1))))
    }
  }

  override def routeRequest(request: RequestHeader): Option[Handler] = {
     removeTrailingSlash(request) match {
      case None => super.routeRequest(request)
      case result => result
    }
  }

}
