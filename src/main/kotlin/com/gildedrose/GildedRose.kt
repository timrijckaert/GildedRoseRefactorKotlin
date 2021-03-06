package com.gildedrose

// Could become an immutable variable, but prohibited by requirements
class GildedRose(var items: Array<Item>) {

    private val defaultItemStrategy = defaultSellInStrategy() to defaultQualityStrategy()
    private val strategyMap: Map<String, Pair<SellInStrategy, QualityStrategy>> =
        mapOf(
            agedBrie to (defaultSellInStrategy() to agedBrieQualityStrategy()),
            backstagePassess to (defaultSellInStrategy() to backstagePassesQualityStrategy()),
            conjuredManaCake to (defaultSellInStrategy() to conjuredManaCakeQualityStrategy()),
            sulfuras to (identitySellInStrategy() to identityQualityStrategy())
        )

    fun updateQuality() {
        items.forEach {
            val currSellIn = SellIn(it.sellIn)
            val currQuality = Quality(it.quality)

            val (sellInStrategy, qualityStrategy) = strategyMap.getOrDefault(it.name, defaultItemStrategy)
            val newSellIn = sellInStrategy.updateSellInValue(currSellIn)
            val extraQuality = qualityStrategy.calculateExtraQuality(currSellIn, currQuality)

            it.quality = (currQuality + extraQuality).quality
            it.sellIn = newSellIn.days
        }
    }
}

