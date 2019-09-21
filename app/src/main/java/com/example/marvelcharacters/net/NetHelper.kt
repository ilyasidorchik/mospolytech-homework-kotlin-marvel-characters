package com.example.marvelcharacters.net

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

// Хеш
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

class NetHelper {
    lateinit var requestInterface: Retrofit

    companion object {
        val instance = NetHelper()
        const val BASE_URL = "https://gateway.marvel.com/"
        const val publicKey = "ae97102077b1811aba0b9d276b226b9f"
        const val privateKey = "ee47579f7d0d21bffaae33e6d68b4fc202b14b01"
    }

    init {
        initClient()
    }

    private fun initClient() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        requestInterface = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okClient)
            .build()
    }

    fun getCharacters(): Observable<CharacterResponse> {
        // ts - a timestamp (or other long string which can change on a request-by-request basis)
        val ts = 1

        // hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
        val hash = "$ts$privateKey$publicKey".md5()
        val interfaceObj = requestInterface.create(GetData::class.java)
        return interfaceObj.getData(publicKey, ts, hash)
    }

}