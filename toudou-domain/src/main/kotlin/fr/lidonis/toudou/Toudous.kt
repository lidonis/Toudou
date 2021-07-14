package fr.lidonis.toudou

import java.util.*

class Toudous {

    private val _toudous = mutableListOf<Toudou>()
    val all get(): List<Toudou> = _toudous

    fun add(label: Label): Toudou {
        val toudou = Toudou(ToudouId(), label)
        _toudous.add(toudou)
        return toudou
    }
}

data class Toudou(
    val toudouId: ToudouId,
    val label: Label
)

@JvmInline
value class ToudouId(val id: UUID = UUID.randomUUID())

@JvmInline
value class Label(val value: String)