
package com.grabtaxi.android_tech_test_grab

import com.grabtaxi.android_tech_test_grab.model.NewsItems
import com.grabtaxi.android_tech_test_grab.model.NewsResponse

object TestUtil {

    fun createNewsResponse() = NewsResponse(
        newsList = createNewsItems(
            5,
            "author",
            "title",
            "description",
            "url",
            "urlToImage",
            "publishedAt",
            "content"
        ),
        status = "OK")

    fun createNewsItems(count: Int, author: String, title: String, description: String,url: String,
                        urlToImage: String,publishedAt: String,content: String): List<NewsItems> {
        return (0 until count).map {
            createNewsItem(
                count = it,
                author = author + it,
                title = title + it,
                description = description + it,
                url = url + 1,
                urlToImage = urlToImage + 1,
                publishedAt = publishedAt + 1,
                content = content + 1
            )
        }
    }

    fun createNewsItem(count: Int, author: String, title: String, description: String,url: String,
                       urlToImage: String,publishedAt: String,content: String) = NewsItems(
        id =  count,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}
