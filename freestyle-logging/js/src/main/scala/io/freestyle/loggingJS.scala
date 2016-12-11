package io.freestyle

import cats.MonadError
import io.freestyle.logging._
import slogging.WinstonLoggerFactory

object loggingJS {

  object implicits {
    implicit def freeStyleLoggingInterpreter[M[_]](implicit ME: MonadError[M, Throwable]): LoggingM.Interpreter[M] =
      new LoggingM.Interpreter[M] {

        val logger = WinstonLoggerFactory().getUnderlyingLogger("freestyle-logging")

        val source = ""

        def debugImpl(msg: String): M[Unit] = ME.catchNonFatal(logger.debug(source, msg))

        def debugWithCauseImpl(msg: String, cause: Throwable): M[Unit] = ME.catchNonFatal(logger.debug(source, msg, cause))

        def errorImpl(msg: String): M[Unit] = ME.catchNonFatal(logger.error(source, msg))

        def errorWithCauseImpl(msg: String, cause: Throwable): M[Unit] = ME.catchNonFatal(logger.error(source, msg, cause))

        def infoImpl(msg: String): M[Unit] = ME.catchNonFatal(logger.info(source, msg))

        def infoWithCauseImpl(msg: String, cause: Throwable): M[Unit] = ME.catchNonFatal(logger.info(source, msg, cause))

        def warnImpl(msg: String): M[Unit] = ME.catchNonFatal(logger.warn(source, msg))

        def warnWithCauseImpl(msg: String, cause: Throwable): M[Unit] = ME.catchNonFatal(logger.warn(source, msg, cause))
      }
  }
}
