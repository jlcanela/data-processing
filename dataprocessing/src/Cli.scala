package dataprocessing

import zio._
import zio.logging._
import zio.logging.backend.SLF4J
object Cli extends ZIOAppDefault {

  import LogFormat._
    val slf4j = SLF4J.slf4j(LogLevel.Info, colored, _ => "org.apache")
    //val slf4j = SLF4J.slf4j(LogLevel.Info)
  override def hook: RuntimeConfigAspect = slf4j >>> RuntimeConfigAspect.enableCurrentFiber

    lazy val myLogFormat: LogFormat = LogFormat.colored
    def run = for {
        ///runtimeConfig <- ZIO.runtimeConfig
        _ <- ZIO.logInfo("start processing ...") //.withRuntimeConfig(zio.logging.console(myLogFormat)(runtimeConfig))
    } yield ()
}
