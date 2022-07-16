package com.gildedrose

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next

internal class GildedRoseTest : StringSpec() {
    init {
        dexterityVest {
            table(
                headers("days passed", "expected sell in", "expected quality"),
                row(1, 9, 19),
                row(9, 1, 11),
                row(10, 0, 10),
                row(11, -1, 8),
                row(15, -5, 0),
                row(16, -6, 0),
            ).forAll { days, sellIn, quality ->
                val item = Item(dexterityVest, 10, 20)
                val gildedRose = GildedRose(arrayOf(item))
                repeat(days) { gildedRose.updateQuality() }
                item shouldBeEqualToComparingFields Item(dexterityVest, sellIn, quality)
            }
        }

        agedBrie {
            table(
                headers("days passed", "expected sell in", "expected quality"),
                row(1, 1, 1),
                row(2, 0, 2),
                row(3, -1, 4),
                row(4, -2, 6),
                row(5, -3, 8),
                row(26, -24, 50),
                row(27, -25, 50),
            ).forAll { days, sellIn, quality ->
                val item = Item(agedBrie, 2, 0)
                val gildedRose = GildedRose(arrayOf(item))
                repeat(days) { gildedRose.updateQuality() }
                item shouldBeEqualToComparingFields Item(agedBrie, sellIn, quality)
            }
        }

        // TODO Check same as normal item?
        "Elixir of the Mongoose" {
            val elixirOfTheMongoose = "Elixir of the Mongoose"
            table(
                headers("days passed", "expected sell in", "expected quality"),
                row(1, 4, 6),
                row(2, 3, 5),
                row(5, 0, 2),
                row(6, -1, 0),
                row(7, -2, 0),
            ).forAll { days, sellIn, quality ->
                val item = Item(elixirOfTheMongoose, 5, 7)
                val gildedRose = GildedRose(arrayOf(item))
                repeat(days) { gildedRose.updateQuality() }
                item shouldBeEqualToComparingFields Item(elixirOfTheMongoose, sellIn, quality)
            }
        }

        sulfuras {
            val sellIn = Arb.int().next()
            val quality = Arb.int().next()
            val item = Item(sulfuras, sellIn, quality)
            val gildedRose = GildedRose(arrayOf(item))
            repeat(100) { gildedRose.updateQuality() }
            item shouldBeEqualToComparingFields Item(sulfuras, sellIn, quality)
        }

        backstagePassess {
            table(
                headers("days passed", "expected sell in", "expected quality"),
                row(1, 14, 21),
                row(2, 13, 22),
                row(5, 10, 25),
                row(6, 9, 27),
                row(10, 5, 35),
                row(11, 4, 38),
                row(14, 1, 47),
                row(15, 0, 50),
                row(16, -1, 0),
                row(17, -2, 0),
            ).forAll { days, sellIn, quality ->
                val item = Item(backstagePassess, 15, 20)
                val gildedRose = GildedRose(arrayOf(item))
                repeat(days) { gildedRose.updateQuality() }
                item shouldBeEqualToComparingFields Item(backstagePassess, sellIn, quality)
            }
        }

        conjuredManaCake {
            table(
                headers("days passed", "expected sell in", "expected quality"),
                row(1, 2, 8),
                row(2, 1, 6),
                row(3, 0, 4),
                row(4, -1, 0),
                row(5, -2, 0),
            ).forAll { days, sellIn, quality ->
                val item = Item(conjuredManaCake, 3, 10)
                val gildedRose = GildedRose(arrayOf(item))
                repeat(days) { gildedRose.updateQuality() }
                item shouldBeEqualToComparingFields Item(conjuredManaCake, sellIn, quality)
            }
        }

        "Normal item" {
            val unknown = "normal item"
            table(
                headers("days passed", "expected sell in", "expected quality"),
                row(1, 2, 9),
                row(2, 1, 8),
                row(3, 0, 7),
                row(4, -1, 5),
                row(5, -2, 3),
            ).forAll { days, sellIn, quality ->
                val item = Item(unknown, 3, 10)
                val gildedRose = GildedRose(arrayOf(item))
                repeat(days) { gildedRose.updateQuality() }
                item shouldBeEqualToComparingFields Item(unknown, sellIn, quality)
            }
        }
    }
}


