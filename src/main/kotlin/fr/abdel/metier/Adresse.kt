package fr.abdel.metier

class Adresse{

    var idAdresse = 0
    var numRue = 0
    var nomRue: String? = null
    var codePostale = 0
    var ville: String? = null
    var pays: String? = null

    constructor() {}
    constructor(idAdresse: Int, numRue: Int, nomRue: String?, codePostale: Int, ville: String?, pays: String?) {
        this.idAdresse = idAdresse
        this.numRue = numRue
        this.nomRue = nomRue
        this.codePostale = codePostale
        this.ville = ville
        this.pays = pays
    }

    constructor(numRue: Int, nomRue: String?, codePostale: Int, ville: String?, pays: String?) {
        this.numRue = numRue
        this.nomRue = nomRue
        this.codePostale = codePostale
        this.ville = ville
        this.pays = pays
    }

    override fun toString(): String {
        return "Adresse(id= $idAdresse, numRue=$numRue, nomRue='$nomRue', codePostal=$codePostale, ville='$ville', pays='$pays')"}

}


