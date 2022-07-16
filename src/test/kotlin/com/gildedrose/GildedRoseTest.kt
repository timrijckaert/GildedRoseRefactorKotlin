package com.gildedrose

import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals

internal class GildedRoseTest : StringSpec() {
    init {
        "foo" {
            val items = arrayOf(Item("foo", 0, 0))
            val app = GildedRose(items)
            app.updateQuality()
            assertEquals("foo", app.items[0].name)
        }
    }
}


