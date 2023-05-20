package com.severett.planetxcompose.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Foo(val fizz: String, val bazz: List<String>, val count: UInt)

data class ThirdPartyFoo(val fizz: String, val bazz: List<String>, val count: UInt)
