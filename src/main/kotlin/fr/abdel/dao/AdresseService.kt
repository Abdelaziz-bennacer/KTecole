package fr.abdel.dao

import fr.abdel.connect.ConnectionUtils.myConnection
import fr.abdel.metier.Adresse
import java.sql.Connection
import java.sql.SQLException

class AdresseService: Idao<Adresse> {
    override fun creer(item: Adresse): Boolean {
        try {
            val sql = ("Insert Into Adresse (NUM_RUE, NOM_RUE, CODE_POSTALE,VILLE,PAYS )" + "Values (?,?,?,?,?)")

            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            pstm.setInt(1, item.numRue)
            pstm.setString(2, item.nomRue)
            pstm.setInt(3, item.codePostale)
            pstm.setString(4, item.ville)
            pstm.setString(5, item.pays)
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

    override fun delete(adresse: Adresse): Boolean {
        try {
            val sql = "DELETE FROM adresse where ID_ADRESSE= ?"
            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            pstm.setObject(1, adresse)
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

    override fun update(adresse: Adresse): Boolean {
        try {
            val sql = "UPDATE ADRESSE SET NUM_RUE=? ,NOM_RUE=? ,CODE_POSTAL=? , VILLE= ?, PAYS = ?   where ID_ADRESSE=?"
            // Get Connection
            val connection = myConnection
            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)
            // Set value for the first parameter (First '?')
            pstm.setInt(1, adresse.numRue)
            pstm.setString(2, adresse.nomRue)
            pstm.setInt(3, adresse.codePostale)
            pstm.setString(4, adresse.ville)
            pstm.setString(5, adresse.pays)
            pstm.setInt(6, adresse.idAdresse)
            println("Update OK " + pstm.executeUpdate())

        } catch (e1: SQLException) {
            e1.printStackTrace()
            return false
        }
        return true
    }

    override fun findById(id: Int): Adresse? {
        var result = Adresse()
        try {
            val sql = "select ID_ADRESSE, NUM_RUE, NOM_RUE, CODE_POSTALE,VILLE,PAYS from adresse where ID_ADRESSE = ? "
            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            pstm.setInt(1, id)
            val rs = pstm.executeQuery()
            while (rs.next()) {
                val idadresse = rs.getInt(1)
                val numRue = rs.getInt(2)
                val nomRue = rs.getString(3)
                val codePostale = rs.getInt(4)
                val ville = rs.getString(5)
                val pays = rs.getString("PAYS")
                var result = Adresse(idadresse,numRue, nomRue, codePostale, ville, pays)
            }
            println("Update OK " + rs.row)
        } catch (e1: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
            return null
        } catch (e1: SQLException) {
            e1.printStackTrace()
            return null
        }
        return result

    }

    override fun <Adresse> findAll(): MutableList<Adresse> {
        val result: MutableList<Adresse> = mutableListOf<Adresse>()
        // Get Connection
        // Get Connection
        val connection: Connection
        try {
            connection = myConnection
            // Create statement
            val statement = connection.createStatement()
            val sql = "Select ID_ADRESSE, NUM_RUE, NOM_RUE,CODE_POSTALE, VILLE , PAYS from ADRESSE"
            // Execute SQL statement returns a ResultSet object.
            val rs = statement.executeQuery(sql)
            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                val a = Adresse(
                    rs.getInt(1), rs.getInt(2), rs.getString(3),
                    rs.getInt(4), rs.getString(5), rs.getString(6)
                )
                result.add(a)
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

private fun <E> MutableList<E>.add(adresse: Adresse) {
    mutableListOf<Adresse>().add(adresse)
}


