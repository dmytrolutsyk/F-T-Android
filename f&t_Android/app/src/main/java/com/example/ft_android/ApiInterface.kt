import com.example.ft_android.Annonce
import com.example.ft_android.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("signin")
    fun signin(@Body info: SignInBody): retrofit2.Call<SignInResult>

    @Headers("Content-Type:application/json")
    @POST("signup")
    fun signup(@Body info: SignUpBody): retrofit2.Call<SignUpResult>

    @Headers("Content-Type:application/json")
    @PUT("annonces")
    fun putannonce(@Header("x-access-token") token: String, @Body info: PutAnnonce): retrofit2.Call<PutAnnonceResult>

    @Headers("Content-Type:application/json")
    @GET("annonces/all")
    fun getannonces(@Header("x-access-token") token: String): retrofit2.Call<GetAnnoncesResult>

    @Headers("Content-Type:application/json")
    @PATCH("/users/5f4a96509906490017cec845")
    fun patchuser(@Header("x-access-token") token: String, @Body info: PatchUser): retrofit2.Call<PatchUserResult>

}
class RetrofitInstance {
    companion object {
        //val BASE_URL: String = "https://findandtrade.herokuapp.com/"
        val BASE_URL: String = "http://192.168.43.174:3000/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
            //this.connectTimeout(30, TimeUnit.SECONDS)
            //this.writeTimeout(30, TimeUnit.SECONDS)
            //this.readTimeout(30, TimeUnit.SECONDS)
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
data class SignUpBody(val username: String, val password: String)
data class SignUpResult(val createdId: String, val error: String, val token: String)
data class PutAnnonce(val title: String, val description: String, val category: String, val type: String, val photos: String)
data class PutAnnonceResult(val error: String, val annonce: Annonce)
data class GetAnnonces(val _id: String, val userID: String, val title: String, val category: String, val description: String, val type: String, val photos: String, val createdAt: String, val lastUpdatedAt: String, val username: String)
data class GetAnnoncesResult(val error: String, val annonces : Array<Annonce> )
data class PatchUser (val username: String, val password: String, val ville: String, val email: String, val status_user: String, val phone: String)
data class PatchUserResult(val error: String, val user: User)



