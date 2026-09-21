package com.therealpercival.avalon.domain.model

import com.therealpercival.avalon.R

sealed class AvalonCharacter(
    val name: String,
    val cardImage: Int,
    val tileImage: Int
) {
    object Merlin : AvalonCharacter(
        name = "Merlin",
        cardImage = R.drawable.merlin_placeholder,
        tileImage = R.drawable.merlin_tile_placeholder
    )

    object Percival : AvalonCharacter(
        name = "Percival",
        cardImage = R.drawable.percival_placeholder,
        tileImage = R.drawable.percival_tile_placeholder
    )

    object LoyalServantOfArthur1 : AvalonCharacter(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_1_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_1_tile_placeholder
    )

    object LoyalServantOfArthur2 : AvalonCharacter(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_2_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_2_tile_placeholder
    )

    object LoyalServantOfArthur3 : AvalonCharacter(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_3_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_3_tile_placeholder
    )

    object LoyalServantOfArthur4 : AvalonCharacter(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_4_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_4_tile_placeholder
    )

    object LoyalServantOfArthur5 : AvalonCharacter(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_5_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_5_tile_placeholder
    )

    object LoyalServantOfArthur6 : AvalonCharacter(
        name = "Loyal Servant of Arthur",
        cardImage = R.drawable.loyal_servant_of_arthur_6_placeholder,
        tileImage = R.drawable.loyal_servant_of_arthur_6_tile_placeholder
    )

    object Assassin : AvalonCharacter(
        name = "Assassin",
        cardImage = R.drawable.assassin_placeholder,
        tileImage = R.drawable.assassin_tile_placeholder
    )

    object Morgana : AvalonCharacter(
        name = "Morgana",
        cardImage = R.drawable.morgana_placeholder,
        tileImage = R.drawable.morgana_tile_placeholder
    )

    object Mordred : AvalonCharacter(
        name = "Mordred",
        cardImage = R.drawable.mordred_placeholder,
        tileImage = R.drawable.mordred_tile_placeholder
    )

    object MinionOfMordred1 : AvalonCharacter(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_1_placeholder,
        tileImage = R.drawable.minion_of_mordred_1_tile_placeholder
    )

    object MinionOfMordred2 : AvalonCharacter(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_2_placeholder,
        tileImage = R.drawable.minion_of_mordred_2_tile_placeholder
    )

    object MinionOfMordred3 : AvalonCharacter(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_3_placeholder,
        tileImage = R.drawable.minion_of_mordred_3_tile_placeholder
    )

    object MinionOfMordred4 : AvalonCharacter(
        name = "Minion of Mordred",
        cardImage = R.drawable.minion_of_mordred_4_placeholder,
        tileImage = R.drawable.minion_of_mordred_4_tile_placeholder
    )
}
