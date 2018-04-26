package br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao

import android.util.Log
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Tarefa
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.BuscaTodasTarefasService
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.TarefaRemotaListener
import br.edu.ifpr.paranagua.tads_recyclerview.ui.TarefasAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by aluno on 26/04/18.
 */
class TarefaDaoRemoto(val listener: TarefaRemotaListener) {

    private var retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/tarefas/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun buscarTodas() {

        var service = retrofit.create(BuscaTodasTarefasService::class.java)

        var call = service.buscaTodas()
        call.enqueue(object: Callback<List<Tarefa>> {
            override fun onFailure(call: Call<List<Tarefa>>?, t: Throwable?) {}

            override fun onResponse(call: Call<List<Tarefa>>?, response: Response<List<Tarefa>>?) {
                var tarefas: List<Tarefa> = response?.body()!!
                listener.onBuscaTodasReturn(tarefas)
//                val adapter = TarefasAdapter(tarefas)
//                listTarefas.adapter = adapter

                Log.d("TAREFAS", response.toString())
            }
        })
    }
}



