package com.gildedrose

import kotlin.math.abs

fun interface QualityStrategy {
    fun updateQuality(sellIn: SellIn, currentQuality: Quality): Int
}

internal fun defaultQualityStrategy() =
    QualityStrategy { sellIn, _ ->
        if (sellIn.days <= 0) {
            -2
        } else {
            -1
        }
    }

internal fun agedBrieQualityStrategy() = QualityStrategy { sellIn, currQuality -> abs(defaultQualityStrategy().updateQuality(sellIn, currQuality)) }
internal fun identityQualityStrategy() = QualityStrategy { _, _ -> 0 }
internal fun backstagePassesQualityStrategy() = QualityStrategy { sellIn, currQuality ->
    if (sellIn.days - 1 < 0) {
        -currQuality.quality
    } else {
        when {
            sellIn.days < 6 -> 3
            sellIn.days < 11 -> 2
            else -> 1
        }
    }
}

internal fun conjuredManaCakeQualityStrategy() = QualityStrategy { sellIn, currQuality -> defaultQualityStrategy().updateQuality(sellIn, currQuality) * 2 }
