package com.severett.planetxcompose.lincheck

import org.jetbrains.kotlinx.lincheck.RandomProvider
import org.jetbrains.kotlinx.lincheck.paramgen.ParameterGenerator

class AnEnumGen(randomProvider: RandomProvider, configuration: String) : ParameterGenerator<AnEnum> {
    override fun generate(): AnEnum {
        return AnEnum.entries.random()
    }
}
