package dev.hagastua.action.badgetopath

import io.quarkiverse.githubaction.Inputs
import io.quarkiverse.githubaction.InputsInitializer
import io.quarkiverse.githubaction.testing.DefaultTestInputs
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.quarkus.test.junit.main.Launch
import io.quarkus.test.junit.main.LaunchResult
import io.quarkus.test.junit.main.QuarkusMainTest
import java.io.File
import java.util.*
import javax.enterprise.inject.Alternative
import javax.inject.Singleton
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@QuarkusMainTest
@TestProfile(BadgeToPathActionTest.BadgeToPathActionTestProfile::class)
class BadgeToPathActionTest {

  val fileName = "target/test.svg"

  @Test
  @Launch(value = [])
  fun testLaunchCommand(result: LaunchResult) {
    File(fileName).deleteOnExit()
    assertThat(File(fileName)).exists()
    assertThat(File(fileName).readText()).contains("fill=\"#007ec6\"")
    assertThat(File(fileName).readText()).contains("fill=\"#9f9f9f\"")
  }

  class BadgeToPathActionTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return Collections.singleton(MockInputsInitializer::class.java)
    }
  }
}

@Alternative
@Singleton
class MockInputsInitializer : InputsInitializer {
  override fun createInputs(): Inputs {
    return DefaultTestInputs(
        mapOf<String, String>(
            Pair("label", "test"), Pair("message", "134"), Pair("path", "target/test.svg")))
  }
}
