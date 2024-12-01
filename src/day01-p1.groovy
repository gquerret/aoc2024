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

def sLeft = left.toSorted()
def sRight = right.toSorted()
def sum = 0L
sLeft.eachWithIndex { leftVal, idx ->
  sum += Math.abs(sRight[idx] - leftVal)
}
println sum
