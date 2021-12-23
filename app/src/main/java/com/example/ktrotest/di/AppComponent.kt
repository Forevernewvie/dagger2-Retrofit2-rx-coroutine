package com.example.ktrotest.di

import android.content.Context
import com.example.ktrotest.di.data.LocalModule
import com.example.ktrotest.di.data.RemoteModule
import com.example.ktrotest.di.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// dagger2 위밋 앱 코드 참조 작성
// dagger2 자체(subComponet 생성시 그래프가 어디 밑으로 그려지는지) 및 어노테이션에 대한 이해 더 필요 ex) factory, binds 등

@Singleton
@Component(modules = [RemoteModule::class,RepositoryModule::class ,LocalModule::class,ViewModelModule::class,ViewModelFactoryModule::class,SubComponentModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create( @BindsInstance context: Context) : AppComponent
    }

    fun mainComponent() : MainComponent.Factory

}

//@BindsInstance 인스턴스를 구성요소에 바인딩
//AppComponent가 context를 요소로 가지고 이 컴포넌트에 바인딩된 모듈에서는 context 자유롭게 사용가능

/*
빌더 단점
1. 주입해 줄 속성이 있다면 @BindsInstance + 함수 하나, 반환형 Builder로 다 만들어줘야됨로 ex)
       // context 종속성 바인딩 함수
        @BindsInstance
        fun application(context: Context): Builder

        // age 종속성 바인딩 함수
        @BindsInstance
        fun age(age: Int): Builder

2. 빌더 할때도 메서드 체이닝 하나하나 다 설정해주어야됨 ex)
DaggerAppComponent
    .builder().application(this) .age(20)
*/


/*
팩토리
1. 편하다
2. 좋다
3. 모듈에 주입해줄 뭔가가 있다면(ex context..etc) @BindsInstance로 매개변수를 늘려주면 된다
 */