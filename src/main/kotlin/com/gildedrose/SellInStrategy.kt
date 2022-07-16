package com.gildedrose

internal fun interface SellInStrategy {
    fun updateSellInValue(currentValue: SellIn): SellIn
}

internal fun defaultSellInStrategy() = SellInStrategy { SellIn(it.days - 1) }
internal fun identitySellInStrategy() = SellInStrategy { it }
