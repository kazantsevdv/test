package com.example.test.api



sealed class ResponseData <out T : Any>{
    data class Success<out T : Any>(val value:T) : ResponseData<T>()
    data class Error<out T : Any>(val error: Throwable) : ResponseData<Nothing>()
    data class Loading<out T : Any>(val progress: Int?) : ResponseData<Nothing>()
}


//liveData.value = ResponseData.Loading(null)
//liveData.value = ResponseData.Error(t)
//liveData.value =ResponseData.Error(Throwable(message))
//liveData.value =ResponseData.Success(response.body()!!)
//
//
//fun render(data:ResponseData) {
//    when (data) {
//        is ResponseData.Success -> {
//
//        }
//        is ResponseData.Loading -> {
//        }
//        is ResponseData.Error -> {
//
//        }
//    }
//}


//sealed class Abrakadabra<out T : Any> {
//    data class Success<out T : Any>(val value: T) : Abrakadabra<T>()
//    data class Error(val message: String, val cause: Exception? = null) : Abrakadabra<Nothing>()
//    object Loading : Abrakadabra<Nothing>()
//}
//val <T> T.exhaustive: T
//    get() = this
//
//fun makeCalculationOfRealTask(): Boolean { return result of real task }
//
//fun getResult(): Abrakadabra<Int> {
//    val valid: Boolean = true // makeCalculationOfRealTask()
//    if (valid) {
//        return Abrakadabra.Success(1001)
//    } else {
//        return Abrakadabra.Error("accident happened", IOException("disaster"))
//    }
//}
//fun main(args: Array<String>) {
//    when(val result = getResult()) {
//        is Abrakadabra.Success -> println("succeeded")
//        is Abrakadabra.Error -> println("error: " + result.cause?.message)
//        Abrakadabra.Loading -> println("loading...")
//    }.exhaustive
//}