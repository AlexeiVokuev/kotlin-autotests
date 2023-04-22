package org.example

import io.restassured.path.json.JsonPath
import org.hamcrest.Matcher
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.junit.jupiter.api.Assertions.*

class JsonText {

    private val parsedBody: JsonPath

    constructor(message: String) {
        this.parsedBody = JsonPath.from(message)
    }

    constructor(message: JsonPath) {
        this.parsedBody = message
    }

    override fun toString(): String{
        return toText()
    }

    fun body(jsonPath: String, matcher: Matcher<Any?>): JsonText {
        val value = parsedBody.get<Any?>(jsonPath)
        matcher.matchesExceptionally(value)
        return this
    }

    fun sizeList(jsonPath: String, matcher: Matcher<Collection<Any?>>): JsonText {
        val value = parsedBody.get<Collection<Any?>>(jsonPath)
        matcher.matchesExceptionally(value)
        return this
    }

    fun map(jsonPath: String, matcher: Matcher<Map<Any?, Any?>>): JsonText {
        val value = parsedBody.get<Map<Any?, Any?>>(jsonPath)
        matcher.matchesExceptionally(value)
        return this
    }

    fun matchRegex(jsonPath: String, regex: Regex): JsonText {
        val value = parsedBody.get<String>(jsonPath)
        assertTrue(
                value.matches(regex),
                "Value $value not match to regex $regex"
        )
        return this
    }

    fun assertMapSize(jsonPath: String, size: Int): JsonText {
        val value: Map<String, Any>
        try {
            value = parsedBody.get<Map<String, Any>>(jsonPath)
        } catch (e: Exception) {
            assertTrue(false, "Cannot parse json:\n${toText()}\n${e.message}")
            return this
        }
        assertEquals(
                size, value.size,
                "Size values in $jsonPath = ${value.size}, expected $size." +
                        "\nValues: ${value.toList().joinToString(prefix = "\n", separator = "\n")}"
                )
        return this
    }

    private fun getArray(jsonPath: String = ""): ArrayList<Map<String, Any>>
            = parsedBody.get<ArrayList<Map<String, Any>>>(jsonPath)

    fun get(jsonPath: String = ""): Any = parsedBody.get(jsonPath)

    private fun getText(jsonPath: String = ""): String = parsedBody.get<Map<String, Any>>(jsonPath).toString()

    private fun getMap(jsonPath: String): Map<Any?, Any?> = parsedBody.get<Map<Any?, Any?>>(jsonPath)

    private fun getJsonTextFromPath(jsonPath: String): JsonText  =
            JsonText(prettyFromPath((jsonPath)))

    private fun prettyFromPath(jsonPath: String): String = try {
        JSONObject(parsedBody.get<Map<String, Any>>(jsonPath)).toJSONString()
    } catch (e: Exception) {
        val temp = JSONArray()
        temp.addAll(parsedBody.get<ArrayList<Map<String, Any>>>(jsonPath))
        temp.toJSONString()
    }

    // для сравнения простых объектов json (без мап и массивов внутри)
    fun compareObject(jsonPath: String, expected: JsonText){
        val actualMap = this.getMap(jsonPath)

        expected.getMap("").forEach {
            assertEquals( it.value, actualMap[it.key])
        }
    }

    private fun isArray(jsonPath: String): Boolean{
        return try{
            this.getArray(jsonPath)
            true
        } catch (e: Throwable){
            false
        }
    }

    private fun isMap(jsonPath: String): Boolean{
        return try{
            this.getMap(jsonPath)
            true
        } catch (e: Throwable){
            false
        }
    }

    private fun compare(current: JsonText, expected: JsonText): Boolean{
        if(current.isArray("")){
            var counter = 0
            val expectedSize = expected.getArray("").size
            var i = 0
            val skipList = ArrayList<Int>()

            while(i < expectedSize) {
                var j = 0
                while(j < current.getArray("").size && i < expectedSize){
                    if(skipList.contains(j)) {
                        j++
                        continue
                    }
                    if (compare(current.getJsonTextFromPath("[$j]"),
                                    expected.getJsonTextFromPath("[$i]"))){
                        counter++
                        skipList.add(j)
                        break
                    }
                    j++
                }
                i++
            }
            return counter == expectedSize
        }
        else {
            var check = true
            current.getMap("").forEach {
                if(current.isArray(it.key as String) || current.isMap(it.key as String)){
                    check = compare(current.getJsonTextFromPath(it.key as String),
                            expected.getJsonTextFromPath(it.key as String))
                    if(!check) {
                        return false
                    }
                }
                else{
                    if(it.value != expected.getMap("")[it.key as String])
                        return false
                }
            }
            return check
        }
    }

    fun compareWithCompoundObject(jsonPath: String, expected: JsonText){
        assertTrue(compare(this.getJsonTextFromPath(jsonPath), expected), "Items differ!")
    }

    // сравнение массивов элементов, в котором могут быть и мапы, и массивы внутри
    fun compareWithArray(jsonPath: String, expected: JsonText){
        val expectedSize = expected.getArray("").size
        val actualSize = this.getArray(jsonPath).size
        assertEquals(expectedSize, actualSize, "The sizes of the arrays are different!")
        var counter = 0
        var i = 0
        val skipList = ArrayList<Int>()

        while(i < expectedSize) {
            var j = 0
            val expectedElem = expected.getJsonTextFromPath("[$i]")
            while(j < actualSize && i < expectedSize){
                if(skipList.contains(j)) {
                    j++
                    continue
                }
                if (compare(this.getJsonTextFromPath("$jsonPath[$j]"), expectedElem)){
                    counter++
                    skipList.add(j)
                    break
                }
                j++
            }
            i++
        }
        assertEquals(expectedSize, counter, "$counter out of $expectedSize items equals expected elements")
    }

    fun containsArray(jsonPath: String, expected: JsonText){
        val expectedSize = expected.getArray("").size
        var counter = 0
        var i = 0
        val skipList = ArrayList<Int>()

        while (i < expectedSize) {
            var j = 0
            val expectedElem = expected.getMap("[$i]")
            while (j < this.getArray(jsonPath).size && i < expectedSize) {
                if(skipList.contains(j)) {
                    j++
                    continue
                }
                if (this.getMap("$jsonPath[$j]") == expectedElem) {
                    counter++
                    skipList.add(j)
                    break
                }
                j++
            }
            i++
        }

        assertEquals(expectedSize, counter, "This array contains $counter of $expectedSize expected elements")
    }

    fun compareWithStringList(jsonPath: String, expected: JsonText){
        val expectedSize = expected.getArray("").size
        val actualSize = this.getArray(jsonPath).size

        assertEquals(expectedSize, actualSize, "The sizes of the lists are different!")
        for (i in 0 until expectedSize){
            assertEquals(expected.getText("[$i]"), this.getText("$jsonPath[$i]"))
        }
    }

    fun containsStringList(jsonPath: String, expected: JsonText){
        assertTrue(this.getArray(jsonPath).containsAll(expected.getArray("")),
                "List:\n${this.getArray(jsonPath).joinToString("\n")}\n Not contains all of:" +
                        "\n${expected.getArray("").joinToString("\n")}")
    }

    private fun toText(): String = parsedBody.prettify()
}