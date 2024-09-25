@file:OptIn(InternalSerializationApi::class)

package sim

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PhoneSerializer : KSerializer<Phone> {
    override val descriptor: SerialDescriptor = buildSerialDescriptor("sim.Phone", kind = PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Phone) = encoder.encodeString(value.value)
    override fun deserialize(decoder: Decoder): Phone = Phone.parse(decoder.decodeString())
}