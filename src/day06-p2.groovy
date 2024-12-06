import java.nio.file.*

def textFile = Paths.get('resources/day06-01.txt')
def text = ""
def w = 0
textFile.readLines().collect { line ->
  w = line.length()
  text += line
}
def arr = text.toCharArray()

// ^: 0, >: 1, ∨! 2, <: 3
def offset = [ -w, 1, w, -1]
def facing = 0
def pos = text.indexOf('^')
arr[pos] = '0'

def stuckCount = 0
for (int zz = 0; zz < arr.length; zz++) {
  println "Test ${zz} / ${arr.length}"
  def cpy = arr.collect(it -> { if (it == '#') return [ -1 ] else if (it == '^') return [ 0 ] else return [] } )
  if (cpy[zz].size() == 0) {
    cpy[zz] = [ -1 ]
    if (isStuck(cpy, pos, w))
      stuckCount++
  }
}
println stuckCount

def isStuck(arr, pos, w) {
  def offset = [ -w, 1, w, -1]
  def facing = 0
  def quit = false
  def stuck = false
  loop:
  while (!quit) {
    // Going to leave?
    if ((facing == 0) && (pos < w)) {
      quit = true
      break loop
    }
    if ((facing == 2) && (pos >= (w - 1) * w)) {
      quit = true
      break loop
    }
    if ((facing == 1) && ((pos % w) == (w - 1))) {
      quit = true
      break loop
    }
    if ((facing == 3) && ((pos % w) == 0)) {
      quit = true
      break loop
    }
    // Blocked ?
    if (arr[pos + offset[facing]].contains(-1)) {
      facing = (facing + 1) % 4
    } else if (arr[pos + offset[facing]].contains(facing)) {
      stuck = true
      break loop
    } else {
      arr[pos + offset[facing]].add(facing)
      pos = pos + offset[facing]
    }
  }
  return stuck
}
