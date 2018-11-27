package com.example.claro.iflix.activity

import android.app.Activity
import android.os.Bundle
import com.example.claro.iflix.R
import com.example.claro.iflix.model.Series
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*

class DetalhesActivity : Activity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val serie = intent.extras!!["serie"] as Series

        textTituloDetalhe.text = serie.NOME
        textClassificacaoDetalhes.text = "${serie.CENSURA}+"
        textGeneroDetalhes.text = serie.GENERO
        textDescricaoDetalhes.text = serie.DESCRICAO

        val uri = serie.FOTO
        if (uri == "erro") imageDetalhes.setImageResource(R.drawable.ic_erro_branco)
        else Picasso.with(this).load(uri).into(imageDetalhes)
    }
}
