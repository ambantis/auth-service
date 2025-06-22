package com.ambantis.auth

import com.ambantis.auth.UserRegistry.ActionPerformed
import spray.json.DefaultJsonProtocol
//#json-formats
import spray.json.RootJsonFormat

object JsonFormats {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val userJsonFormat: RootJsonFormat[User] = jsonFormat3(User.apply)
  implicit val usersJsonFormat: RootJsonFormat[Users] = jsonFormat1(Users.apply)

  implicit val actionPerformedJsonFormat: RootJsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed.apply)
}
//#json-formats
