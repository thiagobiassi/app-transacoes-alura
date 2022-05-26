package br.com.alura.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataParaBrasileiro(): String {
    val formatoMoeda = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatoMoeda.format(this)
}