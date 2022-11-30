package com.example.smartcity6.network

import com.example.smartcity6.SmartCityApplication
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface SmartCityApi {
    @POST("/prod-api/api/login")
    fun postLogin(@Body user:Login):Call<Message>

    @POST("/prod-api/api/register")
    fun postRegister(@Body register: Register):Call<Message>

    @GET("/prod-api/api/rotation/list")
    fun getHomeBanner():Call<HomeBanner>

    @GET("/prod-api/api/service/list")
    fun getHomeService():Call<HomeService>

    @GET("/prod-api/press/category/list")
    fun getNewsType():Call<NewsType>

    @GET("/prod-api/press/press/list")
    fun getHot(@Query("hot")hot:String = "Y"):Call<NewsList>

    @GET("/prod-api/press/press/list")
    fun getNewsList(@Query("title")title:String?,@Query("type")type:String?):Call<NewsList>

    @GET("/prod-api/press/press/{id}")
    fun getNewsContent(@Query("id")id:Int):Call<NewsContent>

    @GET("/prod-api/api/common/user/getInfo")
    fun getUserInfo(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<UserInfo>

    @PUT("/prod-api/api/common/user")
    fun putUser(@Body user:User,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @PUT("/prod-api/api/common/user/resetPwd")
    fun putPass(@Body pass:Pass,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @POST("/prod-api/api/common/feedback")
    fun postFeed(@Body feed:Feed,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/bus/line/list")
    fun getBusList():Call<BusList>

    @GET("/prod-api/api/bus/stop/list")
    fun getStop(@Query("linesId")linesId:Int):Call<Stop>

    @GET("/prod-api/api/bus/line/{id}")
    fun getBusContent(@Path("id")id: Int):Call<BusContent>

    @POST("/prod-api/api/bus/order")
    fun postBus(@Body order:AddBus,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/metro/statement")
    fun getMetro():Call<Metro>

    @GET("/prod-api/api/traffic/car/list")
    fun getCArList(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<CarList>

    @POST("/prod-api/api/traffic/car")
    fun postBinding(@Body car:Binding,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @PUT("/prod-api/api/traffic/car")
    fun putBinding(@Body car:Binding,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @Multipart
    @POST("prod-api/common/upload")
    fun upload(@Part file:MultipartBody.Part, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<ImageUpload>

    @GET("/prod-api/api/movie/rotation/list")
    fun getMovieBanner():Call<MovieBanner>

    @GET("/prod-api/api/movie/film/list")
    fun getMovieList(@Query("name")name:String?):Call<MovieList>

    @GET("/prod-api/api/movie/film/detail/{id}")
    fun getMovieContent(@Path("id")id:Int):Call<MovieContent>

    @GET("/prod-api/api/volunteer-service/ad-banner/list")
    fun getVolunteerBanner( @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<VolunteerBanner>

    @GET("/prod-api/api/volunteer-service/news/list")
    fun  getVolunteerNews( @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<VolunteerNews>

    @GET("/prod-api/api/volunteer-service/activity/list")
    fun getActivityList(@Query("title")title:String?, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<ActivityList>

    @GET("/prod-api/api/volunteer-service/activity/{id}")
    fun getActivityContent(@Path("id")id: Int, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<ActivityContent>

    @POST("/prod-api/api/volunteer-service/register")
    fun postRegisterActivity(@Body register:RegisterActivity,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/volunteer-service/activity/my-list/{state}")
    fun getMyActivity(@Path("state")state:String?,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<MyActivityList>

}