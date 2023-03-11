package dev.hagastua.action.badgetopath

import io.quarkiverse.githubaction.Action
import io.quarkiverse.githubaction.Commands
import io.quarkiverse.githubaction.Inputs
import java.io.File
import org.silentsoft.badge4j.Badge
import org.silentsoft.badge4j.Style

open class BadgeToPathAction {

  @Action
  fun action(inputs: Inputs, commands: Commands) {
    try {
      val link: String? = if (inputs.get("link").isPresent) inputs.get("link").get() else null
      val logo: String? = if (inputs.get("logo").isPresent) inputs.get("logo").get() else null
      val logoWidth: Int =
          if (inputs.get("logoWidth").isPresent) inputs.getInteger("logoWidth").asInt else 0
      val badgesrc =
          Badge.builder()
              .style(Style.nameOf(inputs.get("style").orElse("flat")))
              .message(inputs.getRequired("message"))
              .label(inputs.getRequired("label"))
              .labelColor(inputs.get("labelColor").orElse("#007ec6"))
              .color(inputs.get("messageColor").orElse("#9f9f9f"))
              .links(link?.split(",")?.toTypedArray())
              .logo(logo)
              .logoWidth(logoWidth)
              .build()
      if (inputs.get("path").isPresent) {
        File(inputs.get("path").get()).writeText(badgesrc)
      }
      commands.setOutput("badge-src", badgesrc)
    } catch (e: IllegalStateException) {
      println("[ERROR] ${e.message}")
      throw e
    }
  }
}
