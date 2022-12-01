package id.isato.emina.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PaginationResponse(

	@field:SerializedName("last_visible_page")
	val lastVisiblePage: Int? = null,

	@field:SerializedName("has_next_page")
	val hasNextPage: Boolean? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null,

	@field:SerializedName("items")
	val items: PaginationItemResponse? = null,

	@field:SerializedName("data")
	val data: List<AnimeResponse>

)

data class PaginationItemResponse(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null

)
