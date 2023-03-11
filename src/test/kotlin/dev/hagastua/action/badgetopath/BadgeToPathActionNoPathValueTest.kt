package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockCommandsInitializer
import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerNoPathValue
import dev.hagastua.action.badgetopath.testdata.TEST_SVG
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.quarkus.test.junit.main.Launch
import io.quarkus.test.junit.main.LaunchResult
import io.quarkus.test.junit.main.QuarkusMainTest
import org.assertj.core.api.Assertions
import java.util.*
import org.junit.jupiter.api.Test
import java.io.File

@QuarkusMainTest
@TestProfile(BadgeToPathActionNoPathValueTest.BadgeToPathActionNoPathValueTestProfile::class)
class BadgeToPathActionNoPathValueTest {

  @Test
  @Launch(exitCode = 0)
  fun `Creating a badge with no path value should succeed`(result: LaunchResult) {
    // Nothing to test here yet
  }

  class BadgeToPathActionNoPathValueTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return mutableSetOf(
          MockInputsInitializerNoPathValue::class.java, MockCommandsInitializer::class.java)
    }
  }
}
