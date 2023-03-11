package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockCommandsInitializer
import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerNoMessageValue
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.quarkus.test.junit.main.Launch
import io.quarkus.test.junit.main.LaunchResult
import io.quarkus.test.junit.main.QuarkusMainTest
import java.util.*
import org.junit.jupiter.api.Test

@QuarkusMainTest
@TestProfile(BadgeToPathActionNoMessageValueTest.BadgeToPathActionNoMessageValueTestProfile::class)
class BadgeToPathActionNoMessageValueTest {

  @Test
  @Launch(exitCode = 1)
  fun `Creating a badge with no message value should fail with exitcode = 1`(result: LaunchResult) {
    println(result.exitCode())
  }

  class BadgeToPathActionNoMessageValueTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return mutableSetOf(
          MockInputsInitializerNoMessageValue::class.java, MockCommandsInitializer::class.java)
    }
  }
}
