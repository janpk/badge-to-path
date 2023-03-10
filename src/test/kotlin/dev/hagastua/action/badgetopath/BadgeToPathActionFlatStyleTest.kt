package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerFlat
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
@TestProfile(BadgeToPathActionFlatStyleTest.BadgeToPathActionFlatStyleTestProfile::class)
class BadgeToPathActionFlatStyleTest {

  @Test
  @Launch()
  fun `Creating a badge with flat style should succeed`() {
    File(TEST_SVG).deleteOnExit()
    assertThat(File(TEST_SVG)).exists()
    assertThat(File(TEST_SVG).readText())
        .isEqualToIgnoringNewLines(File("docfiles/flat.svg").readText())
  }

  class BadgeToPathActionFlatStyleTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return Collections.singleton(MockInputsInitializerFlat::class.java)
    }
  }
}
