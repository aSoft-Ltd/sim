package sim

import kotlinx.serialization.Serializable
import nation.Country

@Serializable(with = PhoneSerializer::class)
data class Phone(
    val code: Int,
    val body: String
) {

    constructor(country: Country,body: String):this(country.dialingCode,body)

    companion object {
        fun parse(value: String?): Phone {
            if (value == null) throw IllegalArgumentException("Invalid phone number: $value")
            val dashless = value.replace("-", "")
            val codeless = dashless.replace("+", "").replace(" ", "")
            val candidates = Country.entries.filter { codeless.startsWith("${it.dialingCode}") }
            if (candidates.isEmpty()) {
                throw IllegalArgumentException("Failed to detect country code of phone: $value")
            }
            val code = candidates.first().dialingCode
            val body = codeless.substringAfter("$code")
            return Phone(code, body)
        }
    }

    val value by lazy { "${code}$body" }
}