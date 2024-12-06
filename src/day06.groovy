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
arr[pos] = 'X'
def quit = false
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
  if (arr[pos + offset[facing]] == '#') {
    facing = (facing + 1) % 4
  } else {
    arr[pos + offset[facing]] = 'X'
    pos = pos + offset[facing]
  }
}

def count = 0
for (int zz = 0; zz < arr.length; zz++) {
  if (arr[zz] == 'X') count++
}
println count
