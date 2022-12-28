package id.rafli.mychecklist.helper

import id.rafli.mychecklist.network.ClientService

open class BaseHelper {

    val ApiServiceServer by lazy { ClientService().create() }


}