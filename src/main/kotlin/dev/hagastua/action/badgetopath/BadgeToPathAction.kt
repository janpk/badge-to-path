package dev.hagastua.action.badgetopath

import io.github.dsibilio.badgemaker.core.BadgeFormatBuilder
import io.github.dsibilio.badgemaker.core.BadgeMaker
import io.github.dsibilio.badgemaker.model.NamedColor
import io.quarkiverse.githubaction.Action
import io.quarkiverse.githubaction.Inputs
import java.io.File

open class BadgeToPathAction {
  @Action
  fun action(inputs: Inputs) {

    val label = inputs.getRequired("label")
    val status = inputs.getRequired("status")
    val path = inputs.getRequired("path")

    val badge =
        BadgeMaker.makeBadge(
            BadgeFormatBuilder(status)
                .withLabel(label)
                .withLabelColor(NamedColor.BLUE)
                .withMessageColor(NamedColor.GREY)
                .build())
    File(path).writeText(badge)
  }
}
