package com.example.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<SuperheroItem>
)

data class SuperheroItem(
    @SerializedName("response") val response:String,
    @SerializedName("id") val superheroId:String,
    @SerializedName("name") val name:String,
    @SerializedName("powerstats") val powerstats: Powerstats,
    @SerializedName("appearance") val appearance: Appearance,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("work") val work: Work,
    @SerializedName("connections") val connections: Connections,
    @SerializedName("image") val image: Image

)

data class Powerstats(
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String
)

data class Appearance(
    @SerializedName("gender") val gender:String,
    @SerializedName("race") val race:String
)

data class Biography(
    @SerializedName("full-name") val fullName:String,
    @SerializedName("alter-egos") val alterEgos:String,
    @SerializedName("place-of-birth") val placeOfBirth:String,
    @SerializedName("first-appearance") val firstAppearance:String,
    @SerializedName("publisher") val publisher:String,
    @SerializedName("alignment") val alignment:String
)

data class Work(
    @SerializedName("base") val base:String,
    @SerializedName("occupation") val occupation:String
)

data class Connections(
    @SerializedName("group-affiliation") val groupAffiliation:String,
    @SerializedName("relatives") val relatives:String
)

data class Image(
    @SerializedName("url") val url:String
)

