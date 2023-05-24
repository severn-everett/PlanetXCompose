package com.severett.planetxcompose.common.serde

import com.severett.planetxcompose.common.model.Foo
import com.severett.planetxcompose.common.model.ThirdPartyFoo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("UnnecessaryVariable")
fun runSerde(
    isNormalSerde: Boolean,
    fizzStr: String,
    bazzStr: String,
    countStr: String
): Pair<String, String> {
    val fizz = fizzStr
    val bazz = bazzStr.split(",")
    val count = if (countStr.isNotBlank()) countStr.toUInt() else 0u
    return if (isNormalSerde) {
        val foo = Foo(fizz, bazz, count)
        val serializedFoo = Json.encodeToString(foo)
        val deserializedFoo = Json.decodeFromString<Foo>(serializedFoo)
        serializedFoo to deserializedFoo.toString()
    } else {
        val thirdPartyFoo = ThirdPartyFoo(fizz, bazz, count)
        val serializedTPF = Json.encodeToString(ThirdPartyFooSerializer, thirdPartyFoo)
        val deserializedTPF = Json.decodeFromString(ThirdPartyFooSerializer, serializedTPF)
        serializedTPF to deserializedTPF.toString()
    }
}
