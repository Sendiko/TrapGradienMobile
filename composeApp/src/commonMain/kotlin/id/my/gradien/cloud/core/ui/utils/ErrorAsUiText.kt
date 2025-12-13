package id.my.gradien.cloud.core.ui.utils

import id.my.gradien.cloud.core.network.utils.DataError
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.bad_request
import trapgradienmobile.composeapp.generated.resources.error_disk_full
import trapgradienmobile.composeapp.generated.resources.error_serialization
import trapgradienmobile.composeapp.generated.resources.no_internet
import trapgradienmobile.composeapp.generated.resources.not_found
import trapgradienmobile.composeapp.generated.resources.server_error
import trapgradienmobile.composeapp.generated.resources.the_request_timed_out
import trapgradienmobile.composeapp.generated.resources.unauthorized
import trapgradienmobile.composeapp.generated.resources.unknown_error
import trapgradienmobile.composeapp.generated.resources.youve_hit_your_rate_limit

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Remote.REQUEST_TIMEOUT -> UiText.CmpStringResource(
            Res.string.the_request_timed_out
        )

        DataError.Remote.TOO_MANY_REQUESTS -> UiText.CmpStringResource(
            Res.string.youve_hit_your_rate_limit
        )

        DataError.Remote.NO_INTERNET -> UiText.CmpStringResource(
            Res.string.no_internet
        )

        DataError.Remote.SERIALIZATION -> UiText.CmpStringResource(
            Res.string.error_serialization
        )

        DataError.Remote.UNKNOWN -> UiText.CmpStringResource(
            Res.string.unknown_error
        )

        DataError.Local.UNKNOWN -> UiText.CmpStringResource(
            Res.string.unknown_error
        )

        DataError.Remote.SERVER -> UiText.CmpStringResource(
            Res.string.server_error
        )

        DataError.Remote.NOT_FOUND -> UiText.CmpStringResource(
            Res.string.not_found
        )

        DataError.Remote.BAD_REQUEST -> UiText.CmpStringResource(
            Res.string.bad_request
        )

        DataError.Remote.UNAUTHORIZED -> UiText.CmpStringResource(
            Res.string.unauthorized
        )

        DataError.Local.DISK_FULL -> UiText.CmpStringResource(
            Res.string.error_disk_full
        )
    }
}