package fr.abdel.connect

import fr.abdel.connect.MySQLConnUtils.getMySQLConnection
import java.sql.Connection
import java.sql.SQLException

object ConnectionUtils {
    // Using Oracle
    // You may be replaced by other Database.
    @get:Throws(SQLException::class, ClassNotFoundException::class)
    val myConnection: Connection
        get() =// Using Oracle
            // You may be replaced by other Database.
            MySQLConnUtils.getMySQLConnection("localHost", "gerer_ktecole", "root", "")

    //
    // Test Connection ...
    //
    @Throws(SQLException::class, ClassNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println("Get connection ... ")

        // Get a Connection object
        val conn: Connection = getMySQLConnection("localHost", "gerer_ktecole", "root", "")
        println("Get connection $conn")
        println("Done!")
    }


}