package nl.mplatvoet.komponents.kovenant.android.demo

import argo.jdom.JdomParser

class AuctionSearchJsonParser {
    companion object {
        val parser = JdomParser()
    }

    fun parse(text: String): Result {
        val rootNode = parser.parse(text)

        val catalogs = rootNode.getArrayNode("data", "pageData").flatMap { it.getArrayNode("items") }
        val items = catalogs.filter { !it.isNullNode("coverImage") }
                .map {
                    val title = it.getStringValue("catalogTitle")
                    val imageUrl = it.getStringValue("coverImage", "thumbURL")
                    val house = it.getStringValue("house", "houseName")
                    val url = it.getStringValue("linkCatalogURL")

                    Auction(title, house, url, imageUrl)
                }
        return Result(items)
    }
}

data class Result(val auctions: List<Auction>)

data class Auction(val name: String,
                   val house: String,
                   val url: String,
                   val imageUrl: String)
