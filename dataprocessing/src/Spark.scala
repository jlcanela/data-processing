package dataprocessing

import zio._
import java.nio.file.Path
import org.apache.spark.sql.SparkSession

trait Spark {

    def session: SparkSession

}

object Spark {

  def newSession =
      ZIO.attempt(SparkSession.builder.appName("SparkApplication").master("local[*]").getOrCreate()) 

  def layer: ZLayer[Any, Throwable, Spark] = newSession
      .map(SparkLive(_)).toLayer

  def readParquet(path: Path) = ZIO.serviceWith[ParquetAnalyzer](_.readParquet(path))
}

case class SparkLive(session: SparkSession) extends Spark {
    
}