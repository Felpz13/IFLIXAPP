package com.example.claro.iflix.adapter

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.claro.iflix.R
import com.example.claro.iflix.model.Series

class GeneroAdapter (val items : MutableList<Series>, val context: Context) : RecyclerView.Adapter<GeneroAdapter.ItemView>()
{
    var auxGeneros : Int = 1

    inner class ItemView (iv : View) : RecyclerView.ViewHolder(iv)
    {
        val titulo : TextView
        val series : RecyclerView
        val vazio : TextView

        init
        {
            titulo = iv.findViewById(R.id.textGeneroTitulo)
            series = iv.findViewById(R.id.RecyclerGeneros)
            vazio = iv.findViewById(R.id.textVazio)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) : ItemView
    {
        val viewCard = LayoutInflater.from(p0.context).inflate(R.layout.series_generos, p0, false)

        return ItemView(viewCard)
    }

    override fun onBindViewHolder(p0: ItemView, p1: Int)
    {
        val listaGenero : MutableList<Series> = filtrarGenero(auxGeneros)

        p0.titulo.text = genero(auxGeneros)

        p0.series.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        p0.series.adapter = SeriesAdapter(listaGenero,context)

        if(listaGenero.count() == 0) p0.vazio.visibility = View.VISIBLE
        else p0.vazio.visibility = View.GONE

        auxGeneros++
        if(auxGeneros == 10) auxGeneros = 1
    }

    override fun getItemCount(): Int
    {
        return 9
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun filtrarGenero(genero : Int) : MutableList<Series>
    {
        val listaGenero : MutableList<Series> = mutableListOf()

        when(genero)
        {
            1 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Drama") listaGenero.add(items[i])
                }
            }

            2 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Suspense") listaGenero.add(items[i])
                }
            }

            3 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Crime") listaGenero.add(items[i])
                }
            }

            4 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Terror") listaGenero.add(items[i])
                }
            }

            5 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Ação") listaGenero.add(items[i])
                }
            }

            6 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Aventura") listaGenero.add(items[i])
                }
            }

            7 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Fantasia") listaGenero.add(items[i])
                }
            }

            8 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Comédia") listaGenero.add(items[i])
                }
            }

            9 ->
            {
                for(i in 0 until items.size)
                {
                    if (items[i].GENERO == "Romance") listaGenero.add(items[i])
                }
            }
        }

        return listaGenero
    }

    fun genero(genero : Int) : String
    {
        var gL = ""

        when(genero)
        {
            1 -> gL = "Drama"
            2 -> gL = "Suspense"
            3 -> gL = "Crime"
            4 -> gL = "Terror"
            5 -> gL = "Ação"
            6 -> gL = "Aventura"
            7 -> gL = "Fantasia"
            8 -> gL = "Comédia"
            9 -> gL = "Romance"
        }

        return gL
    }

}
