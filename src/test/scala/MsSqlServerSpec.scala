import java.sql.{DriverManager, ResultSet, SQLException, Statement}

import com.dimafeng.testcontainers.{ForAllTestContainer, JdbcDatabaseContainer, MSSQLServerContainer}
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import javax.sql.DataSource
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec

class MsSqlServerSpec extends AnyFlatSpec with ForAllTestContainer with BeforeAndAfterAll {

  override val container: MSSQLServerContainer = MSSQLServerContainer()

  override def beforeAll {
    container.container.withInitScript("init.sql")
  }

  override def afterStart(): Unit = {
    container.container.withInitScript("init.sql")

  }

  it should "count no of records in Region" in {

    val rs = performQuery(container, "SELECT count(*) from Sales.Region")
    val count = rs.getInt(1);

    assert(1 == count, "A basic SELECT query succeeds");

  }


  @throws[SQLException]
  protected def performQuery(container: MSSQLServerContainer, sql: String): ResultSet = {
    val ds = getDataSource(container)
    val statement = ds.getConnection.createStatement
    statement.execute(sql)
    val resultSet = statement.getResultSet
    resultSet.next
    resultSet
  }

  protected def getDataSource(container: MSSQLServerContainer): DataSource = {
    val hikariConfig = new HikariConfig
    hikariConfig.setJdbcUrl(container.jdbcUrl)
    hikariConfig.setUsername(container.username)
    hikariConfig.setPassword(container.password)
    hikariConfig.setDriverClassName(container.driverClassName)
    new HikariDataSource(hikariConfig)
  }
}
