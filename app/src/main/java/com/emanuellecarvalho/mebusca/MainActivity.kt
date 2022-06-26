package com.emanuellecarvalho.mebusca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.emanuellecarvalho.mebusca.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconde a barra de navegação
        supportActionBar?.hide()

        val bola = Product(
            "Bola de futebol",
            2.4,
            "Uma bola de futebola amarela e branca",
            "https://img.freepik.com/vetores-gratis/imagem-preta-branca-realistica-da-bola-de-futebol_1284-8506.jpg?w=2000"
        )

        val bolaFutebol = Product(
            "Bola de tênis",
            4.8,
            "Seis bolas de tênis na cor verde-limão",
            "https://img.freepik.com/fotos-gratis/seis-bolas-de-tenis-em-simetria_23-2147661821.jpg?w=2000"
        )

        val bolaBasquete = Product(
            "Bola de basquete",
            8.7,
            "Uma bola de basquete laranja com listas pretas",
            "https://img.freepik.com/psd-gratuitas/maquete-de-bola-de-basquete_35913-2166.jpg?w=2000"
        )

        val bolaVolei = Product(
            "Bola de vôlei",
            10.9,
            "Uma bola de vôlei nas cores amarela, branca e azul. Edição limitada",
            "https://img.freepik.com/fotos-gratis/posicao-plana-do-volei-na-areia-da-praia_23-2148662653.jpg?w=2000"
        )

        val products: ArrayList<Product> = arrayListOf(bola, bolaFutebol, bolaBasquete, bolaVolei)


        //Evento de click
        binding.buttonSearch.setOnClickListener {
            val intent = Intent(this, ProductListActivity::class.java)
            intent.putParcelableArrayListExtra("PRODUCTS", products)
            startActivity(intent)
        }



        //conectar o Retrofit
        //chamada assíncrona
        val service = MeliApiClient.createCategoryService()
        val call: Call<List<CategoryListResponse>> = service.list("bola")
        call.enqueue(object : Callback<List<CategoryListResponse>> { //enqueue = colocar na fila
            override fun onResponse(
                call: Call<List<CategoryListResponse>>,
                response: Response<List<CategoryListResponse>>
            ) {
                val list = response.body()
            }

            override fun onFailure(call: Call<List<CategoryListResponse>>, t: Throwable) {
                val s = ""
            }

        })

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_Search) {
        }
    }

}



