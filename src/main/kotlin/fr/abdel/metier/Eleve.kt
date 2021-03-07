package fr.abdel.metier
import java.sql.Date
import java.time.LocalDate


class Eleve{

    var id = 0
    var nom: String? = null
    var prenom: String? = null
    var dateNaissance: LocalDate? = null
    var age = 0
    var adresse: Adresse? = null

    constructor()  {

    }

    constructor(id: Int, nom: String?, prenom: String?, dateNaissance: LocalDate?, age: Int, adresse: Adresse?) {
        this.id = id
        this.nom = nom
        this.prenom = prenom
        this.dateNaissance = dateNaissance
        this.adresse = adresse
    }

    constructor(nom: String?, prenom: String?, dateNaissance: LocalDate?, age: Int, adresse: Adresse?) {
        this.nom = nom
        this.prenom = prenom
        this.dateNaissance = dateNaissance
        this.age = age
        this.adresse = adresse
    }

    override fun toString(): String {
        return "Eleve(id=$id, nom='$nom', prenom='$prenom', dateNaissance=$dateNaissance, age=$age, Adresse='$adresse')"
    }


}