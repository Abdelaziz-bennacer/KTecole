package fr.abdel.connect

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object MySQLConnUtils {
    // Connect to MySQL
    @get:Throws(SQLException::class, ClassNotFoundException::class)
    val mySQLConnection: Connection
        get() {
            val hostName = "localhost"
            val dbName = "gerer_ktecole"
            val userName = "root"
            val password = ""
            return getMySQLConnection(hostName, dbName, userName, password)
        }

    @Throws(SQLException::class, ClassNotFoundException::class)
    fun getMySQLConnection(
        hostName: String, dbName: String,
        userName: String?, password: String?
    ): Connection {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("com.mysql.cj.jdbc.Driver")
        val connectionURL = "jdbc:mysql://$hostName:3306/$dbName"
        return DriverManager.getConnection(
            connectionURL, userName,
            password)


    }


}
