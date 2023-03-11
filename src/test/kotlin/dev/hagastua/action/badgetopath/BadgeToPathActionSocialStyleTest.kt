package dev.hagastua.action.badgetopath

import dev.hagastua.action.badgetopath.testdata.MockInputsInitializerSocial
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
@TestProfile(BadgeToPathActionSocialStyleTest.BadgeToPathActionSocialStyleTestProfile::class)
class BadgeToPathActionSocialStyleTest {

  @Test
  @Launch()
  fun `Creating a badge with social style should succeed`() {
    File(TEST_SVG).deleteOnExit()
    assertThat(File(TEST_SVG)).exists()
  }

  class BadgeToPathActionSocialStyleTestProfile : QuarkusTestProfile {
    override fun getEnabledAlternatives(): MutableSet<Class<*>> {
      return Collections.singleton(MockInputsInitializerSocial::class.java)
    }
  }
}
