package com.example.claro.iflix.activity

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.claro.iflix.model.Series
import com.example.claro.iflix.R
import com.example.claro.iflix.adapter.GeneroAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{
    lateinit var dir : DatabaseReference
    lateinit var listaSeries : MutableList<Series>
    lateinit var adapter : GeneroAdapter



    override fun onCreate(savedInstanceState: Bundle?)
    {

        dir = FirebaseDatabase.getInstance().reference
        listaSeries = mutableListOf()
        adapter = GeneroAdapter(listaSeries,applicationContext)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dir.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(p0: DataSnapshot)
            {
                if (p0.exists())
                {
                    listaSeries.clear()

                    for(i in 0 until  p0.child("/series").childrenCount)
                    {
                        val serieLocal = Series(
                            p0.child("/series").child("$i").child("CENSURA").value.toString(),
                            p0.child("/series").child("$i").child("CODIGO").value.toString(),
                            p0.child("/series").child("$i").child("DESCRICAO").value.toString(),
                            imgAdapter(p0.child("/series").child("$i").child("FOTO").value.toString()),
                            p0.child("/series").child("$i").child("GENERO").value.toString(),
                            p0.child("/series").child("$i").child("NOME").value.toString(),
                            p0.child("/series").child("$i").child("TEMPORADAS").value.toString(),
                            p0.child("/series").child("$i").child("TRAILER").value.toString()
                        )
                        listaSeries.add(serieLocal)

                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError)
            {
                Log.e("TESTE", "erro!!!")
            }
        })

        RecyclerGeneros.visibility = View.INVISIBLE
        progressBar2.visibility = View.VISIBLE

        val task = AsyncTaskLista()
        task.execute()

    }

    fun imgAdapter (foto : String) : String
    {
        var fotoUrl = "erro"

        if (foto == "Imagens/Capas/1.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/1.png?alt=media&token=74bc7cb4-4ac5-4a6d-8fa3-3731def57506"
        else if(foto == "Imagens/Capas/2.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/2.png?alt=media&token=e95ae9ac-ad79-4de7-b867-2bbd9de6503f"
        else if(foto == "Imagens/Capas/3.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/3.png?alt=media&token=6fcd627a-1028-4f01-b29f-a16efa3b82cc"
        else if(foto == "Imagens/Capas/4.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/4.png?alt=media&token=1accaa55-3373-4e12-9d3e-67d9b91ef67c"
        else if(foto == "Imagens/Capas/5.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/5.png?alt=media&token=d9db5acc-6d2e-436e-a09c-6d2c957bae85"
        else if(foto == "Imagens/Capas/6.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/6.png?alt=media&token=cccdd149-7be3-4c63-8920-6cd0801d81f6"
        else if(foto == "Imagens/Capas/7.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/7.png?alt=media&token=a96cbac2-44f8-4379-9b5f-76bb9f600ddd"
        else if(foto == "Imagens/Capas/8.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/8.png?alt=media&token=479990e7-b8e8-414a-9436-7229097ef476"
        else if(foto == "Imagens/Capas/9.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/9.png?alt=media&token=d9796d97-720b-4a3e-ae4b-c19a0b8a83a6"
        else if(foto == "Imagens/Capas/10.png") fotoUrl = "https://firebasestorage.googleapis.com/v0/b/iflix-249c7.appspot.com/o/10.png?alt=media&token=9ad3a0e8-f714-4f85-998a-99c38e6d7d9b"

        return fotoUrl
    }

    inner class AsyncTaskLista : AsyncTask<Void, Void, String>()
    {

        override fun doInBackground(vararg params: Void?): String
        {

            RecyclerGeneros.layoutManager = LinearLayoutManager(applicationContext)
            RecyclerGeneros.setItemViewCacheSize(9)
            RecyclerGeneros.adapter = adapter

            Thread.sleep(1000)

            return "Carregado"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            RecyclerGeneros.visibility = View.VISIBLE
            progressBar2.visibility = View.INVISIBLE
        }
    }
}
