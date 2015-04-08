package io.dwak.meh.network

import io.dwak.meh.model.CurrentMeh
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

public trait MehService {
    public companion object{
        public val ENDPOINT_URL: String = "https://api.meh.com";
        public val ENDPOINT_VERSION : String = "/1";
        public val API_KEY : String = "";
    }
    GET("/current.json")
    public fun getCurrentMeh(Query("apikey") apiKey : String) : Observable<CurrentMeh>
}