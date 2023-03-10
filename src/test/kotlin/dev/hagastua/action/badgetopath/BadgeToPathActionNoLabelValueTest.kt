package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerNoLabelValue
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.quarkus.test.junit.main.Launch
import io.quarkus.test.junit.main.LaunchResult
import io.quarkus.test.junit.main.QuarkusMainTest
import java.util.*
import org.junit.jupiter.api.Test

@QuarkusMainTest
@TestProfile(BadgeToPathActionNoLabelValueTest.BadgeToPathActionNoLabelValueTestProfile::class)
class BadgeToPathActionNoLabelValueTest {

  @Test
  @Launch(exitCode = 1)
  fun `Creating a badge with no label value should fail with exitcode = 1`(result: LaunchResult) {
    println(result.exitCode())
  }

  class BadgeToPathActionNoLabelValueTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return Collections.singleton(MockInputsInitializerNoLabelValue::class.java)
    }
  }
}
