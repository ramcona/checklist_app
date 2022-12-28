package id.rafli.mychecklist.repositories

import id.rafli.mychecklist.helper.BaseHelper

class ChecklistRepo: BaseHelper(){

    suspend fun getAllCheclist(token:String) = ApiServiceServer.getAllChecklist(token)

}