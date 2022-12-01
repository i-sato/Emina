package id.isato.emina.utils

import id.isato.emina.BuildConfig
import okhttp3.CertificatePinner

object CertificatePinnerUtils {

    fun createCertificatePinner(): CertificatePinner {
        val hostName = "api.jikan.moe"
        val builder = CertificatePinner.Builder()
        val pins = arrayOf(
            BuildConfig.CERT_SUBJECT_API,
            BuildConfig.CERT_SUBJECT_R3,
            BuildConfig.CERT_SUBJECT_ISRG_ROOT_X1
        )
        for (pin in pins) {
            builder.add(hostName, pin.toSha256)
        }
        return builder.build()
    }

    private val String.toSha256: String
        get() = "sha256/${this}"

}