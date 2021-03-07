package fr.abdel.dao
import fr.abdel.connect.ConnectionUtils.myConnection
import fr.abdel.metier.Salle
import java.sql.Connection
import java.sql.SQLException


class SalleService: Idao<Salle> {

    override fun creer(item: Salle): Boolean {
        try {
            val sql = ("Insert into Salle (CODE, LIBELLE)"
                    + "Values (?,?)")
            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            pstm.setString(1, item.code)
            pstm.setString(2, item.libelle)
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

    override fun delete(salle: Salle): Boolean {
        try {
            val sql = "DELETE FROM salle where  SALLE_ID= ?"
            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            println(salle)
            pstm.setInt(1, salle.id)
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

    override fun update(salle: Salle): Boolean {
        try {
            val sql = "UPDATE SALLE SET CODE=? ,LIBELLE=?  where SALLE_ID=?"
            // Get Connection
            val connection = myConnection
            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)
            // Set value for the first parameter (First '?')
            pstm.setString(1, salle.code)
            pstm.setString(2, salle.libelle)
            pstm.setInt(3, salle.id)
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

    override fun findById(id: Int): Salle? {
        var result = Salle()
        try {
            val sql = "select  SALLE_ID ,CODE, LIBELLE from salle  where SALLE_ID = ? "
            // Get Connection
            val connection = myConnection

            // Create a PreparedStatement object.
            val pstm = connection.prepareStatement(sql)

            // Set value for the first parameter (First '?')
            pstm.setInt(1, id)
            val rs = pstm.executeQuery()
            while (rs.next()) {
                val idSalle = rs.getInt(1)
                val code = rs.getString(2)
                val libelle = rs.getString(3)
                result = Salle(idSalle, code, libelle)
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

    override fun <Salle> findAll(): MutableList<Salle> {
        val result: MutableList<Salle> = mutableListOf<Salle>()
        // Get Connection
        val connection: Connection
        try {
            connection = myConnection
            val statement = connection.createStatement()
            val sql = "Select SALLE_ID, CODE, LIBELLE from SALLE"
            // Execute SQL statement returns a ResultSet object.
            val rs = statement.executeQuery(sql)
            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                val a = Salle(rs.getInt(1), rs.getString(2), rs.getString(3))
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

private fun <E> MutableList<E>.add(salle: Salle) {
    mutableListOf<Salle>().add(salle)

}


