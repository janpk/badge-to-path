package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerPlastic
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
@TestProfile(BadgeToPathActionPlasticStyleTest.BadgeToPathActionPlasticStyleTestProfile::class)
class BadgeToPathActionPlasticStyleTest {

  @Test
  @Launch()
  fun `Creating a badge with plastic style should succeed`() {
    File(TEST_SVG).deleteOnExit()
    assertThat(File(TEST_SVG)).exists()
    assertThat(File(TEST_SVG).readText())
        .isEqualToIgnoringNewLines(File("docfiles/plastic.svg").readText())
  }

  class BadgeToPathActionPlasticStyleTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return Collections.singleton(MockInputsInitializerPlastic::class.java)
    }
  }
}
