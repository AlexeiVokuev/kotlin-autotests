package org.example

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions.assertTrue
import org.hamcrest.Matcher
import org.hamcrest.StringDescription
import java.util.*

val om = ObjectMapper()

/**
 * @param first parameter name.
 * @param second parameter value.
 * */
typealias ResourceParameter = Pair<String, String>

/** @throws AssertionError if value not matches. */
fun Matcher<Any?>.matchesExceptionally(value: Any?) {
    if (!matches(value)) {
        val description = StringDescription()
        //describeMismatch(value, description)
        throw AssertionError(description.toString())
    }
}

/** @throws AssertionError if value not contains. */
fun Matcher<String>.matchesExceptionally(value: String) {
    if (!matches(value)) {
        val description = StringDescription()
        //describeMismatch(value, description)
        throw AssertionError(description.toString())
    }
}

/** @throws AssertionError if value not size of. */
fun Matcher<Collection<Any?>>.matchesExceptionally(value: Collection<Any?>) {
    if (!matches(value)) {
        val description = StringDescription()
        //describeMismatch(value, description)
        throw AssertionError(description.toString())
    }
}

/** @throws AssertionError if value not size of. */
fun Matcher<Map<Any?, Any?>>.matchesExceptionally(value: Map<Any?, Any?>) {
    if (!matches(value)) {
        val description = StringDescription()
        //describeMismatch(value, description)
        throw AssertionError(description.toString())
    }
}

/** @throws AssertionError if value not size of. */
fun Matcher<JsonNode>.matchesExceptionally(value: JsonNode) {
    if (!matches(value)) {
        val description = StringDescription()
        //describeMismatch(value, description)
        throw AssertionError(description.toString())
    }
}

/** @throws AssertionError if all value not in the list. */
@Step("Проверка присутствия всех элементов в списке")
fun List<Any?>.assertContainsAll(expectedList: List<Any?>) = assertTrue(
        this.containsAll(expectedList),
        "List:\n${this.joinToString("\n")}\n Not contains all of:\n${expectedList.joinToString("\n")}"
)

/** Returns a random element */
fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null


@Step("Проверка на идентичность списков Json")
fun JsonText.assertCompareList(jsonPath: String, expected: JsonText){
    this.compareWithStringList(jsonPath, expected)
}

@Step("Проверка присутствия элементов в списке Json")
fun JsonText.assertContainsList(jsonPath: String, expectedList: JsonText){
    this.containsStringList(jsonPath, expectedList)
}

@Step("Проверка на идентичность объекта Json")
fun JsonText.assertCompare(jsonPath: String, expectedMap: JsonText){
    this.compareObject(jsonPath, expectedMap)
}

@Step("Проверка на идентичность сложного объекта Json")
fun JsonText.assertCompareCompoundObject(jsonPath: String, expectedMap: JsonText){
    this.compareWithCompoundObject(jsonPath, expectedMap)
}

@Step("Проверка присутствия элементов в массиве Json")
fun JsonText.assertContainsArray(jsonPath: String, expectedArray: JsonText){
    this.containsArray(jsonPath, expectedArray)
}

@Step("Проверка на идентичность массивов Json")
fun JsonText.assertCompareArray(jsonPath: String, expectedArray: JsonText){
    this.compareWithArray(jsonPath, expectedArray)
}