import java.nio.file.*

def left = []
def right = []

def textFile = Paths.get('resources/day01-01.txt')
textFile.readLines().collect { line ->
  [ Integer.parseInt(line.substring(0, line.indexOf(' '))),
    Integer.parseInt(line.substring(line.indexOf(' ')).trim()) ] as Integer[]
}.forEach {
  left.add(it[0]);
  right.add(it[1])
}

def similarity = 0L
left.each { it ->
  similarity += it * right.findAll( it2 -> it == it2 ).size()
}
println similarity
