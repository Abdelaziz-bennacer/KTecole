package fr.abdel.dao
import fr.abdel.connect.ConnectionUtils.myConnection
import fr.abdel.connect.MySQLConnUtils.getMySQLConnection
import fr.abdel.metier.Adresse
import fr.abdel.metier.Eleve
import java.sql.*
import java.time.LocalDate

var listeEleve = mutableMapOf<Eleve, Adresse>()
var listeEleve2 = mutableListOf<Eleve>()
lateinit var indice: Integer

class EleveService: Idao<Eleve> {

    override fun creer(item: Eleve): Boolean {
        try {
            val sql = ("Insert into ELEVE (NOM, PRENOM, DATENAISSANCE,AGE, ID_ADRESSE) "
                    + "Values (?,?,?,?,?)")
            // Get Connection
            val connection = myConnection
            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)
            // Set value for the first parameter (First '?')
            pstm.setString(1, item.nom)
            pstm.setString(2, item.prenom)
            pstm.setDate(3,convertToDateViaSqlDate(item.dateNaissance))
            pstm.setInt(4, item.age)
            pstm.setInt(5, item.adresse!!.idAdresse)
            println("Create OK " + pstm.executeUpdate())
        } catch (e1: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
            return false
        } catch (e1: SQLException) {
            e1.printStackTrace()
            return false
        }
        return true
    }

    private fun convertToDateViaSqlDate(dateToConvert: LocalDate?): Date? {
        return Date.valueOf(dateToConvert)
    }

    override fun delete(eleve: Eleve): Boolean {
        try {
            val sql = "DELETE FROM Eleve where  ELEVE_ID= ?"
            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            println(eleve)
            pstm.setInt(1, eleve.id)
            println("Delete OK " + pstm.executeUpdate())
        } catch (e1: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
            return false
        } catch (e1: SQLException) {
            e1.printStackTrace()
            return false
        }
        return true
    }

    override fun update(eleve: Eleve): Boolean {
        try {
            val sql = "UPDATE ELEVE SET NOM=? ,PRENOM=?,DATE_NAISSANCE=?,AGE=?,ADRESSE=?  where ELEVE_ID=?"
            // Get Connection
            val connection = myConnection
            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)
            // Set value for the first parameter (First '?')
            pstm.setString(1, eleve.nom)
            pstm.setString(2, eleve.prenom)
            pstm.setDate(3, convertToDateViaSqlDate(eleve.dateNaissance))
            pstm.setInt(4, eleve.age)
            pstm.setInt(5, eleve.adresse!!.idAdresse)
            pstm.setInt(6, eleve.id)
            println("Update OK " + pstm.executeUpdate())
        } catch (e1: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
            return false
        } catch (e1: SQLException) {
            e1.printStackTrace()
            return false
        }
        return true
    }

    override fun findById(id: Int): Eleve? {
        var result = Eleve()
        try {
            val sql = "select  ELEVE_ID ,NOM, PRENOM,DATE_NAISSANCE,AGE,ADRESSE from eleve  where ELEVE_ID = ? "
            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            pstm.setInt(1, id)
            val rs = pstm.executeQuery()
            while (rs.next()) {
                val idEleve = rs.getInt(1)
                val nom = rs.getString(2)
                val prenom = rs.getString(3)
                val dateNaissance = rs.getDate(4).toLocalDate()
                val age = rs.getInt(5)
                val IdAdresse = rs.getInt(6)
                val adresseService = AdresseService()
                // chercher 1 adresse avec son id
                val adresse: Adresse? = adresseService.findById(IdAdresse)
                result = adresse?.let { Eleve(id, nom, prenom, dateNaissance, age, it) }!!
            }
            println("Update OK " + rs.row)


        } catch (e1: SQLException) {
            e1.printStackTrace()
            return null
        }
        return result
    }



    override fun <Eleve> findAll(): MutableList<Eleve> {
        val result: MutableList<Eleve> = mutableListOf<Eleve>()
        // Get Connection
        // Get Connection
        val connection: Connection
        try {
            connection = myConnection
            val statement = connection.createStatement()
            val sql = "Select ELEVE_ID, NOM, PRENOM,DATE_NAISSANCE, AGE, ID_ADRESSE from eleve"
            // Execute SQL statement returns a ResultSet object.
            val rs = statement.executeQuery(sql)
            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                val myAdresseService = AdresseService()
                val adresse: Adresse? = myAdresseService.findById(rs.getInt(6))
                val a = adresse?.let {
                    Eleve(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getInt(5),
                        it
                    )
                }
                if (a != null) {
                    result.add(a)
                }
            }

            // Close connection.
            connection.close()
        } catch (e: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: SQLException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return result
    }
    }

private fun <E> MutableList<E>.add(eleve: Eleve) {
    mutableListOf<Eleve>().add(eleve)
}




