package com.example.nicepaysnap.nicepay.model

class RequestEwalletUrlParam {
    var url: String? = null
    var type: String? = null
    var isDeeplink: String? = null

    constructor()
    constructor(url: String?, type: String?, isDeeplink: String?) {
        this.url = url
        this.type = type
        this.isDeeplink = isDeeplink
    }
}
