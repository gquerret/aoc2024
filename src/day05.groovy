import java.nio.file.*

def textFile = Paths.get('resources/day05-01.txt')
def rules = []
def updates = []

def blank = false
textFile.readLines().collect { line ->
  if (line.isBlank()) {
    blank = true
  } else if (blank) {
    updates.add(line.tokenize(",").collect(it -> Integer.parseInt(it)))
  } else {
    rules.add(line.tokenize("|").collect(it -> Integer.parseInt(it)))
  }
}

def sum = 0
def sum2 = 0
updates.each { update -> 
  println update
  def ok = true
  update.eachWithIndex { number, idx ->
    for (int zz = idx + 1; zz < update.size(); zz++) {
      if (rules.contains([update.get(zz), number]))
        ok = false
    }
  }
  if (ok) {
    sum += getMiddlePage(update)
  } else {
    def ok2 = false
    lbl1:
    while (!ok2) {
      for (int xx = 0; xx < update.size(); xx++) {
        for (int zz = xx + 1; zz < update.size(); zz++) {
          if (rules.contains([ update[zz], update[xx]])) {
            def tmp = update[xx]
            update[xx] = update[zz]
            update[zz] = tmp
            continue lbl1
          }
        }
      }
      ok2 = true
    }
    sum2 += getMiddlePage(update)
  }
}

println sum
println sum2

def getMiddlePage(update) {
  return update[update.size() / 2]
}