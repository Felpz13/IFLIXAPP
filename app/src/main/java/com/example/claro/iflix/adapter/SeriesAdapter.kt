package com.example.claro.iflix.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.claro.iflix.R
import com.example.claro.iflix.activity.DetalhesActivity
import com.example.claro.iflix.model.Series
import com.squareup.picasso.Picasso


class SeriesAdapter(val items : MutableList<Series>, val context: Context) : RecyclerView.Adapter<SeriesAdapter.ItemView>()
{
    inner class ItemView (iv : View) : RecyclerView.ViewHolder(iv)
    {
        val imagem : ImageView

        init
        {
            imagem = iv.findViewById(R.id.imageViewCapa)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemView
    {
        val viewCard = LayoutInflater.from(p0.context).inflate(R.layout.series_item, p0, false)

        return ItemView(viewCard)
    }

    override fun onBindViewHolder(p0: ItemView, p1: Int)
    {
        val uri = items[p1].FOTO

        if (uri == "erro")  p0.imagem.setImageResource(R.drawable.ic_erro_branco)
        else Picasso.with(context).load(uri).into(p0.imagem)

        p0.itemView.setOnClickListener(object : View.OnClickListener{

            override fun onClick(v: View?)
            {
                val serieSelecionado = items[p1]
                val intent = Intent(context, DetalhesActivity::class.java)

                intent.putExtra("serie", serieSelecionado)

                v!!.context.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int
    {
        return items.size
    }
}
