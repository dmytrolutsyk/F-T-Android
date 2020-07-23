import com.example.ft_android.Annonce
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("signin")
    fun signin(@Body info: SignInBody): retrofit2.Call<SignInResult>

    @Headers("Content-Type:application/json")
    @PUT("annonces")
    fun putannonce(@Header("x-access-token") token: String, @Body info: PutAnnonce): retrofit2.Call<PutAnnonceResult>


}
class RetrofitInstance {
    companion object {
        //val BASE_URL: String = "https://findandtrade.herokuapp.com/"
        val BASE_URL: String = "http://192.168.4.29:3000/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return builder
        }
    }
}


data class SignInBody(val username: String, val password: String)
data class SignInResult(val error: String, val UserId: String, val token: String)
data class PutAnnonce(val title: String, val description: String, val category: String, val type: String, val photos: String)
data class PutAnnonceResult(val error: String, val annonce: Annonce)



