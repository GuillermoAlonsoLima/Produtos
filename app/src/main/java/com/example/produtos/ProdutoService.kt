package com.example.produtos

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProdutoService {

    @GET("produtos")
    fun getAll(): Call<List<Produto>>

}