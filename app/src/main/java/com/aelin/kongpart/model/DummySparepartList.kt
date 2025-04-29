package com.aelin.kongpart.model

import com.aelin.kongpart.R

object DummySparepartDatasource {
    val categoryIcons = mapOf(
        "oli" to R.drawable.oil_icon,
        "aki" to R.drawable.battery_accu_icon,
        "ban" to R.drawable.tyre_icon,
        "busi" to R.drawable.spark_plug_icon
    )

    val dummySparepart = listOf(
        Sparepart(
            id = 1,
            name = "Aki Yuasa MF",
            description = "Aki bebas perawatan untuk motor harian.",
            price = 750000.0,
            category = "aki",
            image = 0,
            stock = 15,
            tags = listOf("motor", "aki", "battery")
        ),
        Sparepart(
            id = 2,
            name = "Aki GS Astra",
            description = "Aki kering berkualitas buatan Astra.",
            price = 720000.0,
            category = "aki",
            image = 0,
            stock = 10,
            tags = listOf("motor", "aki", "listrik")
        ),
        Sparepart(
            id = 3,
            name = "Aki Bosch SM",
            description = "Aki premium untuk motor performa tinggi.",
            price = 800000.0,
            category = "aki",
            image = 0,
            stock = 8,
            tags = listOf("motor", "aki", "premium")
        ),
        Sparepart(
            id = 4,
            name = "Aki Panasonic",
            description = "Aki terpercaya untuk mobil dan motor.",
            price = 780000.0,
            category = "aki",
            image = 0,
            stock = 12,
            tags = listOf("mobil", "motor", "battery")
        ),
        Sparepart(
            id = 5,
            name = "Aki Incoe",
            description = "Aki berkualitas buatan lokal.",
            price = 700000.0,
            category = "aki",
            image = 0,
            stock = 20,
            tags = listOf("motor", "aki", "otomotif")
        ),
        Sparepart(
            id = 6,
            name = "Busi NGK Iridium",
            description = "Busi Iridium untuk performa maksimal.",
            price = 125000.0,
            category = "busi",
            image = 0,
            stock = 25,
            tags = listOf("motor", "busi", "performance")
        ),
        Sparepart(
            id = 7,
            name = "Busi Denso Platinum",
            description = "Busi irit dan tahan lama.",
            price = 115000.0,
            category = "busi",
            image = 0,
            stock = 18,
            tags = listOf("motor", "mobil", "busi")
        ),
        Sparepart(
            id = 8,
            name = "Busi Champion Copper",
            description = "Busi standar untuk motor harian.",
            price = 55000.0,
            category = "busi",
            image = 0,
            stock = 30,
            tags = listOf("motor", "busi", "standard")
        ),
        Sparepart(
            id = 9,
            name = "Busi Bosch Super",
            description = "Busi handal untuk motor sport.",
            price = 95000.0,
            category = "busi",
            image = 0,
            stock = 20,
            tags = listOf("motor", "busi", "sport")
        ),
        Sparepart(
            id = 10,
            name = "Busi Torch Spark",
            description = "Busi murah dan awet.",
            price = 45000.0,
            category = "busi",
            image = 0,
            stock = 50,
            tags = listOf("motor", "busi", "harian")
        ),
        Sparepart(
            id = 11,
            name = "Oli Shell Advance AX7",
            description = "Oli semi-sintetik untuk motor bebek.",
            price = 95000.0,
            category = "oli",
            image = 0,
            stock = 40,
            tags = listOf("motor", "oli", "semi-synthetic")
        ),
        Sparepart(
            id = 12,
            name = "Oli Castrol Power1",
            description = "Oli performa tinggi untuk motor sport.",
            price = 110000.0,
            category = "oli",
            image = 0,
            stock = 35,
            tags = listOf("motor", "sport", "pelumas")
        ),
        Sparepart(
            id = 13,
            name = "Oli Motul 300V",
            description = "Oli racing terbaik.",
            price = 250000.0,
            category = "oli",
            image = 0,
            stock = 10,
            tags = listOf("racing", "oli", "motor")
        ),
        Sparepart(
            id = 14,
            name = "Oli Federal Matic 30",
            description = "Oli spesial untuk motor matic.",
            price = 80000.0,
            category = "oli",
            image = 0,
            stock = 50,
            tags = listOf("motor", "matic", "oli")
        ),
        Sparepart(
            id = 15,
            name = "Oli Top 1 Action Plus",
            description = "Oli full synthetic berkualitas.",
            price = 120000.0,
            category = "oli",
            image = 0,
            stock = 20,
            tags = listOf("motor", "oli", "full-synthetic")
        ),
        Sparepart(
            id = 16,
            name = "Ban IRC NR76",
            description = "Ban motor harian anti slip.",
            price = 350000.0,
            category = "ban",
            image = 0,
            stock = 22,
            tags = listOf("motor", "ban", "harian")
        ),
        Sparepart(
            id = 17,
            name = "Ban FDR Sport XR",
            description = "Ban balap harian performa tinggi.",
            price = 420000.0,
            category = "ban",
            image = 0,
            stock = 15,
            tags = listOf("motor", "sport", "ban")
        ),
        Sparepart(
            id = 18,
            name = "Ban Michelin Pilot Street",
            description = "Ban motor premium.",
            price = 550000.0,
            category = "ban",
            image = 0,
            stock = 12,
            tags = listOf("motor", "premium", "ban")
        ),
        Sparepart(
            id = 19,
            name = "Ban Swallow Classic",
            description = "Ban klasik cocok motor retro.",
            price = 300000.0,
            category = "ban",
            image = 0,
            stock = 25,
            tags = listOf("motor", "classic", "ban")
        ),
        Sparepart(
            id = 20,
            name = "Ban Corsa Platinum V22",
            description = "Ban grip kuat cocok touring.",
            price = 400000.0,
            category = "ban",
            image = 0,
            stock = 18,
            tags = listOf("motor", "touring", "ban")
        )
    )
}