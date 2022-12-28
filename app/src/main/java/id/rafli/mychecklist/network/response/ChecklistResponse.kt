package id.rafli.mychecklist.network.response

import id.rafli.mychecklist.model.User
import java.io.Serializable

class ChecklistResponse : Serializable {
    var statusCode:Int = 0
    var errorMessage:String = ""
    var message:String = ""
    var data:User = User()
}