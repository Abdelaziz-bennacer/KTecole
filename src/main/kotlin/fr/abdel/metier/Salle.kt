package fr.abdel.metier


data class Salle(var id:Int, var code: String, var libelle:String){

    @JvmOverloads constructor(): this(id = 2, code ="e111", libelle= "prof")


   //constructor(id:Int, code: String, libelle:String):this()


    override fun toString(): String {
        return "Salle(id: '$id' code='$code', libelle='$libelle')"
    }
}






