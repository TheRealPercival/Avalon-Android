package com.therealpercival.avalon.domain.model

import com.therealpercival.avalon.R

sealed class Character(
    name: String,
    cardImage: Int,
    tileImage: Int
) {
    object Merlin : Character(
        name = "Merlin",
        cardImage = R.drawable.merlin_placeholder,
        tileImage = R.drawable.merlin_tile_placeholder
    )

    object Percival : Character(
        name = "Percival",
        cardImage = R.drawable.percival_placeholder,
        tileImage = R.drawable.percival_tile_placeholder
    )

    object LoyalServantOfArthur1 : Character(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_1_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_1_tile_placeholder
    )

    object LoyalServantOfArthur2 : Character(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_2_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_2_tile_placeholder
    )

    object LoyalServantOfArthur3 : Character(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_3_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_3_tile_placeholder
    )

    object LoyalServantOfArthur4 : Character(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_4_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_4_tile_placeholder
    )

    object LoyalServantOfArthur5 : Character(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_5_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_5_tile_placeholder
    )

    object LoyalServantOfArthur6 : Character(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_6_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_6_tile_placeholder
    )

    object Assassin : Character(
        name = "Assassin",
        cardImage = R.drawable.assassin_placeholder,
        tileImage = R.drawable.assassin_tile_placeholder
    )

    object Morgana : Character(
        name = "Morgana",
        cardImage = R.drawable.morgana_placeholder,
        tileImage = R.drawable.morgana_tile_placeholder
    )

    object Mordred : Character(
        name = "Mordred",
        cardImage = R.drawable.mordred_placeholder,
        tileImage = R.drawable.mordred_tile_placeholder
    )

    object MinionOfMordred1 : Character(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_1_placeholder,
        tileImage = R.drawable.minion_of_mordred_1_tile_placeholder
    )

    object MinionOfMordred2 : Character(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_2_placeholder,
        tileImage = R.drawable.minion_of_mordred_2_tile_placeholder
    )

    object MinionOfMordred3 : Character(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_3_placeholder,
        tileImage = R.drawable.minion_of_mordred_3_tile_placeholder
    )

    object MinionOfMordred4 : Character(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_4_placeholder,
        tileImage = R.drawable.minion_of_mordred_4_tile_placeholder
    )
}
