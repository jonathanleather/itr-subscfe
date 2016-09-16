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

package controllers

import java.util.UUID
import config.{FrontendAuthConnector, FrontendAppConfig}
import auth.AuthorisedForTAVC
import uk.gov.hmrc.play.frontend.controller.FrontendController
import uk.gov.hmrc.play.http.{SessionKeys, HeaderCarrier}
import uk.gov.hmrc.play.http.logging.SessionId
import play.api.mvc._
import scala.concurrent.Future
import views.html.introduction._

object IntroductionController extends IntroductionController{
  override lazy val applicationConfig = FrontendAppConfig
  override lazy val authConnector = FrontendAuthConnector
}

trait IntroductionController extends FrontendController with AuthorisedForTAVC{

  implicit val hc = new HeaderCarrier()

  def show:Action[AnyContent] = Authorised.async { implicit user =>
    implicit request =>
      Future.successful(Ok(Introduction()))
  }

  val submit = Action.async { implicit request =>
    Future.successful(Ok)
  }
}
