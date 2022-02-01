package dataprocessing

import zio._
import zio.schema._
import zio.schema.codec._
import java.nio.file.Path
import org.apache.spark.sql.types._

trait ParquetAnalyzer {

    def readParquet(path: Path): ZIO[Any, Throwable, Chunk[Schema.Field[_]]]
}

object ParquetAnalyzer {

  val layer: ZLayer[Spark, Nothing, ParquetAnalyzer] = 
    (ParquetAnalyzerLive(_)).toLayer[ParquetAnalyzer]

  def readParquet(path: Path) = ZIO.serviceWith[ParquetAnalyzer](_.readParquet(path))
}

case class ParquetAnalyzerLive(spark: Spark) extends ParquetAnalyzer {
    
    def df(path: Path) = ZIO.attempt(spark.session.read.parquet(path.toFile.getAbsolutePath()))
    
    def dataTypeToSchema(sf: StructField) = sf match {
          case StructField(name, DataTypes.StringType, nullable, metadata) => Right(Schema.Field(name, Schema[String]))
          case StructField(name, DataTypes.IntegerType, nullable, metadata) => Right(Schema.Field(name, Schema[Int]))
          case StructField(name, dataType, nullable, metadata) => Left(s"dataType $dataType not recognized")
      }

    def readParquet(path: Path): ZIO[Any, Throwable, Chunk[Schema.Field[_]]] = for {
      parquet <- df(path)
      (invalidschema, validschema) = parquet.schema.fields.map(dataTypeToSchema(_)).partition(_.isLeft)
      _ <- ZIO.logError(invalidschema.toList.toString())
    } yield Chunk(validschema.flatMap(_.toOption):_*)
    
}