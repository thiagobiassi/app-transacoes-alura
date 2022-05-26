package br.com.alura.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.extension.limitaEmAte
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>,
    private val context: Context
) : BaseAdapter() {

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    private val LIMITE_DE_CATEGORIA = 14

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada =
            LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        adicionarValor(transacao, viewCriada)
        adicionarCategoria(viewCriada, transacao)
        adicionarData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionarData(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionarCategoria(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(LIMITE_DE_CATEGORIA)
    }

    private fun adicionarValor(
        transacao: Transacao,
        viewCriada: View
    ) {
        val cor : Int
        val icone : Int
        if (transacao.tipo == Tipo.RECEITA) {
            cor = ContextCompat.getColor(context, R.color.receita)
            icone = R.drawable.icone_transacao_item_receita
        }
        else{
            cor = ContextCompat.getColor(context, R.color.despesa)
            icone = R.drawable.icone_transacao_item_despesa
        }
        viewCriada.transacao_valor.setTextColor(cor) // Altera cor do valor
        viewCriada.transacao_icone.setBackgroundResource(icone) // Altera icone da Transação
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }
}