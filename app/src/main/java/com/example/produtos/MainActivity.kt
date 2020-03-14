package com.example.produtos

import ProdutoAdapter
import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var service:ProdutoService? = null
    var listaProdutosRv:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create((ProdutoService::class.java))

        listaProdutosRv = findViewById(R.id.listaProdutosRv) as RecyclerView
        listaProdutosRv!!.setLayoutManager(LinearLayoutManager(this));
        listaProdutosRv?.adapter = ProdutoAdapter(List<Produto>())


        val produtoCall = service?.getAll()
        produtoCall?.enqueue(object: Callback<List<Produto>?> {
            override fun onFailure(call: Call<List<Produto>?>, t: Throwable) {
                Log.e("Error",t.message)
            }

            override fun onResponse(call: Call<List<Produto>?>, response: Response<List<Produto>?>) {
                if(response.isSuccessful){
                    response.body().let { produtos ->
                        listaProdutosRv?.adapter = ProdutoAdapter(produtos!!)
                    }
                }
            }
        })
    }
}
