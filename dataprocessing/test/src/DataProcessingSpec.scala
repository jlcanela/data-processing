package dataprocessing

import zio.Console._
import zio._
import zio.schema._

import zio.test.Assertion._
import zio.test.TestAspect._
import zio.test._

import zio.test.junit.JUnitRunnableSpec
import zio.test.junit.ZTestJUnitRunner
import org.junit.runner.RunWith
import java.nio.file.Paths

case class Person(firstname: String, lastname: String, age: Int)
object Person {
  implicit val schema = DeriveSchema.gen[Person]

}

@RunWith(classOf[ZTestJUnitRunner])
class DataProcessingSpec extends JUnitRunnableSpec {

  def spec: ZSpec[TestEnvironment, Any] =
    (suite("DataProcessingSpec")(
      analyzeSourceSuite,
    ) @@ timeout(90.seconds)) //.provide(ParquetAnalyzer.layer, Spark.layer)

  private val analyzeSourceSuite = suite("analyze source")(
      test("parquet file") {
        val parquetFile = Paths.get("dataprocessing/test/resources/sample.parquet")
        
        def ast = for {
            _ <- ZIO.logInfo(s"path=${parquetFile.toFile().getAbsolutePath()}")
            schema <- ParquetAnalyzer.readParquet(parquetFile)
            ast <- schema.tap(Console.printLine(_)).map(Schema.record(_:_*).ast) 
        } yield ast
        assertM(ast.provide(ParquetAnalyzer.layer, Spark.layer, Console.live))(equalTo(Person.schema.ast))
      },
      test("csv file") {
        assert(true)(equalTo(true))
      },
  )
}
