package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockCommandsInitializer
import dev.hagastua.action.badgetopath.testdata.MockInputsInitializer
import dev.hagastua.action.badgetopath.testdata.TEST_SVG
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.quarkus.test.junit.main.Launch
import io.quarkus.test.junit.main.QuarkusMainTest
import java.io.File
import java.util.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@QuarkusMainTest
@TestProfile(BadgeToPathActionDefaultValuesTest.BadgeToPathActionTestProfile::class)
class BadgeToPathActionDefaultValuesTest {

  @Test
  @Launch()
  fun `Creating a badge with default values should succeed`() {
    File(TEST_SVG).deleteOnExit()
    assertThat(File(TEST_SVG)).exists()
    assertThat(File(TEST_SVG).readText()).contains("fill=\"#007ec6\"")
    assertThat(File(TEST_SVG).readText()).contains("fill=\"#9f9f9f\"")
  }

  class BadgeToPathActionTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return mutableSetOf(MockInputsInitializer::class.java, MockCommandsInitializer::class.java)
    }
  }
}
