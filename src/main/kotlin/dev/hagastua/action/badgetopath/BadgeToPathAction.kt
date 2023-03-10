package dev.hagastua.action.badgetopath

import io.quarkiverse.githubaction.Action
import io.quarkiverse.githubaction.Inputs
import java.io.File
import org.silentsoft.badge4j.Badge
import org.silentsoft.badge4j.Style

open class BadgeToPathAction {
  @Action
  fun action(inputs: Inputs) {
    try {
      File(inputs.getRequired("path"))
          .writeText(
              Badge.builder()
                  .style(Style.nameOf(inputs.get("style").orElse("flat")))
                  .message(inputs.getRequired("message"))
                  .label(inputs.getRequired("label"))
                  .labelColor(inputs.get("labelColor").orElse("#007ec6"))
                  .color(inputs.get("messageColor").orElse("#9f9f9f"))
                  .build())
    } catch (e: IllegalStateException) {
      println("[ERROR] ${e.message}")
      throw e
    }
  }
}
