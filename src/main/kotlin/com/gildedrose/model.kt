package com.gildedrose

@JvmInline
value class SellIn(val days: Int)

@JvmInline
value class Quality(val quality: Int) {
    operator fun plus(addedQuality: Int): Quality =
        if (addedQuality != 0) {
            Quality((quality + addedQuality).coerceIn(0..50))
        } else {
            this
        }
}
