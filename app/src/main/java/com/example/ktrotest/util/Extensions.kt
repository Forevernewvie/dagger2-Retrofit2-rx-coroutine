package com.example.ktrotest.util

import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

suspend fun HttpClient.fetchBoxOfficeData(
   targetDt:String,
):List<DailyBoxOffice>{
    return this.get<OfficeResult>(Api.URL) {
        method = HttpMethod.Get
        parameter("key", Api.KEY)
        parameter("targetDt", targetDt)
    }.boxOfficeResult.dailyBoxOfficeList


}

fun Int.dateFormat():String{
    val intToString = this.toString()
    return if(intToString.length<2){
        intToString.format("0${this}")
    }else{
        intToString
    }
}

//1. key가 잘못된 경우
//2. 날짜가 잘못된 경우
//3. 미래의 날짜나 옛날 정보 불러와서 정보가 없는 경우
//4. 걍 인터넷이 삐꾸난 경우

//ktor는 알아서 200대 코드를 처리하고
//400대 같은 경우는 에러를 보여준다
//100대와 300대는 따로 설정 해줘야 된다.

