package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerFlatSquare
import dev.hagastua.action.badgetopath.testdata.TEST_SVG
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.quarkus.test.junit.main.Launch
import io.quarkus.test.junit.main.QuarkusMainTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

@QuarkusMainTest
@TestProfile(
    BadgeToPathActionFlatSquareStyleTest.BadgeToPathActionFlatSquareStyleTestProfile::class)
class BadgeToPathActionFlatSquareStyleTest {

  @Test
  @Launch()
  fun `Creating a badge with flat-square style should succeed`() {
    File(TEST_SVG).deleteOnExit()
    assertThat(File(TEST_SVG)).exists()
    assertThat(File(TEST_SVG).readText())
        .isEqualToIgnoringNewLines(File("docfiles/flat-square.svg").readText())
  }

  class BadgeToPathActionFlatSquareStyleTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return Collections.singleton(MockInputsInitializerFlatSquare::class.java)
    }
  }
}
