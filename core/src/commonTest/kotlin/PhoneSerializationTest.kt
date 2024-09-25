import kommander.expect
import kotlin.test.Test
import kotlinx.serialization.json.Json
import sim.Phone

class PhoneSerializationTest {
    val codec = Json {
        ignoreUnknownKeys = true
    }

    @Test
    fun should_be_able_to_serialize_a_phone_number() {
        val phone = Phone.parse("+255 712 345 678")
        val json = codec.encodeToString(Phone.serializer(),phone)
        println(json)
        val value = codec.decodeFromString<Phone>(json)
        expect(value).toBe(phone)
    }
}