package main

class MovieRentingSystem(n: Int, entries: Array<IntArray>) {
    //n개의 가게
    // 영화는 2차원 배열 각각 엔트리가 [가게,영화, 가격] 을 나타냄
    // 각각의 가게는 영화를 1개까지 가짐
    // n은 30만까지 배열의 길이는 10만개까지 영화번호와 가격은 1만까지 쿼리는 10만번까지

    // 특정 영화를 빌릴수있는 샵 번호
    private val rentedMovies = mutableSetOf<Pair<Int,Int>>()
    private val unRentedMovies = mutableMapOf<Int, MutableSet<Int>>()
    private val shops = mutableMapOf<Int, MutableMap<Int,Pair<Int,Boolean>>>()
    init {
        for ((s,m,p) in entries){
            shops.computeIfAbsent(s) { mutableMapOf() }
            shops[s]!![m] = Pair(p,true)
            unRentedMovies.merge(m, mutableSetOf(s)) {
                    v1,_ -> v1.plus(s) as MutableSet<Int>
            }
        }
    }

    // 가게중에 해당 영화를 가장 싸게 대여할 수있는걸(대여되지 않은) 5개 보여줌
    // 가격 오름차순으로 정렬할것, 같은 가격이면 가게번호가 작은순으로
    // 해당하는 가게가 없으면 빈 배열을, 5개 미만의 가게가 검색되면 그만큼 반환
    fun search(movie: Int): List<Int> {
        return unRentedMovies[movie]!!.toList()
            .sortedWith(compareBy<Int> { shop -> shops[shop]!![movie]!!.first }.thenBy { it })
            .take(5)
    }

    //해당 가게의 해당 영화를 대여한다.
    fun rent(shop: Int, movie: Int) {
        // 상수시간에 수행되어야함
        val curShop = shops[shop]!!
        val curMovie = curShop[movie]
        curShop.replace(movie, Pair(curMovie!!.first, false))

        unRentedMovies[movie]!!.minus(shop)
        rentedMovies.add(Pair(shop,movie))
    }

    //해당 가게의 이전에 대여된 해당 영화를 드롭한다..?
    fun drop(shop: Int, movie: Int) {
        // 상수시간에 수행되어야함
        val curShop = shops[shop]!!
        val curMovie = curShop[movie]
        curShop.replace(movie, Pair(curMovie!!.first, true))

        unRentedMovies[movie]!!.plus(shop)
        rentedMovies.remove(Pair(shop,movie))
    }

    // 대여중인 가장 싼 5개 영화를 반환, 같은 영화도 가능
    // 2차원 배열의 res[i] -> [shop(i), movie(i)] 로 i번째로 싼 가게와 영화를 나타냄
    // 영화는 가격 오름차순으로 정렬하되 가격이 같으면 가게번호로 정렬하는데 그것도 같으면 영화번호로 정렬한다.
    fun report(): List<List<Int>> {
        return rentedMovies.toList()
            .sortedWith(
                compareBy<Pair<Int, Int>> { shops[it.first]!![it.second]!!.first }
                    .thenBy { it.first }
                    .thenBy { it.second }
            ).take(5).map { it.toList() }
    }

}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * var obj = MovieRentingSystem(n, entries)
 * var param_1 = obj.search(movie)
 * obj.rent(shop,movie)
 * obj.drop(shop,movie)
 * var param_4 = obj.report()
 */
