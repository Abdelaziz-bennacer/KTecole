package fr.abdel.dao

import fr.abdel.metier.Eleve
import fr.abdel.metier.Salle
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface Idao <T>{

    fun creer(item: T): Boolean
    fun delete(item: T): Boolean
    fun update(item: T): Boolean
    fun findById(id:Int): T?
    fun <T>findAll(): MutableList<T>
    //fun findById(item: Int): T
}