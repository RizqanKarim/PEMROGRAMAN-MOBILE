package com.example.laprak3_compose

data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val year: String,
    val engine: String,
    val description: String,
    val imageResId: Int,
    val webUrl: String
)

val carList = listOf(
    Car(
        id = 1,
        brand = "Toyota",
        model = "Supra MK4",
        year = "1998",
        engine = "3.0L Twin-Turbo 2JZ-GTE",
        description = "Ikon budaya pop Jepang yang mendunia berkat mesin 2JZ-GTE yang sangat tangguh dan mudah dimodifikasi hingga ribuan tenaga kuda. Supra MK4 tetap menjadi salah satu mobil sport paling dicari sepanjang masa karena desainnya yang abadi dan performa luar biasa.",
        imageResId = R.drawable.car1,
        webUrl = "https://en.wikipedia.org/wiki/Toyota_Supra"
    ),
    Car(
        id = 2,
        brand = "Nissan",
        model = "Skyline GT-R R34",
        year = "2002",
        engine = "2.6L Twin-Turbo RB26DETT",
        description = "Dijuluki sebagai 'Godzilla', R34 memadukan kecanggihan sistem penggerak All-Wheel Drive ATTESA E-TS dengan mesin legendaris RB26DETT. Mobil ini adalah puncak dari seri Skyline yang mendominasi lintasan balap dan menjadi bintang di layar lebar.",
        imageResId = R.drawable.car2,
        webUrl = "https://en.wikipedia.org/wiki/Nissan_Skyline_GT-R"
    ),
    Car(
        id = 3,
        brand = "Porsche",
        model = "911 GT3 RS",
        year = "2023",
        engine = "4.0L Naturally Aspirated Flat-6",
        description = "Sebuah jet darat yang legal di jalan raya. Porsche 911 GT3 RS generasi terbaru ini fokus pada aerodinamika ekstrem dan presisi handling. Mesin Flat-6 Naturally Aspirated miliknya mampu berteriak hingga 9.000 RPM, memberikan pengalaman berkendara yang murni.",
        imageResId = R.drawable.car3,
        webUrl = "https://en.wikipedia.org/wiki/Porsche_911_GT3"
    ),
    Car(
        id = 4,
        brand = "Ford",
        model = "Mustang Shelby GT500",
        year = "2020",
        engine = "5.2L Supercharged V8 'Predator'",
        description = "Puncak dari performa American Muscle. Dengan mesin 'Predator' V8 supercharged, Shelby GT500 menghasilkan tenaga buas yang mampu melahap lintasan drag maupun sirkuit balap dengan teknologi suspensi MagneRide yang canggih.",
        imageResId = R.drawable.car4,
        webUrl = "https://en.wikipedia.org/wiki/Shelby_Mustang"
    ),
    Car(
        id = 5,
        brand = "Mazda",
        model = "RX-7 FD",
        year = "1995",
        engine = "1.3L Sequential Twin-Turbo Rotary",
        description = "Dikenal karena keindahan desain 'organic' yang tidak pernah lekang oleh waktu dan penggunaan mesin Rotary Engine yang unik. RX-7 FD menawarkan keseimbangan berat 50:50 yang sempurna, menjadikannya salah satu mobil dengan handling terbaik di masanya.",
        imageResId = R.drawable.car5,
        webUrl = "https://en.wikipedia.org/wiki/Mazda_RX-7"
    )
)