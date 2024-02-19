@file:DependsOn("xyz.pavelkorolev.danger.detekt:plugin:1.2.0")

import systems.danger.kotlin.*
import systems.danger.kotlin.models.github.*
import xyz.pavelkorolev.danger.detekt.DetektPlugin
import java.io.File

register.plugin(DetektPlugin)

danger(args) {
    warnDetekt()
}

fun warnDetekt() {
    val file = File("app/build/reports/detekt/detekt.xml") // or report.sarif
    if (!file.exists()) {
        warn(
            "🙈 No detekt report found",
        )
        return
    }
    with(DetektPlugin) {
        val report = parse(file)
        val count = report.count
        if (count == 0) {
            message("👏👏👏 Good job! Detekt found no violations here!")
            return
        }
        message(
            "🙁 Detekt violations found: **${report.count}**.\n"
        )
        report(report)
    }
}