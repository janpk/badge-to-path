package dev.hagastua.action.badgetopath.testdata

import io.quarkiverse.githubaction.Commands
import io.quarkiverse.githubaction.CommandsInitializer
import io.quarkiverse.githubaction.Inputs
import io.quarkiverse.githubaction.InputsInitializer
import io.quarkiverse.githubaction.runtime.CommandsImpl
import io.quarkiverse.githubaction.runtime.github.EnvFiles
import io.quarkiverse.githubaction.testing.DefaultTestInputs
import java.io.IOException
import java.io.UncheckedIOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.Map
import javax.enterprise.inject.Alternative
import javax.inject.Singleton

const val TEST_SVG = "target/test.svg"

@Alternative
@Singleton
class MockInputsInitializer : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("label", "hello"), Pair("message", "world"), Pair("path", TEST_SVG)))
  }
}

@Alternative
@Singleton
class MockInputsInitializerLinks : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("label", "hello"), Pair("message", "world"), Pair("path", TEST_SVG), Pair("link", "https://link1")))
  }
}

@Alternative
@Singleton
class MockInputsInitializerNoMessageValue : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(mapOf<String, String>(Pair("label", "hello"), Pair("path", TEST_SVG)))
  }
}

@Alternative
@Singleton
class MockInputsInitializerNoLabelValue : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(Pair("message", "world"), Pair("path", TEST_SVG)))
  }
}

@Alternative
@Singleton
class MockInputsInitializerNoPathValue : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(Pair("label", "hello"), Pair("message", "world")))
  }
}

@Alternative
@Singleton
class MockInputsInitializerNoValues : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(mapOf<String, String>())
  }
}

@Alternative
@Singleton
class MockInputsInitializerFlatSquare : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("style", "flat-square"),
            Pair("label", "hello"),
            Pair("message", "world"),
            Pair("path", TEST_SVG)))
  }
}

@Singleton
@Alternative
class MockInputsInitializerFlat : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("style", "flat"),
            Pair("label", "hello"),
            Pair("message", "world"),
            Pair("path", TEST_SVG)))
  }
}

@Singleton
@Alternative
class MockInputsInitializerForTheBadge : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("style", "for-the-badge"),
            Pair("label", "hello"),
            Pair("message", "world"),
            Pair("path", TEST_SVG)))
  }
}

@Singleton
@Alternative
class MockInputsInitializerPlastic : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("style", "plastic"),
            Pair("label", "hello"),
            Pair("message", "world"),
            Pair("path", TEST_SVG)))
  }
}

@Singleton
@Alternative
class MockInputsInitializerSocial : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("style", "social"),
            Pair("label", "hello"),
            Pair("message", "world"),
            Pair("path", TEST_SVG)))
  }
}

@Alternative
@Singleton
class MockCommandsInitializer : CommandsInitializer {
  override fun createCommands(): Commands {
    return try {
      val githubOutputPath: Path =
        Path.of(System.getProperty("java.io.tmpdir") + "/temp-github-output.txt")
      Files.deleteIfExists(githubOutputPath)
      CommandsImpl(Map.of(EnvFiles.GITHUB_OUTPUT, githubOutputPath.toString()))
    } catch (e: IOException) {
      throw UncheckedIOException(e)
    }
  }
}
