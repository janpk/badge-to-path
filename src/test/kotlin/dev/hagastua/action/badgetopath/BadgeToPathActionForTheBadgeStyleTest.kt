package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerForTheBadge
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
@TestProfile(
    BadgeToPathActionForTheBadgeStyleTest.BadgeToPathActionForTheBadgeStyleTestProfile::class)
class BadgeToPathActionForTheBadgeStyleTest {

  @Test
  @Launch()
  fun `Creating a badge with for-the-badge style should succeed`() {
    File(TEST_SVG).deleteOnExit()
    assertThat(File(TEST_SVG)).exists()
    assertThat(File(TEST_SVG).readText())
        .isEqualToIgnoringNewLines(File("docfiles/forthebadge.svg").readText())
  }

  class BadgeToPathActionForTheBadgeStyleTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return Collections.singleton(MockInputsInitializerForTheBadge::class.java)
    }
  }
}