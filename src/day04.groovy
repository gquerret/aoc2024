import java.nio.file.*


def textFile = Paths.get('resources/day04-01.txt')
def text = ""
def w = 0
def h = 0
textFile.readLines().collect { line ->
  w = line.length()
  h++
  text += line
}

def found = 0L
for (int zz = 0; zz < text.length(); zz++) {
  if (text.getAt(zz) == 'X') {
    // Left to right
    if (((zz % w) <= (w - 4)) && (text.getAt(zz + 1) == 'M') && (text.getAt(zz + 2) == 'A') && (text.getAt(zz + 3) == 'S'))
      found++
    // Right to left
    if (((zz % w) >= 3) && (text.getAt(zz - 1) == 'M') && (text.getAt(zz - 2) == 'A') && (text.getAt(zz - 3) == 'S'))
      found++
    // Top to bottom
    if ((zz < text.length() - (3 * h)) && (text.getAt(zz + w) == 'M') && (text.getAt(zz + 2 * w) == 'A') && (text.getAt(zz + 3 * w) == 'S'))
      found++
    // Bottom to top
    if ((zz >= (3 * h)) && (text.getAt(zz - w) == 'M') && (text.getAt(zz - 2 * w) == 'A') && (text.getAt(zz - 3 * w) == 'S'))
      found++
    // Diagonale haut gauche
    if ((zz >= (3 * h)) && ((zz % w) >= 3) && (text.getAt(zz - (w + 1)) == 'M') && (text.getAt(zz - 2 * (w + 1)) == 'A') && (text.getAt(zz - 3 * (w + 1)) == 'S'))
      found++
    // Diagonale haut droit
    if ((zz >= (3 * h)) && ((zz % w) <= (w - 4)) && (text.getAt(zz - (w - 1)) == 'M') && (text.getAt(zz - 2 * (w - 1)) == 'A') && (text.getAt(zz - 3 * (w - 1)) == 'S'))
      found++
    // Diagonale bas gauche
    if ((zz < text.length() - (3 * h)) && ((zz % w) >= 3) && (text.getAt(zz + (w - 1)) == 'M') && (text.getAt(zz + 2 * (w - 1)) == 'A') && (text.getAt(zz + 3 * (w - 1)) == 'S'))
      found++
    // Diagonale bas droit
    if ((zz < text.length() - (3 * h)) && ((zz % w) <= (w - 4)) && (text.getAt(zz + (w + 1)) == 'M') && (text.getAt(zz + 2 * (w + 1)) == 'A') && (text.getAt(zz + 3 * (w + 1)) == 'S'))
      found++
  }
}
println found

def found2 = 0
for (int yy = 1 ; yy < h - 1; yy++) {
  for (int xx = 1 ; xx < w - 1; xx++) {
    pos = yy * h + xx
    if (text.getAt(pos) == 'A') {
      def diag1 = text.getAt(pos - w - 1) == 'M' && text.getAt(pos + w + 1) == 'S'
      def diag2 = text.getAt(pos - w - 1) == 'S' && text.getAt(pos + w + 1) == 'M'
      def diag3 = text.getAt(pos - w + 1) == 'M' && text.getAt(pos + w - 1) == 'S'
      def diag4 = text.getAt(pos - w + 1) == 'S' && text.getAt(pos + w - 1) == 'M'
      if ((diag1 || diag2) && (diag3 || diag4))
        found2++
    }
  }
}
println found2
