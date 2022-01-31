import mill._, scalalib._

object dataprocessing extends ScalaModule {

  val a = 2
  val ZioVersion        = "2.0.0-RC1"
  val ZioJsonVersion    = "0.3.0-RC2"
  val ZioHttpVersion    = "2.0.0-RC2"
  val ZioConfigVersion  = "3.0.0-RC1"
  val ZioSchemaVersion  = "0.2.0-RC1-1"
  val ZioLoggingVersion = "2.0.0-RC4"
  val ZioZmxVersion     = "2.0.0-M1"
  
  // def scalaVersion = "3.1.0"
  // def scalaVersion = "2.13.8"
  def scalaVersion = "2.12.15"

  def ivyDeps = Agg(
    ivy"dev.zio::zio::${ZioVersion}",
    // ivy"io.d11::zhttp:${ZioHttpVersion}",
    ivy"dev.zio::zio-json:${ZioJsonVersion}",
    ivy"dev.zio::zio-schema:${ZioSchemaVersion}",
    ivy"dev.zio::zio-cli:0.2.0",
    ivy"io.getquill::quill-spark:3.16.0",
    ivy"org.apache.spark::spark-sql:2.4.4",

  )

  override def mainClass = T { Some("Cli") }

  val b = ""
    object test extends Tests {
        def ivyDeps = Agg(
            ivy"org.scalactic::scalactic:3.1.1",
            ivy"org.scalatest::scalatest:3.1.1",
            ivy"dev.zio::zio-test:2.0.0-RC1",
            ivy"dev.zio::zio-test-junit:2.0.0-RC1",
            ivy"com.novocode:junit-interface:0.11",
        )

        def testFramework = "com.novocode.junit.JUnitFramework"
        
        // def testFramework = Seq("org.scalatest.tools.Framework")
    }
}
