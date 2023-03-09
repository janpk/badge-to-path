package dev.hagastua.action.badgetopath

import io.quarkiverse.githubaction.Action
import io.quarkiverse.githubaction.Inputs
import org.silentsoft.badge4j.Badge
import java.io.File

open class BadgeToPathAction {
  @Action
  fun action(inputs: Inputs) {
    val badge = Badge.builder()
      .message(inputs.getRequired("message"))
                .label(inputs.getRequired("label"))
                .labelColor(inputs.get("labelColor").orElse("#007ec6"))
                .color(inputs.get("messageColor").orElse("#9f9f9f"))
                .build()
    File(inputs.getRequired("path")).writeText(badge)
  }
}
