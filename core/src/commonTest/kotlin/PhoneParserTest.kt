import kommander.expect
import kotlin.test.Test
import sim.Phone

class PhoneParserTest {
    @Test
    fun should_be_able_to_parse_a_phone_number() {
        val phone = Phone.parse("+255 712 345 678")
        expect(phone.code).toBe(255)
        expect(phone.body).toBe("712345678")
    }

    @Test
    fun should_retrieve_code_even_when_multiple_countries_are_detected() {
        val phone = Phone.parse("+15789994400")
        expect(phone.code).toBe(1)
        expect(phone.body).toBe("5789994400")
    }

    @Test
    fun should_parse_a_phone_number_with_dashes() {
        val phone = Phone.parse("+25-567-338844")
        expect(phone.code).toBe(255)
        expect(phone.body).toBe("67338844")
    }
}