package fr.abdel.presentation
import fr.abdel.dao.AdresseService
import fr.abdel.dao.EleveService
import fr.abdel.dao.SalleService
import fr.abdel.metier.Adresse
import fr.abdel.metier.Eleve
import fr.abdel.metier.Salle
import java.sql.Date
import java.time.LocalDate

fun main(args: Array<String>) {

    val s1 = SalleService()
    val e1 = EleveService()
    val a1 = AdresseService()
    val adresse = Adresse(25,"rue grande", 62000, "arras", "france")
    val salle = Salle(1, "a888", "moumou")
    val eleve = Eleve("tt", "yy", LocalDate.of(1990,5,5),18,adresse)
    //a1.creer(adresse)
    //s1.creer(salle)
    e1.creer(eleve)
    //val eleve = Eleve(0,"petit", "jean", LocalDate.of(1999,5,5),20,)



}



