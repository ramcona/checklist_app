package id.rafli.mychecklist.repositories

import id.rafli.mychecklist.helper.BaseHelper
import id.rafli.mychecklist.model.User

class AuthRepo: BaseHelper(){

    suspend fun login(user: User) = ApiServiceServer.login(user)

    suspend fun register(user: User) = ApiServiceServer.register(user)

}