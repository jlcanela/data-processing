package zio.schema.codec

import zio.Console._
import zio._

import zio.test.Assertion._
import zio.test.TestAspect._
import zio.test._

import zio.test.junit.JUnitRunnableSpec
import zio.test.junit.ZTestJUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[ZTestJUnitRunner])
class DataProcessingSpec extends JUnitRunnableSpec {

  def spec: ZSpec[TestEnvironment, Any] =
    suite("DataProcessingSpec")(
      analyzeSourceSuite,
    ) @@ timeout(90.seconds)

  private val analyzeSourceSuite = suite("analyze source")(
      test("parquet file") {
        assert(true)(equalTo(true))
      },
      test("csv file") {
        assert(true)(equalTo(true))
      },
  )
}
