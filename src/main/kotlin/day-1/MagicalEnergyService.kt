package day

import java.util.ListResourceBundle
import java.util.stream.Collectors


class MagicalEnergyService {
    fun getMaxCaloriesCarriedByElve(calories: List<String>): Int {
        return calories.split { it.isBlank() }
            .map { list -> convertToInt(list) }
            .maxOf { it.reduce { acc, next -> acc + next } }
    }

    fun getTotalCaloriesCarriedByTopThreeElves(calories: List<String>): Int {
        return calories.split { it.isBlank() }
            .map { list -> computeSumOfCarriedCaloriesForElve(list) }
            .sortedDescending()
            .take(3)
            .sum()
    }

    private fun computeSumOfCarriedCaloriesForElve(list: List<String>) =
        convertToInt(list).reduce { acc, next -> acc + next }

    private fun convertToInt(list: List<String>) = list.map { it.toInt() }
}

private fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { acc, t ->
        if (predicate(t)) acc.add(mutableListOf())
        else acc.last().add(t)
        acc
    }.filterNot { it.isEmpty() }
