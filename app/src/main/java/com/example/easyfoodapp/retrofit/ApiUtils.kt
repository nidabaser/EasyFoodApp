package com.example.easyfoodapp.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodsDaoInterface(): FoodsDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(FoodsDaoInterface::class.java)
        }
    }
}