package com.example.smartcity6.network

data class Login(
    val password: String,
    val username: String
)
data class Message(
    val code: Int,
    val msg: String,
    val token: String = ""
)

data class Register(
    val idCard: String,
    val nickName: String,
    val password: String,
    val phonenumber: String,
    val sex: String,
    val userName: String
)

data class HomeBanner(
    val code: String,
    val msg: String,
    val rows: List<Row>,
    val total: String
) {
    data class Row(
        val advImg: String,
        val advTitle: String,
        val id: Int,
        val servModule: String,
        val sort: Int,
        val targetId: Int,
        val type: String
    )
}

data class HomeService(
    val code: Int,
    val msg: String,
    val rows: List<Row>
) {
    data class Row(
        val id: Int,
        val imgUrl: String,
        val isRecommend: String,
        val link: String,
        val serviceDesc: String,
        val serviceName: String,
        val serviceType: String,
        val sort: Int
    )
}

data class NewsType(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val appType: String,
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val name: String,
        val params: Params,
        val parentId: Any,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val status: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class NewsList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val appType: String,
        val commentNum: Int,
        val content: String,
        val cover: String,
        val createBy: String,
        val createTime: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val params: Params,
        val publishDate: String,
        val readNum: Int,
        val remark: Any,
        val searchValue: Any,
        val status: String,
        val subTitle: Any,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String,
        val updateBy: String,
        val updateTime: String
    ) {
        class Params
    }
}

data class NewsContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val appType: String,
        val commentNum: Int,
        val content: String,
        val cover: String,
        val createBy: String,
        val createTime: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val params: Params,
        val publishDate: String,
        val readNum: Int,
        val remark: Any,
        val searchValue: Any,
        val status: String,
        val subTitle: Any,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String,
        val updateBy: String,
        val updateTime: String
    ) {
        class Params
    }
}

data class UserInfo(
    val code: Int,
    val msg: String,
    val user: User
) {
    data class User(
        val avatar: String,
        val balance: Int,
        val email: String,
        val idCard: String,
        val nickName: String,
        val phonenumber: String,
        val score: Int,
        val sex: String,
        val userId: Int,
        val userName: String
    )
}

data class User(
    val nickName: String,
    val phonenumber: String,
    val sex: String,
    val avatar:String?
)

data class Pass(
    val newPassword: String,
    val oldPassword: String
)

data class Feed(
    val content: String,
    val title: String
)

data class BusList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: String,
        val end: String,
        val endTime: String,
        val first: String,
        val id: Int,
        val mileage: String,
        val name: String,
        val params: Params,
        val price: Double,
        val remark: Any,
        val searchValue: Any,
        val startTime: String,
        val updateBy: Any,
        val updateTime: String
    ) {
        class Params
    }
}

data class Stop(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val linesId: Int,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sequence: Int,
        val stepsId: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class BusContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: String,
        val end: String,
        val endTime: String,
        val first: String,
        val id: Int,
        val mileage: String,
        val name: String,
        val params: Params,
        val price: Double,
        val remark: Any,
        val searchValue: Any,
        val startTime: String,
        val updateBy: Any,
        val updateTime: String
    ) {
        class Params
    }
}

data class AddBus(
    val end: String?,
    val path: String?,
    val price: String?,
    val start: String?,
    val status: Int
)

data class Metro(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val title: String,
        val type: String,
        val updateBy: Any,
        val updateTime: String
    ) {
        class Params
    }
}

data class CarList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val engineNo: String,
        val id: Int,
        val params: Params,
        val plateNo: String,
        val remark: Any,
        val searchValue: Any,
        val type: String,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int,
        val userName: Any
    ) {
        class Params
    }
}

data class Binding(
    val engineNo: String,
    val plateNo: String,
    val type: String
)

data class ImageUpload(
    val code: Int,
    val fileName: String,
    val msg: String,
    val url: String
)


data class MovieBanner(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val advImg: String,
        val advTitle: String,
        val appType: String,
        val createBy: String,
        val createTime: String,
        val id: Int,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val servModule: String,
        val sort: Int,
        val status: String,
        val targetId: Int,
        val type: String,
        val updateBy: String,
        val updateTime: String
    ) {
        class Params
    }
}

data class MovieList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val category: String,
        val cover: String,
        val createBy: Any,
        val createTime: Any,
        val duration: Int,
        val favoriteNum: Int,
        val id: Int,
        val introduction: String,
        val language: String,
        val likeNum: Int,
        val name: String,
        val other: Any,
        val params: Params,
        val playDate: String,
        val playType: String,
        val recommend: String,
        val remark: Any,
        val score: Int,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class MovieContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val category: String,
        val cover: String,
        val createBy: Any,
        val createTime: Any,
        val duration: Int,
        val favoriteNum: Int,
        val id: Int,
        val introduction: String,
        val language: String,
        val likeNum: Int,
        val name: String,
        val other: Any,
        val params: Params,
        val playDate: String,
        val playType: String,
        val recommend: String,
        val remark: Any,
        val score: Int,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class VolunteerBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class VolunteerNews(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val summary: String,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class ActivityList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val detail: String,
        val id: Int,
        val params: Params,
        val remark: Any,
        val requireText: String,
        val searchValue: Any,
        val startAt: String,
        val title: String,
        val undertaker: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class ActivityContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val detail: String,
        val id: Int,
        val params: Params,
        val remark: Any,
        val requireText: String,
        val searchValue: Any,
        val startAt: String,
        val title: String,
        val undertaker: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class RegisterActivity(
    val activityId: Int,
    val newState: Boolean
)

data class MyActivityList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val detail: String,
        val id: Int,
        val requireText: String,
        val startAt: String,
        val title: String,
        val undertaker: String
    )
}